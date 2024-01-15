package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int id;
    private int number;
    private List<ProductDto> products = new ArrayList<>();
    private CustomerDto customer;
}