package pl.perski.eat.together.service;

import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.model.GroupData;
import pl.perski.eat.together.database.repository.AccountRepository;
import pl.perski.eat.together.database.repository.GroupRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;
import pl.perski.eat.together.utils.AccountUtils;

import java.util.List;

@Service
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final IUserService userService;
    private final IAccountService accountService;
    private final AccountRepository accountRepository;

    public GroupService(GroupRepository groupRepository, IUserService userService, IAccountService accountService, AccountRepository accountRepository) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<GroupData> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public GroupData getById(int groupId) {
        return getGroupById(groupId);
    }

    @Override
    public GroupData add(GroupData groupData) {
        groupData.addUser(groupData.getCreatorUserId());
        GroupData addedGroupData = groupRepository.save(groupData);
        AccountData accountData = accountService.getById(groupData.getCreatorUserId());
        AccountUtils.addGroup(accountData, addedGroupData.getId());
        return addedGroupData;
    }

    @Override
    public String addUserToGroup(int userId, int groupId) {
        GroupData groupData = getGroupById(groupId);
        groupData.addUser(userId);
        groupRepository.save(groupData);
        AccountData accountData = accountRepository.findAccountByUserData(userId);
        AccountUtils.addGroup(accountData, groupId);
        accountRepository.save(accountData);
        return String.format("Added user %s to group %s.", accountData.getUserData().getFirstName(), groupData.getName());
    }

    private GroupData getGroupById(int id) {
        GroupData groupData = groupRepository.findById(id).orElse(null);
        if (groupData == null) {
            throw new EntityNotFoundException(GroupData.class, "id", Integer.toString(id));
        }
        return groupData;
    }
}
