package pl.perski.eat.together.api.v2.dto.group;

import lombok.Data;

@Data
public class GroupDtoGet {
    private Integer id;
    private String name;
    private int creatorUserId;
}
