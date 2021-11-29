package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.District;
import uz.pdp.enums.Region;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.repository.DistrictRepo;
import uz.pdp.service.DistrictService;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;

    @Autowired
    public DistrictServiceImpl(DistrictRepo districtRepo) {
        this.districtRepo = districtRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<List<District>>> getList(Integer page, Integer size) {
        List<District> allDistrict = districtRepo.findAllDistrict(page * size, size);
        if (allDistrict.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("District is empty!"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ApiResponse<>(allDistrict));
    }

    @Override
    public ResponseEntity<ApiResponse<List<District>>> getListByRegion(Region region) {
        List<District> districtList = districtRepo.findByRegion(region);

        if (districtList.isEmpty()){
            return new ResponseEntity<>(new ApiResponse<>("District is empty!"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ApiResponse<>(districtList));
    }
}
