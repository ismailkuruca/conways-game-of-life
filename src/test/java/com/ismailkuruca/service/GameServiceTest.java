package com.ismailkuruca.service;

import com.ismailkuruca.model.Board;
import com.ismailkuruca.simulator.GameSimulator;
import com.ismailkuruca.validation.BoardValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by ismailkuruca on 18/04/2017.
 */
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameSimulator gameSimulator;

    @Mock
    private BoardValidator boardValidator;

    private Board board;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        PodamFactory podamFactory = new PodamFactoryImpl();
        board = podamFactory.manufacturePojo(Board.class);

    }

    @Test
    public void calculateNextGeneration() throws Exception {
        doNothing().when(boardValidator).checkBoardValidity(any(Board.class));
        when(gameSimulator.calculateNextGeneration(any(Board.class))).thenReturn(board);

        Board board = gameService.calculateNextGeneration(this.board);

        assertThat("returned object is not null", board, is(not(nullValue())));
    }

    @Test
    public void calculateSpecificGenerations() throws Exception {
        doNothing().when(boardValidator).checkBoardValidity(any(Board.class));
        when(gameSimulator.calculateNextGeneration(any(Board.class))).thenReturn(board);

        Board board = gameService.calculateSpecificGenerations(this.board, 5);

        assertThat("returned object is not null", board, is(not(nullValue())));
        verify(gameSimulator, times(5)).calculateNextGeneration(any(Board.class));
    }

}