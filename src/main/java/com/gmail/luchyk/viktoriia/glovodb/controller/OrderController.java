package com.gmail.luchyk.viktoriia.glovodb.controller;

import com.gmail.luchyk.viktoriia.glovodb.dto.Address;
import com.gmail.luchyk.viktoriia.glovodb.dto.Customer;
import com.gmail.luchyk.viktoriia.glovodb.dto.Order;
import com.gmail.luchyk.viktoriia.glovodb.dto.Product;
import com.gmail.luchyk.viktoriia.glovodb.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order get(@PathVariable int id) {
        return orderService.get(id);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable int id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    @PutMapping("/{id}/products/{productName}")
    public Order update(@PathVariable int id, @PathVariable String productName, @RequestBody Product product) {
        return orderService.update(id, productName, product);
    }

    @PutMapping("/{id}/customer")
    public Order update(@PathVariable int id, @RequestBody Customer customer) {
        return orderService.updateCustomer(id, customer);
    }

    @PutMapping("/{id}/address")
    public Order update(@PathVariable int id, @RequestBody Address address) {
        return orderService.update(id, address);
    }

    @PatchMapping("/{id}")
    public Order patch(@PathVariable int id, @RequestBody Product product) {
        return orderService.patch(id, product);
    }

    @DeleteMapping("/{id}/products/{productName}")
    public Order delete(@PathVariable int id, @PathVariable String productName) {
        return orderService.delete(id, productName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }
}
