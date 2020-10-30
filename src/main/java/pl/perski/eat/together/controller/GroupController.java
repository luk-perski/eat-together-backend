package pl.perski.eat.together.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.database.model.GroupData;
import pl.perski.eat.together.service.GroupService;
import pl.perski.eat.together.service.IGroupService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "groups", description = "the Group API")
@Controller
public class GroupController {
    private final IGroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<GroupData> getAllGroups() {
        return groupService.getAll();
    }

    @PostMapping("/groups")
    public GroupData addGroup(@Valid @RequestBody GroupData groupData) {
        return groupService.add(groupData);
    }

    @PatchMapping("/groups/{groupId}/{userId}")
    public String addUser(@PathVariable(name = "groupId") int groupId, @PathVariable(name = "userId") int userId) {
        return groupService.addUserToGroup(userId, groupId);
    }
}
