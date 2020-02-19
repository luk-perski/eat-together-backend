package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
