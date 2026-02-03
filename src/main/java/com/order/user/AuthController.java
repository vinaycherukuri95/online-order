package com.order.user;

import com.order.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (repo.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }
        repo.save(user);
        return ResponseEntity.ok("Registration Successful");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User loggedUser = service.login(user.getEmail(), user.getPassword());
        if(loggedUser != null){
            return ResponseEntity.ok("Login Successful");
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        }

    }
}
