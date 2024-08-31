package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.BrandDtoResponse;
import com.emazon.stock.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandDtoResponseMapper {

    BrandDtoResponse toBrandDtoResponse(Brand brand);
}
