package pl.perski.eat.together.database.model;

import lombok.*;
import pl.perski.eat.together.enums.EventStatus;

import javax.persistence.*;
import javax.validation.constraints.Future;
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
    private Integer id;
    @Column(name = "creator_account_id")
    private
    int creatorAccountId;
    @Column(name = "creator_name")
    private String creatorName;
    @NotNull
    @Future
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
    @NotNull
    private EventStatus status;
    //todo check if these fields are needed
    @Transient
    private boolean callerJoin;
    @Transient
    private boolean callerIsCreator;
}
