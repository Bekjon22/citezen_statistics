package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.domain.Person;
import uz.pdp.enums.Region;
import uz.pdp.model.*;
import uz.pdp.model.response.ApiResponse;

import java.util.List;


public interface PersonService {


    ResponseEntity<ApiResponse<Person>> savePerson(PersonGetDto personGetDto);

//    ResponseEntity<ApiResponse<List<PersonDto>>> getList();

    ResponseEntity<ApiResponse<PersonDto>> get(Long id);

    ResponseEntity<ApiResponse<Person>> update(Long id, PersonGetDto dto);

    ResponseEntity<ApiResponse<Boolean>> delete(Long id);


    ResponseEntity<ApiResponse<List<ArmyAgeDto>>> getArmyAge(Long neighborhoodId);

    ResponseEntity<ApiResponse<List<PersonDto>>> getList(Integer page, Integer size);

    ResponseEntity<ApiResponse<List<FamilyInfo>>> getFamilyInfo(String passportNum);

    ResponseEntity<ApiResponse<PersonStatistics>> getPersonsStatistics(Boolean gender);

    ResponseEntity<ApiResponse<PopulationByRegionDto>> getPersonsStatisticsByRegion(Boolean gender, Integer startAge, Integer endAge, Region region);

//    ResponseEntity<ApiResponse<PopulationByRegionDto>> getPersonsStatisticsByRegion(Boolean gender, Integer startAge, Integer endAge);

}
