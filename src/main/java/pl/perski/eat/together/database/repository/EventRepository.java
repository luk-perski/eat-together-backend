package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
