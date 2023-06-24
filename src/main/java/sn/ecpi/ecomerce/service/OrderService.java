package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import sn.ecpi.ecomerce.entite.Order;
import sn.ecpi.ecomerce.repos.OrderRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class OrderService {

    public final OrderRepos orderRepos;

    public List<Order> getAllOrders(){
        return orderRepos.findAll();
    }

    public Order findByUuid(UUID uuid){
        Optional<Order> result = orderRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Order not Found");
        }


    }

    public  Order createOrder(Order order){
        return orderRepos.save(order);
    }

    public Order updateOrder(UUID uuid, Order orderResquest){
        Order order = orderRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Order not found"));
        order.setClient(orderResquest.getClient());
        order.setDate(orderResquest.getDate());
        order.setPosition(orderResquest.getPosition());
        order.setProduit(orderResquest.getProduit());
        return orderRepos.save(order);
    }

    public void deleteOrder(UUID uuid){
        Order order = orderRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Order not found"));
        orderRepos.delete(order);
    }


}
