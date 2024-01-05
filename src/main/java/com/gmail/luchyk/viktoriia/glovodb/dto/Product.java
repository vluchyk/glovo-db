package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String name;
    private double cost;
    private Order order;
}