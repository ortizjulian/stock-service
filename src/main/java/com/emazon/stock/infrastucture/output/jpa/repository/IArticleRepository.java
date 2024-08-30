package com.emazon.stock.infrastucture.output.jpa.repository;

import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByName(String articleName);
}
