package com.gmail.luchyk.viktoriia.glovodb.controller;

import com.gmail.luchyk.viktoriia.glovodb.dto.OrderDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable int id) {
        try {
            return orderService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PutMapping
    public OrderDto update(@RequestBody OrderDto orderDto) {
        try {
            return orderService.update(orderDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public OrderDto patch(@PathVariable int id, @RequestBody ProductDto productDto) {
        try {
            return orderService.patch(id, productDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public OrderDto delete(@PathVariable int id, @RequestBody ProductDto productDto) {
        try {
            return orderService.delete(id, productDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody OrderDto orderDto) {
        try {
            orderService.delete(orderDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
