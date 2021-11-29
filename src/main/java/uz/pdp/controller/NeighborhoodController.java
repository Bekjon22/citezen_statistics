package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.domain.Neighborhood;
import uz.pdp.model.response.ApiResponse;
import uz.pdp.service.NeighborhoodService;

import java.util.List;

@RestController
@RequestMapping("/api/neighborhood")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    @Autowired
    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<Neighborhood>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                                   @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return neighborhoodService.getList(page,size);
    }

}
