package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandDtoMapper {

    Brand toBrand(BrandDto brandDto);
    BrandDto toBrandDto(Brand brand);
    List<BrandDto> toDtoList(List<Brand> brandList);
}