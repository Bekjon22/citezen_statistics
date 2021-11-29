package uz.pdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.domain.Family;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.repository.FamilyRepo;
import uz.pdp.service.FamilyService;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepo familyRepo;

    @Autowired
    public FamilyServiceImpl(FamilyRepo familyRepo) {
        this.familyRepo = familyRepo;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Family>>> getList(Integer page, Integer size) {
        List<Family> allFamily = familyRepo.findAllFamily(page*size, size);
        if (allFamily.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>("Family is empty!"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new ApiResponse<>(allFamily));

    }









}
