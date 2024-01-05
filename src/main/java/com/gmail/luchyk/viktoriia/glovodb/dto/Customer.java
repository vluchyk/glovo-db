package com.gmail.luchyk.viktoriia.glovodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String name;
    private String phone;
}
