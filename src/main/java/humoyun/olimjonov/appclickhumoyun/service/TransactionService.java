package humoyun.olimjonov.appclickhumoyun.service;

import humoyun.olimjonov.appclickhumoyun.entity.Cards;
import humoyun.olimjonov.appclickhumoyun.entity.TransactionHistory;
import humoyun.olimjonov.appclickhumoyun.repository.CardsRepository;
import humoyun.olimjonov.appclickhumoyun.repository.TransactionRepository;
import humoyun.olimjonov.appclickhumoyun.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final CardsRepository cardsRepository;
    private final TransactionRepository transactionRepository;
    private final UsersRepository usersRepository;
    private final CardsService cardsService;

    public ResponseEntity<String> p2p(String senderPan, String recipientPan, Long amount) throws Exception {
        if (cardsRepository.existsByPan(senderPan) && cardsRepository.existsByPan(recipientPan)) {
            TransactionHistory history = new TransactionHistory();
            history.setSenderPan(senderPan);
            history.setRecipientPan(recipientPan);
            history.setAmount(amount);
            Optional<Cards> sender = cardsRepository.findByPan(senderPan);
            Cards senderCard = sender.get();
            Optional<Cards> recipient = cardsRepository.findByPan(recipientPan);
            Cards recipientCard = recipient.get();
            Long commission = amount / 100;
            history.setCommission(commission);
            Long totalAmount = amount + commission;
            history.setTotalAmount(totalAmount);
            history.setUser_uuid(senderCard.getUserUUID());
            System.out.println("senderPan : " + senderPan);
            System.out.println("recipientPan : " + recipientPan);
            System.out.println("sender balance : " + senderCard.getBalance());
            System.out.println("total amount : " + totalAmount);
            System.out.println("amount : " + amount);
            if (totalAmount < senderCard.getBalance()) {
                senderCard.setBalance(senderCard.getBalance() - totalAmount);
                recipientCard.setBalance(recipientCard.getBalance() + amount);
                transactionRepository.save(history);
                return ResponseEntity.ok("The TransactionService Was Completed Successfully");
            } else {
                throw new Exception("! The Balance Of the Card Not Enough For TransactionService !");
            }
        } else {
            throw new Exception("Sender Card or Recipient Card Not Found");
        }
    }
}
