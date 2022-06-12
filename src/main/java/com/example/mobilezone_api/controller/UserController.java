package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.ColorDTO;
import com.example.mobilezone_api.dto.UserDTO;
import com.example.mobilezone_api.model.User;
import com.example.mobilezone_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/")
    public User getUserByEmail(@RequestParam(required = false) String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public User create(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
