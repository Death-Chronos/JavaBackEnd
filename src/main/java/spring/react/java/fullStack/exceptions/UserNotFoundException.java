package spring.react.java.fullStack.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException( Long id){
        super("Usuario não encontrado com o id: " +id);
        
    }

}
