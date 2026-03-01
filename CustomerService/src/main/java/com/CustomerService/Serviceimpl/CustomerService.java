package com.CustomerService.Serviceimpl;

import com.CustomerService.Model.CustomerModel;
import com.CustomerService.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo repo;

    public CustomerModel savecustomer(CustomerModel custobj) {

        return repo.save(custobj);
    }

    public CustomerModel getcustomer(Integer id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("Customer id not found"));
    }

    public CustomerModel retrycustomercheck(Integer id) throws InterruptedException {
        Thread.sleep(2000);
        return repo.findById(id).orElseThrow(()->new RuntimeException("Customer id not found"));

    }
}
