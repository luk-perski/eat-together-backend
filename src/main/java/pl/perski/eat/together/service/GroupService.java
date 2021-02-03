package pl.perski.eat.together.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.GroupData;
import pl.perski.eat.together.database.model.GroupMember;
import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.GroupMemberRepository;
import pl.perski.eat.together.database.repository.GroupRepository;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.utils.GroupUtils;
import pl.perski.eat.together.utils.UserUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public List<GroupData> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public GroupData getById(int groupId) {
        return GroupUtils.getGroupById(groupRepository, groupId);
    }

    @Override
    public GroupData add(GroupData groupData, String userEmail) {
        AccountData accountData = accountRepository.findAccountByEmail(userEmail);
        UserData userData = accountData.getUserData();
        groupData.setCreatorUserId(userData.getId());
        groupData = groupRepository.save(groupData);
        addGroupMember(groupData, userData);
        return groupData;
    }

    @Override
    public String addUserToGroup(int userId, int groupId) {
        GroupData groupData = GroupUtils.getGroupById(groupRepository, groupId);
        UserData userData = UserUtils.getUserById(userRepository, userId);
        addGroupMember(groupData, userData);
        return String.format("Added user %s to group %s.", userData.getFirstName(), groupData.getName());
    }


    private void addGroupMember(GroupData groupData, UserData userData) {
        if (groupMemberRepository.findByGroup_IdAndUser_Id(groupData.getId(), userData.getId()).size() > 0) {
            throw new DataIntegrityViolationException("The user is already a member in the group");
        }
        GroupMember groupMember = GroupMember.builder()
                .group(groupData)
                .user(userData)
                .build();
        groupMemberRepository.save(groupMember);
    }
}
