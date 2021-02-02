package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.perski.eat.together.database.model.EventParticipationData;

import java.util.List;

@Repository
public interface EventParticipationRepository extends JpaRepository<EventParticipationData, Integer> {
    List<EventParticipationData> findByUser_Id(final Integer id);

    List<EventParticipationData> findByEvent_IdAndUser_Id(final Integer eventId, final Integer userId);
}