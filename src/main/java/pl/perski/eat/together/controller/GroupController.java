package pl.perski.eat.together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.Group;
import pl.perski.eat.together.service.GroupService;
import pl.perski.eat.together.service.IGroupService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {
    private final IGroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups() {
        return groupService.getAll();
    }

    @PostMapping("/groups")
    public Group addGroup(@Valid @RequestBody Group group) {
        return groupService.add(group);
    }

    @PatchMapping("/groups/{groupId}/{userId}")
    public String addUser(@PathVariable(name = "groupId") int groupId, @PathVariable(name = "userId") int userId) {
        return groupService.addUserToGroup(userId, groupId);
    }
}
