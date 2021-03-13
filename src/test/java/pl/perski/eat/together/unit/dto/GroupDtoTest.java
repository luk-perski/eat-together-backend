package pl.perski.eat.together.unit.dto;

import org.junit.Test;
import pl.perski.eat.together.api.v2.dto.group.GroupDtoGet;
import pl.perski.eat.together.api.v2.dto.group.GroupDtoPost;
import pl.perski.eat.together.api.v2.dto.group.GroupMapper;
import pl.perski.eat.together.api.v2.dto.group.GroupMapperImpl;
import pl.perski.eat.together.database.model.GroupData;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;

public class GroupDtoTest {

    private GroupMapper groupMapper() {
        return new GroupMapperImpl();
    }

    @Test
    public void whenConvertGroupDataToGroupDtoGet_thenCorrect() {
        GroupData groupData = GroupData.builder()
                .id(1)
                .name(randomAlphabetic(8))
                .creatorUserId(5)
                .build();

        GroupDtoGet groupDtoGet = groupMapper().toGroupDtoGet(groupData);
        assertEquals(groupData.getId(), groupDtoGet.getId());
        assertEquals(groupData.getName(), groupDtoGet.getName());
        assertEquals(groupData.getCreatorUserId(), groupDtoGet.getCreatorUserId());
    }

    @Test
    public void whenConvertGroupDtoPostToGroupData_thenCorrect() {
        GroupDtoPost groupDtoPost = GroupDtoPost.builder()
                .name(randomAlphabetic(8))
                .build();

        GroupData groupData = groupMapper().toGroupData(groupDtoPost);
        assertEquals(groupDtoPost.getName(), groupData.getName());
    }
}
