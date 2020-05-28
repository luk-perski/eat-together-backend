package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.perski.eat.together.database.model.EventData;

import java.util.List;

public interface EventRepository extends JpaRepository<EventData, Integer> {
    @Query("select a from EventData a where a.status = 0 and a.date >= CURRENT_TIMESTAMP ORDER BY a.date ASC")
    List<EventData> findAllWithEventDateAfterNow();
}
