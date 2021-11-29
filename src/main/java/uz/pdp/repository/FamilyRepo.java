package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Family;

import java.util.List;

@Repository
public interface FamilyRepo extends JpaRepository<Family, Long> {
    @Query(nativeQuery = true, value = " SELECT * FROM families f " +
            "             ORDER BY f.id DESC " +
            "             LIMIT :size OFFSET :start ")
    List<Family> findAllFamily(Integer start, Integer size);
}
