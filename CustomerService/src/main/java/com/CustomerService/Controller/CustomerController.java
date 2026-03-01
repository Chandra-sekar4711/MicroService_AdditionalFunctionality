package com.CustomerService.Controller;


import com.CustomerService.Model.CustomerModel;
import com.CustomerService.Serviceimpl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/customer")
public class CustomerController {

     @Autowired
     final CustomerService customerservice;

    public CustomerController(CustomerService customerservice)
    {
        this.customerservice = customerservice;
    }

    @PostMapping("/CreateCustomer")
    public CustomerModel CreateCustomer(@RequestBody CustomerModel custobj)
    {
        System.out.println("**********   ENTER Customer Service *********");
        return customerservice.savecustomer(custobj);

    }

    @GetMapping("/getcustomer/{id}")
    public CustomerModel getcustomerByid(@PathVariable Integer id)
    {
        System.out.println("**********   ENTER Customer Service *********");

        return customerservice.getcustomer(id);
    }

    @GetMapping("/simulateretry/{id}")
    CustomerModel simulateretry(Integer id) throws InterruptedException {
        return customerservice.retrycustomercheck(id);
    }
}
