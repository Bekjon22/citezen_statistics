package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.domain.Family;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.service.FamilyService;

import java.util.List;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<Family>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return familyService.getList(page,size);
    }


}
