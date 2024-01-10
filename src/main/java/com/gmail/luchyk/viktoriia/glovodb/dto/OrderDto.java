package com.gmail.luchyk.viktoriia.glovodb.dto;

import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
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
    private List<ProductEntity> products = new ArrayList<>();
    private CustomerEntity customer;
}