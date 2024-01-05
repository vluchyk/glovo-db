package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String number;
    private int apartmentNumber;
}