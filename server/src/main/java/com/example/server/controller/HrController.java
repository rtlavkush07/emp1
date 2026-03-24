


package com.example.server.controller;

import com.example.server.model.*;
import com.example.server.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hr")
// @CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class HrController {

    @Autowired
    private UserService service;

    // simple get all users without pagination and search
    @GetMapping("/getAllUsers")
    public List<User> getAll() {

        return (List<User>) service.getAll();
    }

    // Pagination + Search
    @GetMapping("/getAllUser")
    public Page<User> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search) {

        return service.getAll(page, size, search);
    }

    // Role-based
    @GetMapping("/byRole/{role}")
    public List<User> getByRole(@PathVariable Role role,
            @RequestParam(required = false) Long userId) {

        return (List<User>) service.getByRole(role, userId);
    }

    @PostMapping("/addUser")
    public User create(@RequestBody User emp) {
        return service.create(emp);
    }

    @PutMapping("/updateUser/{id}")
    public User update(@PathVariable Long id, @RequestBody User emp) {
        return service.update(id, emp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
}