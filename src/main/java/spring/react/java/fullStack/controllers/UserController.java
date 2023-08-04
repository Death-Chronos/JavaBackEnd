package spring.react.java.fullStack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.react.java.fullStack.DTOs.UserDTO;
import spring.react.java.fullStack.models.User;
import spring.react.java.fullStack.repositorys.UserRepository;
import spring.react.java.fullStack.exceptions.UserNotFoundException;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    ResponseEntity<User> newUser(@RequestBody @Valid UserDTO newUserDTO) {
        User user = new User();
        BeanUtils.copyProperties(newUserDTO, user); // Faz a transformação DTO para Usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/user/{id}")
    ResponseEntity<Object> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado"); // também podemos usar throw new UserNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());

    }
}
