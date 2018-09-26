package br.com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.app.model.user.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findOneByUserId(String userId);
    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}

