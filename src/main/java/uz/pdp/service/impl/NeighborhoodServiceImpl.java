package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.Neighborhood;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.repository.NeighborhoodRepo;
import uz.pdp.service.NeighborhoodService;

import java.util.List;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepo neighborhoodRepo;

    @Autowired
    public NeighborhoodServiceImpl(NeighborhoodRepo neighborhoodRepo) {
        this.neighborhoodRepo = neighborhoodRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Neighborhood>>> getList(Integer page, Integer size) {
        List<Neighborhood> allNeigh = neighborhoodRepo.findAllNeigh(page * size, size);
        if (allNeigh.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("Neighborhood is empty!"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ApiResponse<>(allNeigh));

    }
}
