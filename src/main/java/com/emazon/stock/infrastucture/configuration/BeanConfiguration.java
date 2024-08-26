package com.emazon.stock.infrastucture.configuration;

import com.emazon.stock.application.mapper.CategoryDtoMapper;
import com.emazon.stock.application.mapper.PageDtoMapper;
import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.domain.usecase.BrandUseCase;
import com.emazon.stock.domain.usecase.CategoryUseCase;
import com.emazon.stock.infrastucture.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock.infrastucture.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock.infrastucture.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IBrandRepository;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final CategoryDtoMapper categoryDtoMapper;

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;


    @Bean
    public PageMapper pageMapper() {
        return new PageMapper(categoryEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository,brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public PageDtoMapper pageDtoMapper() {
        return new PageDtoMapper(categoryDtoMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper, pageMapper());
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }
}
