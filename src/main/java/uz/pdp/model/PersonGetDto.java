package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PersonGetDto {


    private String firstName;

    private String lastName;

    private boolean gender;

    private boolean serveToArmyStatus;

   @Size(min = 9,max = 9,message = "Passport length must be 9! ")
    private String passportNum;

    private LocalDate birthday;

    private Long neighborhoodId;

    private Long familyId;
}
