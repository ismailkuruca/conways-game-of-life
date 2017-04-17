package com.ismailkuruca.validation;

import com.ismailkuruca.model.Board;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.validation.ValidationException;


/**
 * Created by ismailkuruca on 18/04/2017.
 */
public class BoardValidatorTest {

    @Spy
    private BoardValidator boardValidator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ValidationException.class)
    public void checkBoardValidity_invalid() throws Exception {
        Board board = new Board();
        board.setWidth(5);
        board.setHeight(5);
        board.setContent(new int[100]);

        boardValidator.checkBoardValidity(board);
    }

    @Test
    public void checkBoardValidity_valid() throws Exception {
        Board board = new Board();
        board.setWidth(5);
        board.setHeight(5);
        board.setContent(new int[25]);

        boardValidator.checkBoardValidity(board);
    }

}