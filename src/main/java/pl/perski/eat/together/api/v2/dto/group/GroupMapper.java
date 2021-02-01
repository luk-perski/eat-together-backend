package pl.perski.eat.together.api.v2.dto.group;

import org.mapstruct.Mapper;
import pl.perski.eat.together.database.model.GroupData;

import java.util.List;

@Mapper
public interface GroupMapper {
    GroupDtoGet toGroupDtoGet(GroupData group);

    GroupData toGroupData(GroupDtoPost groupDtoPost);

    List<GroupDtoGet> toGroupsDTOs(List<GroupData> groups);
}
