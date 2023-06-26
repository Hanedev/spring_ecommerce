package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.OrderDTO;
import sn.ecpi.ecomerce.entite.Order;
import sn.ecpi.ecomerce.pojo.OrderPOJO;
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
    public ResponseEntity<List<OrderPOJO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderPOJO> orderPOJOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderPOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderPOJOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderPOJO> getOrderByUUID(@PathVariable("uuid") UUID uuid) {
        Order order = orderService.findByUuid(uuid);
        if (order != null) {
            OrderPOJO orderPOJO = modelMapper.map(order, OrderPOJO.class);
            return ResponseEntity.ok(orderPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderPOJO> createOrder(@RequestBody OrderDTO orderDTO) {

        Order createdOrder = orderService.createOrder(orderDTO);
        OrderPOJO createdOrderPOJO = modelMapper.map(createdOrder, OrderPOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderPOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrderPOJO> updateOrder(
            @PathVariable("uuid") UUID uuid,
            @RequestBody OrderDTO orderDTO) {

        Order updatedOrder = orderService.updateOrder(uuid, orderDTO);
        if (updatedOrder != null) {
            OrderPOJO updatedOrderPOJO = modelMapper.map(updatedOrder, OrderPOJO.class);
            return ResponseEntity.ok(updatedOrderPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteOrder(@PathVariable("uuid") UUID uuid) {
        orderService.deleteOrder(uuid);
    }
}

