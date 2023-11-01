package humoyun.olimjonov.appclickhumoyun.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    CardType cardType;
    String pan;
    String expire;
    Long balance;
    UUID cardUUID;
    UUID userUUID;

    public Cards(String pan, String expire, Long balance) {
        this.pan = pan;
        this.expire = expire;
        this.balance = balance;
    }
}