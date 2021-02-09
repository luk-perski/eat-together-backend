package pl.perski.eat.together.database.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserData extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "company_name")
    private String companyName;
    private String description;
    @NotNull
    private double userLocationLongitude;
    @NotNull
    private double userLocationLatitude;
    @NotNull
    private String userLocationAddress;
    @Column(name = "distance_range")
    private double distanceRange;
}
