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
public class CardsRequestDto {
    String pan;
    Long balance;
    UUID user_uuid;
}
