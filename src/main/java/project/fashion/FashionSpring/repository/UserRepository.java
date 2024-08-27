package project.fashion.FashionSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.fashion.FashionSpring.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);

    User findByUsername(String username);
}
