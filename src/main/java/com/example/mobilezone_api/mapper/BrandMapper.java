package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.BrandDTO;
import com.example.mobilezone_api.model.Brand;
import com.example.mobilezone_api.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(source = "brandId", target = "id")
    @Mapping(expression = "java(mapProducts(brand.getProducts()))", target = "numberOfProducts")
    BrandDTO mapBrandToDTO(Brand brand);

    default Integer mapProducts(List<Product> numberOfProducts) {
        return numberOfProducts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    Brand mapDTOToBrand (BrandDTO brandDTO);
}
