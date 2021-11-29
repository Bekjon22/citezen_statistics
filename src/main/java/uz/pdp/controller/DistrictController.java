package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.domain.District;
import uz.pdp.enums.Region;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.service.DistrictService;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<District>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                               @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return districtService.getList(page,size);
    }

    @GetMapping("/get/all/dist")
    public ResponseEntity<ApiResponse<List<District>>> getListByRegion(@RequestParam(value = "region")Region region) {
        return districtService.getListByRegion(region);
    }
}
