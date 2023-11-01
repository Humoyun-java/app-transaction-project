package humoyun.olimjonov.appclickhumoyun.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UsersRequestDto {
    String username;
    String firstName;
    String lastName;
    Integer age;
    String phoneNumber;
    String password;
}