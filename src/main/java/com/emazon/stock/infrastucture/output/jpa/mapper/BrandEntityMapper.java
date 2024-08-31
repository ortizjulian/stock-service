package com.emazon.stock.infrastucture.output.jpa.mapper;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);
}
