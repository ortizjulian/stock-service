package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.*;
import com.emazon.stock.application.handler.IArticleHandler;
import com.emazon.stock.domain.model.PageCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = ArticleRestController.class)
@ExtendWith(MockitoExtension.class)
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IArticleHandler articleHandler;

    @Test
    void ArticleRestController_SaveArticle_ShouldReturnCreatedStatus() throws Exception {
        ArticleDtoRequest articledDto =
                new ArticleDtoRequest(
                        "Camisa",
                        "Camisa grande",
                        1,100f,
                        1L,
                        Arrays.asList(1L, 2L, 3L));

        ResultActions response = mockMvc.perform(post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(articledDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void ArticleRestController_SaveArticle_WhenMoreThan3Categories_ShouldReturnBadRequest() throws Exception {
        ArticleDtoRequest invalidArticle =
                new ArticleDtoRequest(
                        "Camisa",
                        "Camisa grande",
                        1,100f,
                        1L,
                        Arrays.asList(1L, 4L, 2L,3L));


        ResultActions response = mockMvc.perform(post("/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidArticle)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void ArticleRestController_GetAllBrands_ShouldReturnListOfArticles() throws Exception {
        List<CategoryArticleDtoResponse> categories1 = List.of(
                new CategoryArticleDtoResponse(1L, "Ropa"),
                new CategoryArticleDtoResponse(2L, "Camisa")
        );
        List<CategoryArticleDtoResponse> categories2 = List.of(
                new CategoryArticleDtoResponse(3L, "Pantalones")
        );

        List<ArticleDtoResponse> articles = Arrays.asList(
                new ArticleDtoResponse(1L, "Camisa", "Camisa grande", 1, 100f, new BrandDtoResponse(1L,"Nike","Ropa"), categories1),
                new ArticleDtoResponse(2L, "Pantalón", "Pantalón azul", 2, 50f, new BrandDtoResponse(1L,"Nike","Ropa"), categories2)
        );

        PageCustom<ArticleDtoResponse> articleDtoResponsePageCustom = new PageCustom<>();
        articleDtoResponsePageCustom.setContent(articles);

        Mockito.when(articleHandler.getAllArticles(0,10,"ASC","name", "", "")).thenReturn(articleDtoResponsePageCustom);

        ResultActions response = mockMvc.perform(get("/article")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(articleDtoResponsePageCustom)));
    }
}