package com.gmail.luchyk.viktoriia.glovodb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    ADDRESS_NOT_FOUND("The address is not found."),
    CUSTOMER_NOT_FOUND("The customer is not found"),
    PRODUCT_NOT_FOUND("The product is not found"),
    ORDER_NOT_FOUND("The order is not found");

    private final String message;
}