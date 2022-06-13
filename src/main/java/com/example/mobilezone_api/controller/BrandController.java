package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.BrandDTO;
import com.example.mobilezone_api.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public BrandDTO getBrand(@PathVariable Long id) {
        return brandService.getBrand(id);
    }

    @PostMapping
    public BrandDTO create(@RequestBody BrandDTO brandDTO) {
        return brandService.save(brandDTO);
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, BrandDTO brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @GetMapping("/delete/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
    }
}
