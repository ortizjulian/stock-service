package com.emazon.stock.infrastucture.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {
    Optional<ArticleEntity> findByName(String articleName);
}
