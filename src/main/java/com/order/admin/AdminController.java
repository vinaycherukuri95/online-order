package com.order.admin;

import com.order.food.Food;
import com.order.food.FoodRepository;
import com.order.food.Restaurant;
import com.order.food.RestaurantRepository;
import com.order.user.User;
import com.order.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    // Add Restaurant
    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    // Add Food
    @PostMapping("/foods")
    public Food addFood(@RequestBody Food food) {
        return foodRepo.save(food);
    }

    // View All Users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}

