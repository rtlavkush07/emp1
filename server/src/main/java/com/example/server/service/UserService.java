package com.example.server.service;

import com.example.server.model.Role;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

// ======================================================================


// for new user creation

    public User create(User emp) {
        return repo.save(emp);
    }
// ======================================================================
    // reading data from database
    // simple get all users without pagination and search
    public List<User> getAll() {
        return (List<User>) repo.findAll();
    }

    // Pagination + Search
    public Page<User> getAll(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName"));

        if (search != null && !search.isEmpty()) {
            return repo
                    .findByEmpIdContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                            search, search, search, search, search, pageable);
        }

        return repo.findAll(pageable);
    }
    

    // get by role
    public List<User> getByRole(Role role, Long userId) {

    // HR → all users
    if (role == Role.HR) {
        return repo.findAll();
    }

    // Manager → all employees
    else if (role == Role.MANAGER) {
        return repo.findByRole(Role.EMPLOYEE);
    }

    // Employee → all employees
    else if (role == Role.EMPLOYEE) {
        return repo.findByRole(Role.EMPLOYEE);
    }

    // fallback
    return Collections.emptyList();
}
// ======================================================================


    // user update
    public User update(Long id, User emp) {
        // find then okay otherswise error return karo
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(emp.getFirstName());
        user.setLastName(emp.getLastName());
        user.setEmail(emp.getEmail());
        user.setDob(emp.getDob());
        user.setLocation(emp.getLocation());
        user.setContactNumber(emp.getContactNumber());
        user.setRole(emp.getRole());

        return repo.save(user);
    }
// ======================================================================

    // delete logic
    public void delete(Long id) {

        User emp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Deleting user: " + emp.getFirstName() + " " + emp.getLastName());
        repo.delete(emp);
    }


// ======================================================================


}