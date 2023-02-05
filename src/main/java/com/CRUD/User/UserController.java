package com.CRUD.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest userRequest) {
        try {
            User user = new User();
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(userRequest.getBirtDay());
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setGender(userRequest.getGender());
            user.setBirthDate(birthDate);
            user.setAddress(userRequest.getAddress());
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOneById(@PathVariable("userId") Integer userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable("userId") Integer userId,
            @Valid @RequestBody UserRequest userRequest) {
        try {
            User user = userRepository.findById(userId).get();
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(userRequest.getBirtDay());
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setGender(userRequest.getGender());
            user.setBirthDate(birthDate);
            user.setAddress(userRequest.getAddress());
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteOneById(@PathVariable("userId") Integer userId) {
        try {
            User user = userRepository.findById(userId).get();
            userRepository.delete(user);
            return ResponseEntity.ok().body("berhasil menghapus user ID : " + userId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
