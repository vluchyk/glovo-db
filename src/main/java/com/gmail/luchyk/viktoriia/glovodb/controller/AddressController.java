package com.gmail.luchyk.viktoriia.glovodb.controller;

import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private AddressService addressService;

    @GetMapping("/{id}")
    public AddressDto get(@PathVariable int id) {
        try {
            return addressService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PostMapping
    public AddressDto save(@RequestBody AddressDto addressDto) {
        return addressService.save(addressDto);
    }

    @PutMapping
    public AddressDto update(@RequestBody AddressDto addressDto) {
        try {
            return addressService.update(addressDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody AddressDto addressDto) {
        try {
            addressService.delete(addressDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}