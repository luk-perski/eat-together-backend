package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.Account;
import pl.perski.eat.together.database.model.Group;
import pl.perski.eat.together.database.model.User;
import pl.perski.eat.together.database.repository.GroupRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.List;

@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final IUserService userService;
    private final IAccountService accountService;

    public GroupService(GroupRepository groupRepository, IUserService userService, IAccountService accountService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.accountService = accountService;
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
        group.addUser(group.getCreatorUserId());
        Group addedGroup = groupRepository.save(group);
        Account account = accountService.getById(group.getCreatorUserId());
        account.addGroup(addedGroup.getId());
        return addedGroup;
    }

    @Override
    public String addUserToGroup(int userId, int groupId) { //todo to dodaje jego samego, a jeszcze dodanie przez kogoś
        Group group = getGroupById(groupId);
        group.addUser(userId);
        groupRepository.save(group);
        User user = userService.getById(userId);
        Account account = user.getUserAccount(); //todo
        account.addGroup(groupId);
        accountService.addAccount(account);
        return String.format("Added user %s to group %s.", user.getName(), group.getName()); //
    }

    private Group getGroupById(int id) {
        Group group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            throw new EntityNotFoundException(Group.class, "id", Integer.toString(id));
        }
        return group;
    }
}
