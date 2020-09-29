package pl.perski.eat.together.database.model;

import lombok.*;
import pl.perski.eat.together.utils.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class EventData extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "creator_account_id")
    private
    int creatorAccountId;
    @Column(name = "creator_name")
    private String creatorName;
    @NotNull
    @Column(name = "date")
    private Date date;
    @NotNull
    @Column(name = "place_name")
    private String placeName;
    @NotNull
    private double locationLongitude;
    @NotNull
    private double locationLatitude;
    @Column(name = "place_location")
    private String placeLocation;
    @Column(name = "description")
    private String description;
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "status")
    //todo change to ENUM
    @NotNull
    private int status;
    private String participants;
    @Transient
    private boolean callerJoin;
    @Transient
    private boolean callerIsCreator;

    public void addUser(int userId) {
        participants = StringUtils.addIdToList(participants, userId);
    }

    public void removeUser(int userId) {
        participants = StringUtils.removeIdFromList(participants, userId);
    }
}
