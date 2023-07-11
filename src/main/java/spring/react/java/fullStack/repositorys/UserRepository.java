package spring.react.java.fullStack.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.react.java.fullStack.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
