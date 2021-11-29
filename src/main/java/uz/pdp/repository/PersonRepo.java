package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Person;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByPassportNum(String passport);

    Person findByPassportNumAndIdNot(String passport, Long id);


    @Query(nativeQuery = true, value = " SELECT * FROM persons p " +
            "             ORDER BY p.id DESC " +
            "             LIMIT :size OFFSET :start ")
    List<Person> findAll(Integer start, Integer size);


    @Query(nativeQuery = true, value = "  SELECT  * " +
            " FROM persons p " +
            " WHERE p.neighborhood_id = :neighborhoodId AND p.army_status = false AND p.gender=true AND EXTRACT(YEAR FROM age(now(),birthday  ) ) >18 AND EXTRACT(YEAR FROM age(now(),birthday)) <=27")
    List<Person> findAllArmyAge(Long neighborhoodId);


    @Query(nativeQuery = true, value = " SELECT * FROM persons p " +
            " WHERE p.family_id = (SELECT p.family_id FROM persons p " +
            "                      WHERE p.passport_num = :passport ) ")
    List<Person> findFamilyInfoByPassportNum(String passport);


    @Query(nativeQuery = true, value = " SELECT count(p.id) FROM persons p " +
            " WHERE gender = :gender  ")
    Double findOneGenderNum(Boolean gender);


    @Query(nativeQuery = true, value = " SELECT COUNT(p.id) FROM persons p ")
    Double findAllGenderNum();


    @Query(nativeQuery = true, value = " SELECT COUNT(p.id) " +
            " FROM persons p " +
            " LEFT JOIN neighborhoods n on n.id = p.neighborhood_id " +
            " LEFT JOIN districts d on d.id = n.district_id " +
            " WHERE d.region = :region " +
            "  AND p.gender = :gender " +
            "  AND EXTRACT(YEAR FROM age(now(), birthday)) >:startAge " +
            "  AND EXTRACT(YEAR FROM age(now(), birthday)) <:endAge " )
   Double findPopulationByRegionByFilter(Boolean gender, Integer startAge, Integer endAge, String region);


    @Query(nativeQuery = true,value = " SELECT COUNT(p.id) " +
            " FROM persons p " +
            "         LEFT JOIN neighborhoods n on n.id = p.neighborhood_id " +
            "         LEFT JOIN districts d on d.id = n.district_id " +
            " WHERE d.region = :region ")

    double findAllPopulationByRegion(String region);

    @Query(nativeQuery = true, value = " SELECT COUNT(p.id) FROM persons p ")
    double findAllGender();


}
