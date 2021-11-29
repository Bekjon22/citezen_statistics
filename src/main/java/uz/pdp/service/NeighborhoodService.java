package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.domain.Neighborhood;
import uz.pdp.model.response.ApiResponse;

import java.util.List;

public interface NeighborhoodService {
    ResponseEntity<ApiResponse<List<Neighborhood>>> getList(Integer page, Integer size);

}
