package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.BrandDTO;
import com.example.mobilezone_api.exception.BrandNotFoundException;
import com.example.mobilezone_api.mapper.BrandMapper;
import com.example.mobilezone_api.model.Brand;
import com.example.mobilezone_api.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Transactional(readOnly = true)
    public List<BrandDTO> getAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::mapBrandToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BrandDTO save(BrandDTO brandDTO) {
        Brand brand = brandMapper.mapDTOToBrand(brandDTO);

        Brand brandSaved = brandRepository.save(brand);

        brandDTO.setId(brandSaved.getBrandId());
        return brandDTO;
    }

    @Transactional(readOnly = true)
    public BrandDTO getBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Subreddit not found with id -" + id));

        return brandMapper.mapBrandToDTO(brand);
    }

    @Transactional
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
