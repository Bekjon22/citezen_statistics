package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.domain.District;
import uz.pdp.enums.Region;

import java.util.List;

public interface DistrictRepo extends JpaRepository<District,Long> {

    @Query(nativeQuery = true, value = " SELECT * FROM districts d " +
            "             ORDER BY d.id DESC " +
            "             LIMIT :size OFFSET :start ")
    List<District> findAllDistrict(Integer start, Integer size);




    List<District> findByRegion (Region region);


}
