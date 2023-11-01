package humoyun.olimjonov.appclickhumoyun.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String senderPan;
    String recipientPan;
    Long amount;
    Long commission;
    Long totalAmount;
    @CreationTimestamp
    LocalDateTime date;
    UUID user_uuid;
}