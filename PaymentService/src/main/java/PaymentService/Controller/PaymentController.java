package PaymentService.Controller;

import PaymentService.Model.PaymentModel;
import PaymentService.Serviceimpl.PaymentServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    PaymentServiceimpl paymentservice;

    @GetMapping("/paymentstatus/{id}")
    public PaymentModel paymentstatus(@PathVariable String id)
    {
        log.info("ENTER Payment Service, id={}", id);

        PaymentModel res =  paymentservice.processpayment(id);
        log.info("Payment status={}", res.getPaymentStatus());

        return res;
    }

}
