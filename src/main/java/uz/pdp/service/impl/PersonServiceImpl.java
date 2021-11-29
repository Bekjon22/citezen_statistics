package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.common.MapstructMapper;
import uz.pdp.domain.Family;
import uz.pdp.domain.Neighborhood;
import uz.pdp.domain.Person;
import uz.pdp.enums.Region;
import uz.pdp.model.*;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.repository.FamilyRepo;
import uz.pdp.repository.NeighborhoodRepo;
import uz.pdp.repository.PersonRepo;
import uz.pdp.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    private final PersonRepo personRepo;
    private final FamilyRepo familyRepo;
    private final MapstructMapper mapstructMapper;
    private final NeighborhoodRepo neighborhoodRepo;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo, FamilyRepo familyRepo, MapstructMapper mapstructMapper, NeighborhoodRepo neighborhoodRepo) {
        this.personRepo = personRepo;
        this.familyRepo = familyRepo;
        this.mapstructMapper = mapstructMapper;
        this.neighborhoodRepo = neighborhoodRepo;
    }



    @Override
    public ResponseEntity<ApiResponse<Person>> savePerson(PersonGetDto personGetDto) {
        Person person = personRepo.findByPassportNum(personGetDto.getPassportNum());
        if (person != null) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("This passport, %s has already existed!", personGetDto.getPassportNum())), HttpStatus.CONFLICT);
        }
        person = mapstructMapper.toPerson(personGetDto);

        Optional<Family> optionalFamily = familyRepo.findById(personGetDto.getFamilyId());
        if (!optionalFamily.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("This family id, %s not found!", personGetDto.getFamilyId())), HttpStatus.NOT_FOUND);

        }
        person.setFamily(optionalFamily.get());

        Optional<Neighborhood> optionalNeighborhood = neighborhoodRepo.findById(personGetDto.getNeighborhoodId());
        if (!optionalNeighborhood.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("This neighborhood id, %s not found!", personGetDto.getNeighborhoodId())), HttpStatus.NOT_FOUND);
        }
        person.setNeighborhood(optionalNeighborhood.get());

        return ResponseEntity.ok(new ApiResponse<>(personRepo.save(person)));
    }

    @Override
    public ResponseEntity<ApiResponse<List<PersonDto>>> getList(Integer page, Integer size) {
        List<Person> list = personRepo.findAll(page*size,size);
        if (list.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("Person not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ApiResponse<>(mapstructMapper.toPersonDto(list)));

    }

    @Override
    public ResponseEntity<ApiResponse<List<FamilyInfo>>> getFamilyInfo(String passportNum) {

        Person byPassportNum = personRepo.findByPassportNum(passportNum);
        if (byPassportNum == null) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("This passport, %s not found!", passportNum)), HttpStatus.NOT_FOUND);
        }

        List<Person> personList = personRepo.findFamilyInfoByPassportNum(passportNum);



        return ResponseEntity.ok(new ApiResponse<>(mapstructMapper.toFamilyInfo(personList)));




    }

    @Override
    public ResponseEntity<ApiResponse<PersonStatistics>> getPersonsStatistics(Boolean gender) {

        Double population = personRepo.findOneGenderNum(gender);

        Double allGenderNum = personRepo.findAllGenderNum();

        double v = population/allGenderNum;

        double percentage=Math.round(v*100);

        PersonStatistics personStatistics = new PersonStatistics();
        personStatistics.setOneGenderNum(population);
        personStatistics.setAllPopulation(allGenderNum);
        personStatistics.setPercent(percentage);

        return ResponseEntity.ok(new ApiResponse<>(personStatistics));

    }

    @Override
    public ResponseEntity<ApiResponse<PopulationByRegionDto>> getPersonsStatisticsByRegion(Boolean gender, Integer startAge, Integer endAge, Region region) {

        Double populationByRegionByFilter = personRepo.findPopulationByRegionByFilter(gender, startAge, endAge, region.name());


        if (populationByRegionByFilter==null){
            return new ResponseEntity<>(new ApiResponse<>("This age range, %s not found!"), HttpStatus.NOT_FOUND);
        }

        Double allPopulationByRegion = personRepo.findAllPopulationByRegion(region.name());

        Double percentageByRegion = populationByRegionByFilter/allPopulationByRegion;

        Double percentage= (double) Math.round(percentageByRegion * 100);

        Double allGenderNum = personRepo.findAllGender();

        Double percentageByCountry = (double) Math.round(populationByRegionByFilter / allGenderNum * 100);

        PopulationByRegionDto populationByRegionDto = new PopulationByRegionDto();
        populationByRegionDto.setPopulationByRegion(populationByRegionByFilter);
        populationByRegionDto.setPercentageByRegion(percentage);
        populationByRegionDto.setPercentageByCountry(percentageByCountry);
        return ResponseEntity.ok(new ApiResponse<>(populationByRegionDto));


    }

    @Override
    public ResponseEntity<ApiResponse<PersonDto>> get(Long id) {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (!optionalPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Person not found"), HttpStatus.NOT_FOUND);
        }
        PersonDto personDto = mapstructMapper.toPersonDto(optionalPerson.get());
        return ResponseEntity.ok(new ApiResponse<>(personDto));
    }

    @Override
    public ResponseEntity<ApiResponse<Person>> update(Long id, PersonGetDto dto) {
        Optional<Person> optionalPerson = personRepo.findById(id);

        if (!optionalPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>(String.format("Person id, %s not found!", id)), HttpStatus.NOT_FOUND);
        }

        if (dto.getPassportNum() != null && !dto.getPassportNum().equals("")) {
            Person person = personRepo.findByPassportNumAndIdNot(dto.getPassportNum(), optionalPerson.get().getId());
            if (person != null) {
                return new ResponseEntity<>(new ApiResponse<>(String.format("This passport, %s has already existed!", dto.getPassportNum())), HttpStatus.CONFLICT);
            }
        }
        Person person = optionalPerson.get();
        person.setBirthday(dto.getBirthday());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setGender(dto.isGender());
        person.setPassportNum(dto.getPassportNum());

//            person = mapstructMapper.toPerson(dto);

            Optional<Family> optionalFamily = familyRepo.findById(dto.getFamilyId());
            if (!optionalFamily.isPresent()) {
                return new ResponseEntity<>(new ApiResponse<>(String.format("This family id, %s not found!", dto.getFamilyId())), HttpStatus.NOT_FOUND);
            }
            person.setFamily(optionalFamily.get());


            Optional<Neighborhood> optionalNeighborhood = neighborhoodRepo.findById(dto.getNeighborhoodId());
            if (!optionalNeighborhood.isPresent()) {
                return new ResponseEntity<>(new ApiResponse<>(String.format("This neighborhood id, %s not found!", dto.getNeighborhoodId())), HttpStatus.NOT_FOUND);
            }
            person.setNeighborhood(optionalNeighborhood.get());





        return ResponseEntity.ok(new ApiResponse<>(personRepo.save(person)));


        }

    @Override
    public ResponseEntity<ApiResponse<Boolean>> delete(Long id) {
        Optional<Person> optionalPerson = personRepo.findById(id);
        if (!optionalPerson.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>(String.format("Person id, %s not found!", id)), HttpStatus.NOT_FOUND);

        }

        personRepo.delete(optionalPerson.get());
        return ResponseEntity.ok(new ApiResponse<>(true));
    }

    @Override
    public ResponseEntity<ApiResponse<List<ArmyAgeDto>>> getArmyAge(Long neighborhoodId) {

        Optional<Neighborhood> neighborhoodOptional = neighborhoodRepo.findById(neighborhoodId);
        if (!neighborhoodOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse<>(String.format("This neighborhood id, %s not found!", neighborhoodId)), HttpStatus.NOT_FOUND);
        }


        List<Person> personList = personRepo.findAllArmyAge(neighborhoodId);


        return ResponseEntity.ok(new ApiResponse<>(mapstructMapper.toArmyAgeDto(personList)));

    }




}
