package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.domain.Family;
import uz.pdp.model.response.ApiResponse;

import java.util.List;

public interface FamilyService {

    ResponseEntity<ApiResponse<List<Family>>> getList(Integer page, Integer size);


}
