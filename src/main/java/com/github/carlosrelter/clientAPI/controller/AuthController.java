package com.github.carlosrelter.clientAPI.controller;

import com.github.carlosrelter.clientAPI.config.security.TokenService;
import com.github.carlosrelter.clientAPI.controller.dto.LoginRequestDTO;
import com.github.carlosrelter.clientAPI.controller.dto.RegisterDTO;
import com.github.carlosrelter.clientAPI.controller.dto.ResponseDTO;
import com.github.carlosrelter.clientAPI.model.User;
import com.github.carlosrelter.clientAPI.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Authentication")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO login){
        User user = this.repository
                .findByEmail(login.email())
                .orElseThrow(()-> new RuntimeException());
        if(passwordEncoder.matches(login.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO register){
        Optional<User> user = this.repository.findByEmail(register.email());

        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(register.password()));
            newUser.setEmail(register.email());
            newUser.setName(register.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


}
