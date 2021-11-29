package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.domain.Neighborhood;

import java.util.List;

@Repository
public interface NeighborhoodRepo extends JpaRepository<Neighborhood,Long> {

    @Query(nativeQuery = true, value = " SELECT * FROM neighborhoods n " +
            "             ORDER BY n.id DESC " +
            "             LIMIT :size OFFSET :start ")
    List<Neighborhood> findAllNeigh(Integer start, Integer size);

}
