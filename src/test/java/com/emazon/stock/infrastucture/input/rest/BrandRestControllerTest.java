package com.emazon.stock.infrastucture.input.rest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.emazon.stock.application.dto.BrandDtoRequest;
import com.emazon.stock.application.handler.IBrandHandler;
import com.emazon.stock.configuration.TestSecurityConfig;
import com.emazon.stock.infrastucture.output.security.jwt.JwtTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BrandRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {BrandRestController.class, TestSecurityConfig.class})
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IBrandHandler iBrandHandler;

    @MockBean
    private JwtTokenManager jwtTokenManager;

    @Test
    void BrandRestController_SaveBrand_ShouldReturnCreatedStatus() throws Exception {
        BrandDtoRequest brandDto = new BrandDtoRequest("Mattelsa", "Marca colombiana");

        ResultActions response = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void BrandRestController_SaveBrand_WhenNameIsNull_ShouldReturnBadRequest() throws Exception {
        BrandDtoRequest invalidBrand = new BrandDtoRequest( null, "Ropa de marca colombiana");

        ResultActions response = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBrand)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void BrandRestController_SaveBrand_WhenDescriptionIsNull_ShouldReturnBadRequest() throws Exception {
        BrandDtoRequest invalidBrand = new BrandDtoRequest( "Mattelsa",null);

        ResultActions response = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBrand)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void BrandRestController_SaveBrand_WhenNameExceeds50Characters_ShouldReturnBadRequest() throws Exception {

        BrandDtoRequest invalidBrand = new BrandDtoRequest(
                "Este nombre de marca es demasiado largo para probar que la Excepción MethodArgumentNotValidException sea lanzada",
                "Ropa de marca colombiana");

        ResultActions response = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBrand)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void BrandRestController_SaveBrand_WhenDescriptionExceeds120Characters_ShouldReturnBadRequest() throws Exception {

        BrandDtoRequest invalidBrand = new BrandDtoRequest(
                "Mattelsa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet euismod neque vitae eleifend. Nam tempus, ipsum id suscipit faucibus, ante ligula luctus sem, eu dignissim nulla justo vitae tellus. Fusce ut bibendum erat, id porttitor nunc. Cras molestie neque et libero ornare fermentum.");

        ResultActions response = mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBrand)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}