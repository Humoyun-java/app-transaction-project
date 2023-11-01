package humoyun.olimjonov.appclickhumoyun.dto.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEditDto {
    Integer password;
    UUID card_uuid;
}
