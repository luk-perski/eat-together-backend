package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.Group;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.GroupRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

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
    public Group getById(int groupId) {
        return getGroupById(groupId);
    }

    @Override
    public Group add(Group group) {
        group.addUser(1); //todo wyciąganie user id z group id
        Group addedGroup = groupRepository.save(group);
        Account account = getAccountById(group.getCreatorId()); //todo obsługa
        account.addGroup(addedGroup.getId());
        return addedGroup;
    }

    @Override
    public String addUserToGroup(int userId, int groupId) { //todo to dodaje jego samego, a jeszcze dodanie przez kogoś
        Group group = getGroupById(groupId); //todo
        group.addUser(userId);
        groupRepository.save(group);
        Account account = getAccountById(1); //todo
        account.addGroup(groupId);
        accountRepository.save(account);
        return "Added"; //todo lepszy response
    }

    private Account getAccountById(int id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new EntityNotFoundException(Account.class, "id", Integer.toString(id));
        }
        return account;
    }

    private Group getGroupById(int id) {
        Group group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            throw new EntityNotFoundException(Group.class, "id", Integer.toString(id));
        }
        return group;
    }
}
