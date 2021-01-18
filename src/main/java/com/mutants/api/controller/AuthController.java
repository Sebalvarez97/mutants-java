package com.mutants.api.controller;

import com.mutants.api.dto.auth.AuthRequest;
import com.mutants.api.dto.auth.AuthResponse;
import com.mutants.api.service.auth.UserService;
import com.mutants.api.util.JWTUtil;
import com.mutants.api.util.PBKDF2Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired private JWTUtil jwtUtil;

  @Autowired private PBKDF2Encoder passwordEncoder;

  @Autowired private UserService userService;

  @PostMapping("/login")
  public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
    return userService
        .findByUsername(ar.getUsername())
        .map(
            (userDetails) -> {
              if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                String token = jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(
                    new AuthResponse(token, jwtUtil.getExpirationDateFromToken(token)));
              } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
              }
            })
        .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }
}
