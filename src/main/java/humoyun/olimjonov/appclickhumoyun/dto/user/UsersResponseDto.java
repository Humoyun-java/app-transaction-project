package humoyun.olimjonov.appclickhumoyun.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UsersResponseDto {
    //UUID uuid;
    String username;
    String firstName;
    String lastName;
    Integer age;
    String phoneNumber;
    String password;
}
