package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.GroupMember;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    List<GroupMember> findByGroup_IdAndUser_Id(final Integer groupId, final Integer userId);
}
