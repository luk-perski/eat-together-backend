package pl.perski.eat.together.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
    @Column(name = "date")
    private Date date;

//    @NotNull
    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_coord")
    private String placeCoord;

    @Column(name = "description")
    private String description;

//    @NotNull
    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "status")
//    @Column(name = "status", columnDefinition = "default '0' ")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
