package com.ismailkuruca.simulator;

import com.ismailkuruca.model.Board;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ismailkuruca on 18/04/2017.
 */
public class GameSimulatorTest {

    @Spy
    private GameSimulator gameSimulator;

    private Board board;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        board = new Board();
    }

    @Test
    public void calculateNextGeneration_blinker() throws Exception {
        board.setWidth(3);
        board.setHeight(3);

        int[] blinker = {0, 1, 0,
                0, 1, 0,
                0, 1, 0};

        int[] blinkerExpectedOutput =
                {0, 0, 0,
                        1, 1, 1,
                        0, 0, 0};

        board.setContent(blinker);
        Board board = gameSimulator.calculateNextGeneration(this.board);


        assertThat("blinker configuration yields expected result", Arrays.equals(board.getContent(), blinkerExpectedOutput), is(true));
    }

    @Test
    public void calculateNextGeneration_beacon() throws Exception {
        board.setWidth(4);
        board.setHeight(4);

        int[] beacon = {1, 1, 0, 0,
                1, 1, 0, 0,
                0, 0, 1, 1,
                0, 0, 1, 1};

        int[] beaconExpectedOutput = {1, 1, 0, 0,
                1, 0, 0, 0,
                0, 0, 0, 1,
                0, 0, 1, 1};

        board.setContent(beacon);
        Board board = gameSimulator.calculateNextGeneration(this.board);


        assertThat("beacon configuration yields expected result", Arrays.equals(board.getContent(), beaconExpectedOutput), is(true));

    }
}