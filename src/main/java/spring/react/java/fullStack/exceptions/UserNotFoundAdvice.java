package spring.react.java.fullStack.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import spring.react.java.fullStack.DTOs.ErroDTO;

import org.springframework.http.HttpStatus;


@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroDTO exceptionHandler(UserNotFoundException exception) {
        ErroDTO erro= new ErroDTO(exception.getMessage());
        return erro;

    }

}
