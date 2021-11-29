package uz.pdp.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.domain.Person;
import uz.pdp.model.ArmyAgeDto;
import uz.pdp.model.FamilyInfo;
import uz.pdp.model.PersonDto;
import uz.pdp.model.PersonGetDto;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MapstructMapper {


    @Mapping(target = "neighborhood_id", source = "neighborhoodId")
    @Mapping(target = "family_id", source = "familyId")
    Person toPerson(PersonGetDto dto);


    @Mapping(target = "neighborhoodId", source = "neighborhood_id")
    @Mapping(target = "familyId", source = "family_id")
    PersonDto toPersonDto(Person person);

    List<PersonDto> toPersonDto(List<Person> personList);



    List<ArmyAgeDto> toArmyAgeDto(List<Person> personList);




    List<FamilyInfo> toFamilyInfo(List<Person> personList);









//    @Mapping(target = "userId", source = "user.id")
//    CardDto toCartDto(Card card);
//
//    List<CardDto> toCartDto(List<Card> cards);
//
//
//    @Mapping(target = "senderCardNumber", source = "sender.number")
//    @Mapping(target = "receiverCardNumber", source = "receiver.number")
//    OperationDto toOperationDto(Operation operation);
//
//    List<OperationDto> toOperationDto(List<Operation> operations);
//
//    @Mapping(target = "active", source = "active", defaultValue = "true")
//    User toUser(UserCrudDto dto);


}
