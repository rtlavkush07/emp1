package com.example.server.repository;

import com.example.server.model.Role;
import com.example.server.model.User;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

        // for LOGIN METHOD (IMPORTANT) to checkking the user
        Optional<User> findByUsername(String username);
        Optional<User> findByEmail(String username);
        

        // search multiple fields
        Page<User> findByEmpIdContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                        String empId,
                        String firstName,
                        String lastName,
                        String location,
                        String username,
                        Pageable pageable);

        // for manager
        // List<User> findByManagerId(List<Role> manager);
        List<User> findByRole(Role employee);

        
}