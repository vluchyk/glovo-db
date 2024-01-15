package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;

import java.util.stream.Collectors;

public class CustomerConverter {
    public static CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirstName())
                .middleName(customerEntity.getMiddleName())
                .lastName(customerEntity.getLastName())
                .phone(customerEntity.getPhone())
                .registrationAddress(AddressConverter.toDto(customerEntity.getRegistrationAddress()))
                .deliveryAddresses(customerEntity.getDeliveryAddresses().stream().map(AddressConverter::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static CustomerEntity toEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .middleName(customerDto.getMiddleName())
                .lastName(customerDto.getLastName())
                .phone(customerDto.getPhone())
                .registrationAddress(AddressConverter.toEntity(customerDto.getRegistrationAddress()))
                .deliveryAddresses(customerDto.getDeliveryAddresses().stream().map(AddressConverter::toEntity).collect(Collectors.toSet()))
                .build();
    }
}