package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
