package com.OrderService.Controller;

import com.OrderService.Model.OrderModel;
import com.OrderService.Serviceimpl.OrderServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderServiceimpl orderserviceimpl;

    @PostMapping("/placeorder")
    public OrderModel createOrder(@RequestBody OrderModel obj)
    {
        log.info("ENTER Order Service");

        OrderModel res =  orderserviceimpl.placeorder(obj);
        log.info("Finished placeorder, orderNumber={}", res.getOrderNumber());
        return res;

    }

    @GetMapping("/retry/check")
    public OrderModel createOrder()
    {
        log.info("ENTER Retry CHECK");
        OrderModel res =  orderserviceimpl.retrycheck();
        log.info("Finished RETRY CHECK, orderNumber={}", res.getOrderNumber());
        return res;

    }
}
