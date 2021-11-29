package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDto {

    private String id;

    private String firstName;

    private String lastName;

    private boolean gender;

    private boolean serveToArmyStatus;

    private String passportNum;

    private LocalDate birthday;

    private Long neighborhoodId;

    private Long familyId;

}
