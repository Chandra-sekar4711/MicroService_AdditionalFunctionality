package PaymentService.Controller;

import PaymentService.Model.PaymentModel;
import PaymentService.Serviceimpl.PaymentServiceimpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentServiceimpl paymentservice;

    @GetMapping("/paymentstatus/{id}")
    public PaymentModel paymentstatus(@PathVariable String id)
    {
        System.out.println("**********   ENTER Payment Service *********");

        PaymentModel res =  paymentservice.processpayment(id);
        System.out.println("**********   "+res.getPaymentStatus());

        return res;
    }

}
