package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.exception.ArticleNotFoundException;
import com.emazon.stock.domain.exception.DuplicateCategoryException;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void ArticleUseCase_SaveArticle_ShouldCallSaveBrandOnPersistencePort() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Ropa", "Ropa y accesorios"),
                new Category(2L, "Moda", "Moda y estilo")
        );

        Article newArticle = new Article(
                null,
                "Camisa",
                "Camisa grande",
                100,
                1000.0F,
                new Brand(2L, "Mattelsa", "Marca colombiana"),
                categories
        );
        Mockito.when(brandPersistencePort.existById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(categoryPersistencePort.existById(Mockito.anyLong())).thenReturn(true);
        Mockito.doNothing().when(articlePersistencePort).saveArticle(newArticle);
        articleUseCase.saveArticle(newArticle);
        Mockito.verify(articlePersistencePort).saveArticle(newArticle);
    }

    @Test
    void ArticleUseCase_SaveArticle_WhenCategoriesIdRepeated_ShouldThrowDuplicateCategoryException() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Ropa", "Ropa y accesorios"),
                new Category(1L, "Moda", "Moda y estilo")
        );

        Article newArticle = new Article(
                null,
                "Camisa",
                "Camisa grande",
                100,
                1000.0F,
                new Brand(1L, "Mattelsa", "Marca colombiana"),
                categories
        );
        Mockito.when(brandPersistencePort.existById(Mockito.anyLong())).thenReturn(true);

        assertThrows(DuplicateCategoryException.class, () -> {
            articleUseCase.saveArticle(newArticle);
        });

        Mockito.verify(articlePersistencePort, Mockito.never()).saveArticle(Mockito.any(Article.class));
    }

    @Test
    void ArticleUseCase_GetAllArticles_ShouldReturnListOfArticles() {

        List<Article> mockArticles = Arrays.asList(
                new Article(1L, "Camisa", "Camisa grande", 100, 1000.0F, new Brand(1L, "Mattelsa", "Marca colombiana"), Arrays.asList(new Category(1L, "Ropa", "Ropa y accesorios"))),
                new Article(2L, "Pantalón", "Pantalón azul", 80, 800.0F, new Brand(2L, "Levis", "Marca internacional"), Arrays.asList(new Category(2L, "Moda", "Moda y estilo")))
        );

        PageCustom<Article> articlePageCustom = new PageCustom<>();
        articlePageCustom.setContent(mockArticles);

        Mockito.when(articlePersistencePort.getAllArticles(0,10,"ASC","name", "", "")).thenReturn(articlePageCustom);


        PageCustom<Article> articles = articleUseCase.getAllArticles(0,10,"ASC","name", "", "");

        assertEquals(mockArticles, articles.getContent());

        Mockito.verify(articlePersistencePort, Mockito.times(1)).getAllArticles(0,10,"ASC","name", "", "");
    }

    @Test
    void ArticleUseCase_UpdateQuantity_WhenArticleExists_ShouldUpdateQuantity() {
        Long articleId = 1L;
        Integer quantity = 10;

        Mockito.when(articlePersistencePort.existById(articleId)).thenReturn(true);
        articleUseCase.updateQuantity(articleId, quantity);

        Mockito.verify(articlePersistencePort, Mockito.times(1)).updateQuantity(articleId, quantity);
    }

    @Test
    void ArticleUseCase_UpdateQuantity_WhenArticleDoesNotExist_ShouldThrowArticleNotFoundException() {
        Long articleId = 1L;
        Integer quantity = 10;

        Mockito.when(articlePersistencePort.existById(articleId)).thenReturn(false);


        assertThrows(ArticleNotFoundException.class, () -> {
            articleUseCase.updateQuantity(articleId, quantity);
        });

        Mockito.verify(articlePersistencePort, Mockito.never()).updateQuantity(Mockito.anyLong(), Mockito.anyInt());
    }

}