package humoyun.olimjonov.appclickhumoyun.service;

import humoyun.olimjonov.appclickhumoyun.dto.card.CardEditDto;
import humoyun.olimjonov.appclickhumoyun.dto.card.CardRequestDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UuidDto;
import humoyun.olimjonov.appclickhumoyun.entity.CardType;
import humoyun.olimjonov.appclickhumoyun.entity.Cards;
import humoyun.olimjonov.appclickhumoyun.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardsService {
    private final CardsRepository repository;

    public List<Cards> findAll() {
        return repository.findAll();
    }

    public Optional<Cards> findById(UUID uuid) {
        if (repository.existsByCardUUID(uuid)) return repository.findByCardUUID(uuid);
        else return Optional.empty();
    }

    public ResponseEntity<Cards> createCard(CardRequestDto dto) throws Throwable {
        boolean b = repository.existsByPan(dto.getPan());
        if (!b) {
            Cards cards = mapToEntity(dto);
            int type = 0;
            int expireM = 0;
            int expireY = 0;

            type = Integer.parseInt(dto.getPan().substring(0, 4));

            switch (type) {
                case 9860:
                    cards.setCardType(CardType.HUMO);
                    break;
                case 8600:
                    cards.setCardType(CardType.UZCARD);
                    break;
            }
            expireM = Integer.parseInt(dto.getExpire().substring(0, 2));
            expireY = Integer.parseInt(dto.getExpire().substring(2, 4));


            if (expireY < 23) {
                throw new Exception(String.valueOf(HttpStatus.BAD_REQUEST));
            } else if (expireY == 2023) {
                if (expireM < 10 || expireM > 12) {
                    throw new Exception(String.valueOf(HttpStatus.BAD_REQUEST));
                }
            } else {
                cards.setExpire(dto.getExpire());
            }
            cards.setUserUUID(dto.getUser_uuid());
            System.out.println(cards);
            repository.save(cards);
            return ResponseEntity.ok(cards);
        } else {
            throw new Throwable("Pan number already existed");
        }
    }

    public ResponseEntity<?> deleteCard(UuidDto uuid) {
        Optional<Cards> byUuid = repository.findByCardUUID(uuid.getUuid());
        if (byUuid.isEmpty()) return ResponseEntity.status(404).body("Card Not Found");
        repository.delete(byUuid.get());
        return ResponseEntity.ok("DELETED");
    }

    public ResponseEntity<?> edit(CardEditDto dto) throws Exception {
        Optional<Cards> byCardUUID = repository.findByCardUUID(dto.getCard_uuid());
        if (byCardUUID.isEmpty()) throw new Exception("Card Not Found");
        Cards cards = byCardUUID.get();
        cards.setCardUUID(dto.getCard_uuid());
        repository.save(cards);
        return ResponseEntity.ok(cards);

    }

    public List<Cards> findCard(UUID user_uuid) {
        return repository.findByUserUUID(user_uuid);
    }

    private Cards mapToEntity(CardRequestDto dto) {
        return Cards.builder().pan(dto.getPan()).expire(dto.getExpire()).balance(dto.getBalance()).cardUUID(UUID.randomUUID()).build();
    }


}
