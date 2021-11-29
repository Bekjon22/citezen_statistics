package uz.pdp.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", columnDefinition = "boolean default false")
    private boolean gender;

    @Column(name = "army_status", columnDefinition = "boolean default false")
    private boolean serveToArmyStatus;

    @Column(name = "passport_num", nullable = false, unique = true)
    private String passportNum;

    @Column(name = "birthday")
    private LocalDate birthday;


    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    private Neighborhood neighborhood;
    @Column(insertable = false, updatable = false)
    private Long neighborhood_id;


    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;
    @Column(insertable = false, updatable = false)
    private Long family_id;





}
