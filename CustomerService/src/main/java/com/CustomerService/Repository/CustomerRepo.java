package com.CustomerService.Repository;

import com.CustomerService.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {
}
