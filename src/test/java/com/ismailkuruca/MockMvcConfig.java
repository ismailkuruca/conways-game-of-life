package com.ismailkuruca;

/**
 * Created by ismail.kuruca on 27/02/2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class MockMvcConfig {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Environment env;

    private int port = 8080;

    public RequestBuilder mockRequestBuilder() {
        return null;
    }

    @Bean
    public MockMvc mockMvc() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
        return builder.build();
    }
}
