package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private AddressDto registrationAddress;
    private Set<AddressDto> deliveryAddresses = new HashSet<>();
}