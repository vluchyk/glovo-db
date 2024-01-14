package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;

public class AddressConverter {
    public static AddressDto toDto(AddressEntity addressEntity) {
        return AddressDto.builder()
                .id(addressEntity.getId())
                .street(addressEntity.getStreet())
                .number(addressEntity.getNumber())
                .apartmentNumber(addressEntity.getApartmentNumber())
                .build();
    }

    public static AddressEntity toEntity(AddressDto addressDto) {
        return AddressEntity.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .apartmentNumber(addressDto.getApartmentNumber())
                .build();
    }
}
