package com.example.Cloud.Spanner.controller;

import com.example.Cloud.Spanner.model.Order;
import com.example.Cloud.Spanner.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

// Spring Cloud Spanner Docs and details: https://codelabs.developers.google.com/codelabs/cloud-spring-spanner#0

@RestController
@RequestMapping("/home")
public class SpannerController {

    private final OrderRepository orderRepository;

    @Autowired
    public SpannerController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        if(order.getOrder_id() == null || order.getOrder_id().isEmpty())
            order.setOrder_id(UUID.randomUUID().toString());
        if(order.getCreation_timestamp() == null)
            order.setCreation_timestamp(LocalDateTime.now());

        order.getOrderItems().forEach(orderItem -> {
            if(orderItem.getOrder_item_id().isEmpty())
                orderItem.setOrder_item_id(UUID.randomUUID().toString());
            if(orderItem.getOrder_id().isEmpty())
                orderItem.setOrder_id(order.getOrder_id());
        });
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable("id") String id){
       return orderRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Id ="+id));
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("id") String orderId){
        orderRepository.deleteById(orderId);
        return "Deletion successful";
    }

    @PutMapping("/updateOrder/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order){
        deleteOrder(id);
        order.setOrder_id(id);
        createOrder(order);
        return order;
    }

}
