package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.perski.eat.together.database.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
}
