package pl.perski.eat.together.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends AuditModel {
    @Id
    @GeneratedValue(generator = "event_generator")
    @SequenceGenerator(
            name = "event_generator",
            sequenceName = "event_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotNull
    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private User author;

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

    @Column(name = "status", columnDefinition = "default '0'")
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
