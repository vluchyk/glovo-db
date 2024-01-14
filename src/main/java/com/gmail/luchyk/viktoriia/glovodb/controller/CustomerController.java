package com.gmail.luchyk.viktoriia.glovodb.controller;

import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable int id) {
        try {
            return customerService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        try {
            return customerService.update(customerDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public CustomerDto patch(@PathVariable int id, @RequestBody AddressDto addressDto) {
        try {
            return customerService.patch(id, addressDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public CustomerDto deleteDeliveryAddress(@PathVariable int id, @RequestBody AddressDto addressDto) {
        try {
            return customerService.deleteDeliveryAddress(id, addressDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody CustomerDto customerDto) {
        try {
            customerService.delete(customerDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
