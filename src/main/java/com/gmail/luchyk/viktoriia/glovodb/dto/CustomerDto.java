package com.gmail.luchyk.viktoriia.glovodb.dto;

import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
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
    private AddressEntity registrationAddress;
    private Set<AddressEntity> deliveryAddresses = new HashSet<>();
}