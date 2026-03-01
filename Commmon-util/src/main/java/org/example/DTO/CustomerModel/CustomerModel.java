package org.example.DTO.CustomerModel;


import lombok.Data;

@Data
public class CustomerModel {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private boolean active = true;
}
