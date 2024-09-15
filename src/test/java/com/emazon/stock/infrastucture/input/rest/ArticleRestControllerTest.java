package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.*;
import com.emazon.stock.application.handler.IArticleHandler;
import com.emazon.stock.configuration.TestSecurityConfig;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.infrastucture.output.security.jwt.JwtTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@WebMvcTest(controllers = ArticleRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ArticleRestController.class, TestSecurityConfig.class})
class ArticleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenManager jwtTokenManager;

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
    void ArticleRestController_GetAllArticles_ShouldReturnListOfArticles() throws Exception {
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

    @Test
    void ArticleRestController_UpdateQuantity_WhenValidRequest_ShouldReturnNoContent() throws Exception {
        UpdateQuantityRequestDto validRequest = new UpdateQuantityRequestDto(1L, 10);

        Mockito.doNothing().when(articleHandler).updateQuantity(validRequest);

        ResultActions response = mockMvc.perform(patch("/article/updateQuantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void ArticleRestController_UpdateQuantity_WhenArticleIdIsNull_ShouldReturnBadRequest() throws Exception {
        UpdateQuantityRequestDto invalidRequest = new UpdateQuantityRequestDto(null, 10);

        ResultActions response = mockMvc.perform(patch("/article/updateQuantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void ArticleRestController_UpdateQuantity_WhenQuantityIsNotPositive_ShouldReturnBadRequest() throws Exception {
        UpdateQuantityRequestDto invalidRequest = new UpdateQuantityRequestDto(1L, -10);

        ResultActions response = mockMvc.perform(patch("/article/updateQuantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}