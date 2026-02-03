package com.order.food;

import com.order.dto.Food;
import com.order.dto.Restaurant;
import com.order.food.repository.FoodRepository;
import com.order.food.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private FoodRepository foodRepo;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    @GetMapping("/foods/{restaurantId}")
    public List<Food> getFoodsByRestaurant(@PathVariable Long restaurantId) {
        return foodRepo.findByRestaurantId(restaurantId);
    }

    @PostMapping("/addFood")
    public Food uploadFood(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("restaurantId") Long restaurantId,
            @RequestParam("image") MultipartFile image) throws IOException {

        // Save image to external folder
        String folder = "src/main/resources/static/images/";
        Files.createDirectories(Paths.get(folder));
        String filename = System.currentTimeMillis() + "-" + name;
        Path filePath = Paths.get(folder + filename);
        Files.write(filePath, image.getBytes());
        
        
        // Save Food in DB
        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setImageUrl("/images/" + filename);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        food.setRestaurant(restaurant);

        return foodRepo.save(food);
    }
}
