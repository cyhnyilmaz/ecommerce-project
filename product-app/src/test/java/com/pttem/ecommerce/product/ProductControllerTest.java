package com.pttem.ecommerce.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pttem.ecommerce.product.dto.product.ProductDto;
import com.pttem.ecommerce.product.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductControllerTest.class)
@OverrideAutoConfiguration(enabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public ProductService productService;

    @Test
    public void shouldUpdateExistingProductStock() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/product/updateProductStock/1/10")

        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReadWithId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/product/read/1")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldCreateAProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ProductDto()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotCreateAProduct() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
