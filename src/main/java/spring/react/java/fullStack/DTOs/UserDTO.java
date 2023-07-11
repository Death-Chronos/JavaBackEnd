package spring.react.java.fullStack.DTOs;

import jakarta.validation.constraints.NotBlank;


public record UserDTO(@NotBlank String name, @NotBlank String username, @NotBlank String email) {
    
}
