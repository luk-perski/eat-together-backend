package pl.perski.eat.together.database.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "creator_account_id")
    private
    int creatorAccountId;
    @NotNull
    @Column(name = "date")
    private Date date;
    @NotNull
    @Column(name = "place_name")
    private String placeName;
    @Column(name = "place_coord")
    private String placeCoord;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "is_public")
    private Boolean isPublic;
    @Column(name = "status")
//    @Column(name = "status", columnDefinition = "default '0' ")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCoord() {
        return placeCoord;
    }

    public void setPlaceCoord(String placeCoord) {
        this.placeCoord = placeCoord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatorAccountId() {
        return creatorAccountId;
    }

    public void setCreatorAccountId(int creatorAccountId) {
        this.creatorAccountId = creatorAccountId;
    }
}
