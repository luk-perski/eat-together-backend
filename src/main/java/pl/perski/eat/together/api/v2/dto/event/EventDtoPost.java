package pl.perski.eat.together.api.v2.dto.event;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EventDtoPost {
    private Date date;
    private String placeName;
    private double locationLongitude;
    private double locationLatitude;
    private String placeLocation;
    private String description;
    private Boolean isPublic;
    private String status;
}
