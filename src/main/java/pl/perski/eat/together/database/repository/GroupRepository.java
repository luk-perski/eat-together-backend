package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.GroupData;

public interface GroupRepository extends JpaRepository<GroupData, Integer> {
}
