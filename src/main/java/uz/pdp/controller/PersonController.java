package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.domain.Person;
import uz.pdp.enums.Region;
import uz.pdp.model.*;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<PersonDto>> get(@PathVariable(value = "id") Long id) {
        return personService.get(id);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return personService.getList(page,size);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Person>> save( @Valid @RequestBody PersonGetDto personGetDto) {
        return personService.savePerson(personGetDto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Person>> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PersonGetDto dto) {
        return personService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return personService.delete(id);
    }

    @GetMapping("/get/all/army{neighborhoodId}")
    public ResponseEntity<ApiResponse<List<ArmyAgeDto>>> getList(@PathVariable (value = "neighborhoodId") Long neighborhoodId) {
        return personService.getArmyAge(neighborhoodId);
    }

    @GetMapping("/get/familyInfo")
    public ResponseEntity<ApiResponse<List<FamilyInfo>>> getFamilyInfo (@RequestParam(value = "Passport") String passportNum){
        return personService.getFamilyInfo(passportNum);
    }


    @GetMapping("/get/PopulationStatistics")
    public ResponseEntity<ApiResponse<PersonStatistics>> getPopulationInfo(@RequestParam (value = "Gender") Boolean gender){
        return personService.getPersonsStatistics(gender);
    }

    @GetMapping("/get/filter/by/age")
    public ResponseEntity<ApiResponse<PopulationByRegionDto>> getPopulationInfo(@RequestParam (value = "Gender") Boolean gender,
                                                                                @RequestParam (value = "endAge") Integer endAge,
                                                                                @RequestParam (value = "startAge") Integer startAge,
                                                                                @RequestParam (value = "region")Region region){
        return personService.getPersonsStatisticsByRegion(gender,startAge,endAge,region);
    }

}
