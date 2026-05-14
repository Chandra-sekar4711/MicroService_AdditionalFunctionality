package com.OrderService.SpringAOPProxy;

import com.OrderService.FeignClient.PaymentFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.DTO.Payment.PaymentModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {
    int attempt =1;
    private final PaymentFeign paymentFeign;

    public PaymentService(PaymentFeign paymentFeign) {
        this.paymentFeign = paymentFeign;
    }

   @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
    public PaymentModel paymentStatus(Integer orderNumber) {
        System.out.println("****************** RETRY PAYMENT method called "+attempt++ +" times "+" at "+new Date());

        return paymentFeign.paymentstatus(String.valueOf(orderNumber));
    }

    public PaymentModel paymentFallback(Integer orderNumber, Exception e) {

        System.out.println("🔥 PAYMENT FALLBACK TRIGGERED");

        PaymentModel fallback = new PaymentModel();
        fallback.setPaymentStatus("PAYMENT_FALLBACK");
        fallback.setNotificationStatus("PAYMENT_FALLBACK");

        return fallback;
    }
}