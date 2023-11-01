package humoyun.olimjonov.appclickhumoyun.contreoller;

import humoyun.olimjonov.appclickhumoyun.dto.card.CardEditDto;
import humoyun.olimjonov.appclickhumoyun.dto.card.CardRequestDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UuidDto;
import humoyun.olimjonov.appclickhumoyun.entity.Cards;
import humoyun.olimjonov.appclickhumoyun.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {
    private final CardsService service;

    @GetMapping("/read")
    public List<Cards> readAllUsers() {
        return service.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Cards> createCard(@RequestBody CardRequestDto dto) throws Throwable {
        return service.createCard(dto);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCard(@RequestBody UuidDto uuid) {
        return service.deleteCard(uuid);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editCard(@RequestBody CardEditDto dto) throws Exception {
        return service.edit(dto);
    }

    @PostMapping("/findById")
    public Optional<?> findById(@RequestBody UuidDto dto) {
        return service.findById(dto.getUuid());
    }

    @GetMapping("/findCards")
    public List<Cards> getCardsByUserUuid(@RequestBody UuidDto dto) {
        return service.findCard(dto.getUuid());
    }
}