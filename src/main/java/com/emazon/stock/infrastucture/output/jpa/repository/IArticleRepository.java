package com.emazon.stock.infrastucture.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {
    @Query("SELECT a.quantity FROM ArticleEntity a WHERE a.id = :articleId")
    Integer findQuantityById(Long articleId);
}
