package humoyun.olimjonov.appclickhumoyun.repository;

import humoyun.olimjonov.appclickhumoyun.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Users> findByUuid(UUID uuid);


    Optional<Users> findByUsername(String user_name);


    boolean existsByUuid(UUID uuid);

}
