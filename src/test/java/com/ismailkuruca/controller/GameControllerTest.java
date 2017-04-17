package com.ismailkuruca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailkuruca.ConwaysGameOfLifeApplication;
import com.ismailkuruca.model.Board;
import com.ismailkuruca.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ismailkuruca on 18/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConwaysGameOfLifeApplication.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GameService gameService;

    @Autowired
    protected ObjectMapper objectMapper;

    private MediaType applicationJsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private Board board;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.board = new Board();
        this.board.setWidth(10);
        this.board.setHeight(10);
        this.board.setContent(new int[100]);
    }


    @Test
    public void nextGeneration() throws Exception {
        when(gameService.calculateNextGeneration(any(Board.class))).thenReturn(board);
        mockMvc.perform(post("/board").contentType(applicationJsonMediaType)
                .content(asJsonString(board)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void specificGeneration() throws Exception {
        when(gameService.calculateSpecificGenerations(any(Board.class), anyInt())).thenReturn(board);
        mockMvc.perform(post("/board", 10).contentType(applicationJsonMediaType)
                .content(asJsonString(board)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}