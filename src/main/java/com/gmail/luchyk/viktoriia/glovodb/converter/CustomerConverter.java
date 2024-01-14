package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;

public class CustomerConverter {
    public static CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirstName())
                .middleName(customerEntity.getMiddleName())
                .lastName(customerEntity.getLastName())
                .phone(customerEntity.getPhone())
                .registrationAddress(customerEntity.getRegistrationAddress())
                .deliveryAddresses(customerEntity.getDeliveryAddresses())
                .build();
    }

    public static CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .middleName(customerDto.getMiddleName())
                .lastName(customerDto.getLastName())
                .phone(customerDto.getPhone())
                .registrationAddress(customerDto.getRegistrationAddress())
                .deliveryAddresses(customerDto.getDeliveryAddresses())
                .build();
    }
}