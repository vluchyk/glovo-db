package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.Customer;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;

public class CustomerConverter {
    public static Customer toCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .name(customerEntity.getName())
                .phone(customerEntity.getPhone())
                .build();
    }

    public static CustomerEntity toCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .name(customer.getName())
                .phone(customer.getPhone())
                .build();
    }
}
