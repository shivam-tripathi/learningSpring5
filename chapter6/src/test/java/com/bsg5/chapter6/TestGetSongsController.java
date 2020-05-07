package com.bsg5.chapter6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGetSongsController extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeTestMethod
    public void buildMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getSongsControllerTest() throws Exception {
        this.mockMvc.perform(get("/songs").param("artist", "van halen").accept(MediaType.ALL))
            .andExpect(status().isOk());
    }

    @Test
    public void getSongsControllerTestWithoutParameters() throws Exception {
        this.mockMvc.perform(get("/songs").accept(MediaType.ALL))
            .andExpect(status().is4xxClientError());
    }
}
