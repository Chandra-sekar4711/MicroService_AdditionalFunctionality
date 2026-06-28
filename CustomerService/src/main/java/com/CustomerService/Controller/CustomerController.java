package com.CustomerService.Controller;


import com.CustomerService.Model.CustomerModel;
import com.CustomerService.Serviceimpl.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController()
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Value("${message}")
    private String message;


    @Autowired
     final CustomerService customerservice;

    public CustomerController(CustomerService customerservice)
    {
        this.customerservice = customerservice;
    }

    @GetMapping("/getData")
    public String getdata()
    {
        return message;
    }

    @PostMapping("/CreateCustomer")
    public CustomerModel CreateCustomer(@RequestBody CustomerModel custobj)
    {
        log.info("ENTER Customer Service");
        return customerservice.savecustomer(custobj);

    }

    @GetMapping("/getcustomer/{id}")
    public CustomerModel getcustomerByid(@PathVariable Integer id)
    {
        log.info("ENTER Customer Service");

        return customerservice.getcustomer(id);
    }

    @GetMapping("/simulateretry/{id}")
    CustomerModel simulateretry(Integer id) throws InterruptedException {
        return customerservice.retrycustomercheck(id);
    }
}
