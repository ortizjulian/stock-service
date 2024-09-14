package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.BrandDtoRequest;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_STRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandDtoRequestMapper {

    Brand toBrand(BrandDtoRequest brandDtoRequest);
}
