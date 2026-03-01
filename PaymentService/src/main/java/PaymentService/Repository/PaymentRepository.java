package PaymentService.Repository;

import PaymentService.Model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentModel,Integer> {
}
