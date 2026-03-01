package PaymentService.Model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Table
@Data
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private String NotificationStatus;
}