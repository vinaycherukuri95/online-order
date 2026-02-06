package com.order.card;

import com.order.food.Food;
import com.order.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private FoodRepository foodRepo;

    public Cart addToCart(Long userId, Long foodId, int qty) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(userId);
                    return c;
                });

        Food food = foodRepo.findById(foodId).orElseThrow();

        Optional<CartItem> existing = cart.getItems()
                .stream()
                .filter(i -> i.getFood().getId().equals(foodId))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + qty);
        } else {
            CartItem item = new CartItem();
            item.setFood(food);
            item.setQuantity(qty);
            item.setPrice(food.getPrice());
            cart.getItems().add(item);
        }

        return cartRepo.save(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepo.findByUserId(userId).orElse(null);
    }

    public void removeItem(Long cartItemId) {
        cartRepo.findAll().forEach(cart ->
                cart.getItems().removeIf(i -> i.getId().equals(cartItemId))
        );
    }
}
