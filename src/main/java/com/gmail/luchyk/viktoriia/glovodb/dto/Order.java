package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private int id;
    private int number;
    private Customer customer;
    private Address address;
}
