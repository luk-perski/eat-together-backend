package pl.perski.eat.together.api.v2.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoPost {
    private String firstName;
    private String lastName;
    private String companyName;
    private String description;
    private Double userLocationLongitude;
    private Double userLocationLatitude;
    private Double distanceRange;
    private String userLocationAddress;
}
