package spring.react.java.fullStack.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jakarta.validation.Valid;
import spring.react.java.fullStack.DTOs.ErroDTO;
import spring.react.java.fullStack.DTOs.UserDTO;
import spring.react.java.fullStack.exceptions.UserNotFoundException;
import spring.react.java.fullStack.models.User;
import spring.react.java.fullStack.repositorys.UserRepository;

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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        // final var gson = new Gson();
        // ErroDTO erro = new ErroDTO("Usuario não encontrado.");
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(erro));

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PutMapping("user/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        BeanUtils.copyProperties(userDTO, user);

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        
        
        userRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário com o id: "+ id +" foi deletado com sucesso.");
    }
}
