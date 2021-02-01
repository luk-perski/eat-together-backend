package pl.perski.eat.together.api.v2.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.perski.eat.together.api.v2.dto.group.GroupDtoGet;
import pl.perski.eat.together.api.v2.dto.group.GroupDtoPost;
import pl.perski.eat.together.api.v2.dto.group.GroupMapper;
import pl.perski.eat.together.database.model.GroupData;
import pl.perski.eat.together.service.IGroupService;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "groups", description = "the Group API")
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final IGroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping("/groups")
    public List<GroupDtoGet> getAllGroups() {
        return groupMapper.toGroupsDTOs(groupService.getAll());
    }

    @PostMapping("/groups")
    @ResponseStatus(value = HttpStatus.CREATED)
    public GroupDtoGet addGroup(@Valid @RequestBody GroupDtoPost request, @Parameter(hidden = true) Authentication authentication) {
        GroupData groupData = groupMapper.toGroupData(request);
        return groupMapper.toGroupDtoGet(groupService.add(groupData, authentication.getName()));
    }

    @PatchMapping("/groups/add-user")
    public String addUser(@RequestParam int groupId, @RequestParam int userId) {
        return groupService.addUserToGroup(userId, groupId);
    }
}
