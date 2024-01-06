package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {
    private int number;
    private List<Product> products;
    private Customer customer;
    private Address address;
}
