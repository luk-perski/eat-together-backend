package pl.perski.eat.together.database.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.perski.eat.together.utils.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "groups")
public class GroupData extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "creator_user_ID")
    private int creatorUserId;

    @Column(name = "users_ID")
    private String usersId;

    public void addUser(int userId) {
        StringUtils.addIdToList(this.usersId, userId);
    }
}
