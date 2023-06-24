package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.OrderDTO;
import sn.ecpi.ecomerce.entite.Order;
import sn.ecpi.ecomerce.service.OrderService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;

    @Autowired
    public OrderController(ModelMapper modelMapper, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderDTO> getOrderByUUID(@PathVariable("uuid") UUID uuid) {
        Order order = orderService.findByUuid(uuid);
        if (order != null) {
            OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
            return ResponseEntity.ok(orderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order createdOrder = orderService.createOrder(order);
        OrderDTO createdOrderDTO = modelMapper.map(createdOrder, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable("uuid") UUID uuid,
            @RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order updatedOrder = orderService.updateOrder(uuid, order);
        if (updatedOrder != null) {
            OrderDTO updatedOrderDTO = modelMapper.map(updatedOrder, OrderDTO.class);
            return ResponseEntity.ok(updatedOrderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteOrder(@PathVariable("uuid") UUID uuid) {
        orderService.deleteOrder(uuid);
    }
}

