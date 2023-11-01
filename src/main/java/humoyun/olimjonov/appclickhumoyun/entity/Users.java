package humoyun.olimjonov.appclickhumoyun.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    UUID uuid;

    @Column(unique = true)
    String username;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    Integer age;

    @Column(unique = true)
    String phoneNumber;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'ACTIVE'")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'USER'")
    Role role;
}