package com.emazon.stock.infrastucture.input.rest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.emazon.stock.application.dto.CategoryDtoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.emazon.stock.application.handler.ICategoryHandler;

@WebMvcTest(controllers = CategoryRestController.class)
@ExtendWith(MockitoExtension.class)
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICategoryHandler categoryHandler;

    @Test
    void CategoryRestController_SaveCategory_ShouldReturnCreatedStatus() throws Exception {
        CategoryDtoRequest categoryDto = new CategoryDtoRequest("Computadores", "Todo lo relacionado a computadores");

        ResultActions response = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void CategoryRestController_SaveCategory_WhenNameIsNull_ShouldReturnBadRequest() throws Exception {
        CategoryDtoRequest invalidCategory = new CategoryDtoRequest(null, "Todo lo relacionado a computadores");

        ResultActions response = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCategory)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void CategoryRestController_SaveCategory_WhenDescriptionIsNull_ShouldReturnBadRequest() throws Exception {
        CategoryDtoRequest invalidCategory = new CategoryDtoRequest("Nombre Categoría", null);

        ResultActions response = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCategory)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void CategoryRestController_SaveCategory_WhenNameExceeds50Characters_ShouldReturnBadRequest() throws Exception {
        CategoryDtoRequest invalidCategory = new CategoryDtoRequest(
                "Nombre de categoría muy largo para que exceda los 50 caracteres y retorne Bad Request",
                "Todo lo relacionado a computadores");

        ResultActions response = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCategory)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void CategoryRestController_SaveCategory_WhenDescriptionExceeds90Characters_ShouldReturnBadRequest() throws Exception {
        CategoryDtoRequest invalidCategory = new CategoryDtoRequest(
                "Computadores",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet euismod neque vitae eleifend. Nam tempus, ipsum id suscipit faucibus, ante ligula luctus sem, eu dignissim nulla justo vitae tellus. Fusce ut bibendum erat, id porttitor nunc. Cras molestie neque et libero ornare fermentum.");

        ResultActions response = mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCategory)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}