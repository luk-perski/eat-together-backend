package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.Group;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final AccountRepository accountRepository;

    public GroupService(GroupRepository groupRepository, AccountRepository accountRepository) {
        this.groupRepository = groupRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group add(Group group) {
        group.addUser(1); //todo wyciąganie user id z group id
        Group addedGroup = groupRepository.save(group);
        Account account = accountRepository.findById(group.getCreatorId()).get(); //todo obsługa
        account.addGroup(addedGroup.getId());
        return addedGroup;
    }

    @Override
    public String addUserToGroup(int userId, int groupId) { //todo to dodaje jego samego, a jeszcze dodanie przez kogoś
        Group group = groupRepository.findById(groupId).get(); //todo
        group.addUser(userId);
        groupRepository.save(group);
        Account account = accountRepository.findById(1).get(); //todo
        account.addGroup(groupId);
        accountRepository.save(account);
        return "Added"; //todo lepszy response
    }
}
