package pl.perski.eat.together.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.perski.eat.together.utils.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class AccountData extends AuditModel {

    @Column(name = "event_history")
    private String eventHistory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "password")
    private String password;
    @Email
    @NotNull
    private String email;

    @Column(name = "user_groups")
    private String userGroups;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserData userData;

    public void addEventToHistory(int eventId) {
        eventHistory = StringUtils.addIdToList(eventHistory, eventId);
    }

    public void removeEventFromHistory(int eventId) {
        eventHistory = StringUtils.removeIdFromList(eventHistory, eventId);
    }

    public void addGroup(int groupId) {
        StringUtils.addIdToList(userGroups, groupId);
    }
}
