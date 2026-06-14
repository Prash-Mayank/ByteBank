package com.bytebank.controller;

import com.bytebank.model.User;
import com.bytebank.security.JwtUtil;
import com.bytebank.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already in use."));
        }
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(Map.of(
            "message", "User registered successfully.",
            "systemId", registeredUser.getSystemId()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletResponse response) {
        String systemId = request.get("systemId");
        String password = request.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(systemId, password)
            );

            if (authentication.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(systemId);
                String jwtToken = jwtUtil.generateToken(userDetails);

                // Store JWT in HttpOnly cookie to secure against XSS
                Cookie cookie = new Cookie("JWT-TOKEN", jwtToken);
                cookie.setHttpOnly(true);
                cookie.setSecure(false); // Set to true if deploying with HTTPS
                cookie.setPath("/");
                cookie.setMaxAge(3600); // 1 hour expiration
                response.addCookie(cookie);

                Optional<User> userOpt = userService.findById(systemId);
                String role = userOpt.map(User::getRole).orElse("CUS");

                return ResponseEntity.ok(Map.of(
                    "message", "Login successful.",
                    "systemId", systemId,
                    "role", role,
                    "token", jwtToken
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials or account locked."));
        }
        return ResponseEntity.status(401).body(Map.of("message", "Authentication failed."));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logged out successfully."));
    }
}
