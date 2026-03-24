package com.example.server.service;

import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    public User register(User user) {
        return repo.save(user);
    }

//     1. Username se user fetch
// 2. Agar nahi mila → error
// 3. Password match karo
// 4. Match → success
// 5. Wrong → error
   public Map<String, Object> login(String username, String password,String email) {

      User user = repo.findByUsername(username).orElseGet(()->repo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found")));

    
    if (!user.getPassword().equals(password)) {
        throw new RuntimeException("Invalid Credentials");
    }

    return Map.of(
            "message", "Login successful",
            "id", user.getId(),
            "firstName", user.getFirstName(),
            "lastName", user.getLastName(),
            "username", user.getUsername(),
            "role", user.getRole());
}




}
