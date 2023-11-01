package humoyun.olimjonov.appclickhumoyun.repository;

import humoyun.olimjonov.appclickhumoyun.entity.Cards;
import humoyun.olimjonov.appclickhumoyun.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, Long> {
    
}