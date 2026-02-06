package com.order.order;

import com.order.card.CartRepository;
import com.order.card.Cart;
import com.order.card.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    public Order placeOrder(Long userId) {

        Cart cart = cartRepo.findByUserId(userId).orElseThrow();
        System.out.println(cart);
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("PLACED");

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setFoodName(ci.getFood().getName());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getPrice());

            total += ci.getPrice() * ci.getQuantity();
            items.add(oi);
        }

        order.setItems(items);
        order.setTotal(total);

        cart.getItems().clear(); // clear cart after order
        cartRepo.save(cart);

        return orderRepo.save(order);
    }

    public List<Order> getOrders(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}

