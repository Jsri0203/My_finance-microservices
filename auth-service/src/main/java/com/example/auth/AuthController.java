package com.example.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final String secret = "verysecretkey_for_demo_purposes_only_change_in_prod";
    @PostMapping("/token")
    public Map<String, String> token(@RequestBody AuthRequest req){
        // WARNING: demo-only. Use proper password hashing & user store in real projects.
        if ("demo".equals(req.username()) && "demo".equals(req.password())){
            String jwt = Jwts.builder()
                    .setSubject(req.username())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                    .compact();
            return Map.of("token", jwt);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public record AuthRequest(String username, String password) {}
}
