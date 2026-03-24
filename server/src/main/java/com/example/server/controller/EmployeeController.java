package com.example.server.controller;

import com.example.server.model.*;
import com.example.server.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
// @CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private UserService service;

    // simple get all users without pagination and search
    @GetMapping("/getAllUsers")
    public List<User> getAll() {

        return (List<User>) service.getAll();
    }

    // Pagination + Search
    @GetMapping("/{id}")
    public Page<User> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search) {

        return service.getAll(page, size, search);
    }

    @PostMapping("/addUser")
    public User create(@RequestBody User emp) {
        return service.create(emp);
    }

    @PutMapping("/updateUser/{id}")
    public User update(@PathVariable Long id, @RequestBody User emp) {
        return service.update(id, emp);
    }

    // 5️⃣ Delete Employee
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Employee deleted successfully";
    }

}