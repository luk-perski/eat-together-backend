package pl.perski.eat.together.api.v2.dto.user;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDtoPost {
    private String firstName;
    private String lastName;
    @Column(name = "company_name")
    private String companyName;
    private String description;
    private Double userLocationLongitude;
    private Double userLocationLatitude;
    @Column(name = "distance_range")
    private Double distanceRange;
    private String userLocationAddress;
}
