package com.example.jwt.Controllers;

import com.example.jwt.Jwtutils.Createjwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class auth {
    @Autowired
    private  Createjwt createjwt;

    @GetMapping("/data")
    public String getauthdata(){
        System.out.println("Hello");
        return "Hellop";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Hardcoded check for demo
        if ("sabari".equals(username) && "123".equals(password)) {
            return createjwt.generatejwt(username, "USER");
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }


}
