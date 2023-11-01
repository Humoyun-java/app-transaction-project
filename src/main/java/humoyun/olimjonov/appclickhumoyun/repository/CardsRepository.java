package humoyun.olimjonov.appclickhumoyun.repository;

import humoyun.olimjonov.appclickhumoyun.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByPan(String pan);

    boolean existsByPan(String pan);

    Optional<Cards> findByCardUUID(UUID cardUUID);

    boolean existsByCardUUID(UUID cardUUID);

    @Query(value = "SELECT c.balance, c.pan FROM Cards c  where c.userUUID = ?1")
    List<Cards> findByUserUUID(UUID user_uuid);
}
