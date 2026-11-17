package com.klu.springmvc.service; 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.springmvc.model.User;
import com.klu.springmvc.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	JWTService jwts;
    @Autowired
    UserRepository repo;
    public Object signupservice(User u) {
        Map<String, Object> response = new HashMap<>();

        try {
            User user = repo.findByUsername(u.getUsername());

            if (user != null) {
                response.put("code", 501);
                response.put("message", "The user is already registered");
            } else {
                u.setRole(1);
                repo.save(u);

                response.put("code", 200);
                response.put("message", "User Registration is success");
            }

            return response;
        }

        catch (Exception e) {
            response.put("code", 501);
            response.put("message", e.getMessage());
            return response;
        }
    }

    public Object signinservice(Map<String, String> u) {
        Map<String, Object> response = new HashMap<>();

        try {
            User user = repo.findByUsername(u.get("username"));

            if (user == null || !user.getPassword().equals(u.get("password"))) {

                response.put("code", "501");
                response.put("message", "Authentication Failed");

            } else {

                String token = jwts.generateJWT(
                        u,
                        user.getRole().toString(),
                        user.getId()
                );

                response.put("code", 200);
                response.put("JWT Token", token);
                response.put("message", "Authentication success");
            }

            return response;

        } catch (Exception e) {
            response.put("code", 501);
            response.put("message", e.getMessage());
            return response;
        }
    }
}