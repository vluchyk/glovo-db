package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.Address;
import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;

public class AddressConverter {
    public static Address toAddress(AddressEntity addressEntity) {
        return Address.builder()
                .street(addressEntity.getStreet())
                .number(addressEntity.getNumber())
                .apartmentNumber(addressEntity.getApartmentNumber())
                .build();
    }

    public static AddressEntity toAddressEntity(Address address) {
        return AddressEntity.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .apartmentNumber(address.getApartmentNumber())
                .build();
    }
}
