package pl.perski.eat.together.api.v2.dto.event;

import lombok.Data;

import java.util.Date;

@Data
public class EventDtoGet {
    private int id;
    private String creatorDisplayName;
    private Date date;
    private String placeName;
    private double locationLongitude;
    private double locationLatitude;
    private String placeLocation;
    private String description;
    private Boolean isPublic;
    private String status;
    private String participants;
    private boolean callerJoin;
    private boolean callerIsCreator;
}
