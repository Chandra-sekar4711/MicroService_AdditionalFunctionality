package com.OrderService.Controller;

import com.OrderService.Model.OrderModel;
import com.OrderService.Serviceimpl.OrderServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceimpl orderserviceimpl;

    @PostMapping("/placeorder")
    public OrderModel createOrder(@RequestBody OrderModel obj)
    {
        System.out.println("**********   ENTER Order Service *********");

        OrderModel res =  orderserviceimpl.placeorder(obj);
        System.out.println("**********   Finished  "+res.getOrderNumber());
        return res;

    }

    @GetMapping("/retry/check")
    public OrderModel createOrder()
    {
        System.out.println("**********   ENTER Retry CHECK *********");
        OrderModel res =  orderserviceimpl.retrycheck();
        System.out.println("**********   Finished RETRY CHECK "+res.getOrderNumber());
        return res;

    }
}
