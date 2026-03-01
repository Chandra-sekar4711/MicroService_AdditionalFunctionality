package com.OrderService.Serviceimpl;

import com.OrderService.FeignClient.CustomerFeign;
import com.OrderService.FeignClient.PaymentFeign;
import com.OrderService.Model.OrderModel;
import com.OrderService.Repository.OrderRepository;
import com.OrderService.SpringAOPProxy.CustomerService;
import com.OrderService.SpringAOPProxy.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.DTO.CustomerModel.CustomerModel;
import org.example.DTO.Payment.PaymentModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceimpl {


    OrderRepository orderrepo;
    CustomerFeign customerfeign;
    PaymentFeign paymentfeign;
    CustomerService customerService;
     PaymentService paymentService;
    public OrderServiceimpl(OrderRepository orderrepo, CustomerFeign customerfeign, PaymentFeign paymentfeign,CustomerService customerService,PaymentService paymentService)
    {
          this.orderrepo = orderrepo;
          this.customerfeign = customerfeign;
          this.paymentfeign = paymentfeign;
          this.customerService=customerService;
          this.paymentService=paymentService;
    }


    //INTERVIEW GOLDS
    //===================
    //name should match with config each circuitbreaker have diff config
    //Dont make the method try catch because exceepet is not proage to fallback it can hold by the catch block itself
    //**** IMPORTANT //Fallback return Type should be same as the methods return type //
    //We seperate the method into another class then only the Spring AOP create a proxy then only circuit breaker works

    public OrderModel placeorder(OrderModel obj) {

        System.out.println("***** ENTER placeorder *****");

        // ✅ Customer service call (CB protected)
        CustomerModel customer =
                customerService.getCustomerById(obj.getCustomerId());

        // ✅ Payment service call (CB protected)
        PaymentModel payment =
                paymentService.paymentStatus(Integer.valueOf(obj.getOrderNumber()));

        // Business logic continues even if services are DOWN
        obj.setOrderNumber(customer.getName()+obj.getOrderNumber());
        obj.setStatus(payment.getPaymentStatus());
        obj.setNotification_status(payment.getNotificationStatus());

        return orderrepo.save(obj);
    }



    //the below one is not worked because they are in the same class

//    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "customerFallback")
//    public CustomerModel getcustomerByid(OrderModel obj)
//    {
//        return customerfeign.getcustomerByid(obj.getCustomerId());
//    }
//
//    @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
//    public PaymentModel paymentstatus(OrderModel obj)
//    {
//
//        return paymentfeign.paymentstatus(obj.getOrderNumber());
//    }



    public OrderModel retrycheck() {

        CustomerModel customer = customerService.customerretry_chceck(1);
        OrderModel obj = new OrderModel();
        obj.setId(0);
        obj.setOrderNumber(customer.getName());
        obj.setTotalAmount(BigDecimal.valueOf(0));
        obj.setStatus(customer.getName());
        obj.setNotification_status(customer.getName());
        return obj;
    }
}
