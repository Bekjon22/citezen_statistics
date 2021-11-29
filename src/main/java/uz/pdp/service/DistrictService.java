package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.domain.District;
import uz.pdp.enums.Region;
import uz.pdp.model.response.ApiResponse;

import java.util.List;

public interface DistrictService {
    ResponseEntity<ApiResponse<List<District>>> getList(Integer page, Integer size);

    ResponseEntity<ApiResponse<List<District>>> getListByRegion(Region region);

}
