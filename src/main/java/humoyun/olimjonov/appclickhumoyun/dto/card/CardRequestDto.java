package humoyun.olimjonov.appclickhumoyun.dto.card;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String pan;
    String expire;
    Long balance;
    UUID user_uuid;
}
