package com.emazon.stock.infrastucture.configuration;

import com.emazon.stock.application.mapper.BrandDtoResponseMapper;
import com.emazon.stock.application.mapper.CategoryDtoResponseMapper;
import com.emazon.stock.application.mapper.PageDtoMapper;
import com.emazon.stock.domain.api.IArticleServicePort;
import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.domain.usecase.ArticleUseCase;
import com.emazon.stock.domain.usecase.BrandUseCase;
import com.emazon.stock.domain.usecase.CategoryUseCase;
import com.emazon.stock.infrastucture.output.jpa.adapter.ArticleJpaAdpater;
import com.emazon.stock.infrastucture.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock.infrastucture.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock.infrastucture.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IArticleRepository;
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
    private final CategoryDtoResponseMapper categoryDtoResponseMapper;

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final BrandDtoResponseMapper brandDtoResponseMapper;

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public IArticlePersistencePort articlePersistencePort(){
        return new ArticleJpaAdpater(articleRepository,brandRepository,categoryRepository, articleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort(){
        return new ArticleUseCase(articlePersistencePort(), brandPersistencePort(), categoryPersistencePort());
    }

    @Bean
    public PageMapper pageMapper() {
        return new PageMapper(categoryEntityMapper, brandEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository,brandEntityMapper, pageMapper());
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public PageDtoMapper pageDtoMapper() {
        return new PageDtoMapper(categoryDtoResponseMapper, brandDtoResponseMapper);
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
