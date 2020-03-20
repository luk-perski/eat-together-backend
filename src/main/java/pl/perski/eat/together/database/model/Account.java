package pl.perski.eat.together.database.model;

import lombok.Getter;
import lombok.Setter;
import pl.perski.eat.together.utils.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends AuditModel {

    @Column(name = "event_history")
    private String eventHistory;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "user_name")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @Email
    @NotNull
    private String email;
    @Column(name = "user_groups")
    private String userGroups;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEventHistory() {
//        return eventHistory;
//    }
//
//    public void setEventHistory(String eventHistory) {
//        this.eventHistory = eventHistory;
//    }
//
//    public String getUserGroups() {
//        return userGroups;
//    }
//
//    public void setUserGroups(String userGroups) {
//        this.userGroups = userGroups;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public void addEventToHistory(int eventId) {
        StringUtils.addIdToList(eventHistory, eventId);
    }

    public void addGroup(int groupdId) {
        StringUtils.addIdToList(userGroups, groupdId);
    }
}
