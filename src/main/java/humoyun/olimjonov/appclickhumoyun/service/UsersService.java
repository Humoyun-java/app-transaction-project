package humoyun.olimjonov.appclickhumoyun.service;

import humoyun.olimjonov.appclickhumoyun.dto.user.UserEditReq;
import humoyun.olimjonov.appclickhumoyun.dto.user.UsersRequestDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UsersResponseDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UuidDto;
import humoyun.olimjonov.appclickhumoyun.entity.Role;
import humoyun.olimjonov.appclickhumoyun.entity.Status;
import humoyun.olimjonov.appclickhumoyun.entity.Users;
import humoyun.olimjonov.appclickhumoyun.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository repository;

    public ResponseEntity<Users> createUser(UsersRequestDto dto) throws Throwable {
        boolean b = repository.existsByPhoneNumber(dto.getPhoneNumber());
        if (b) throw new Throwable("This phone number already used");

        return ResponseEntity.ok(repository.save(mapToEntity(dto)));
    }

    public List<Users> readAll() {
        return repository.findAll();
    }

    public Optional<Users> findById(UUID uuid) {
        if (repository.existsByUuid(uuid)) {
            return repository.findByUuid(uuid);
        } else {
            return Optional.empty();
        }
    }


    public ResponseEntity<?> deleteUser(UuidDto uuid) {
        Optional<Users> byUuid = repository.findByUuid(uuid.getUuid());
        if (byUuid.isEmpty()) return ResponseEntity.status(404).body("User Not Found");
        repository.delete(byUuid.get());
        return ResponseEntity.ok("DELETED");
    }

    public ResponseEntity<?> edit(UserEditReq dto) throws Exception {
        Optional<Users> byUuid = repository.findByUuid(dto.getUuid());
        if (byUuid.isEmpty()) throw new Exception("User Not Found");
        Users users = byUuid.get();
        users.setUuid(dto.getUuid());
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
        users.setAge(dto.getAge());
        repository.save(users);
        return ResponseEntity.ok(users);
    }

    private Users mapToEntity(UsersRequestDto dto) {
        return Users.builder().username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .phoneNumber(dto.getPhoneNumber())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .password(passwordEncoder()
                        .encode(dto.getPassword()))
                .uuid(UUID.randomUUID()).build();
    }

    private UsersResponseDto mapToDto(Users user) {
        return UsersResponseDto.builder()
//                .uuid(user.getUuid())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber()).build();
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
