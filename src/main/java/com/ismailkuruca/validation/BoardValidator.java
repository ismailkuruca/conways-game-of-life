package com.ismailkuruca.validation;

import com.ismailkuruca.model.Board;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * Created by ismailkuruca on 17/04/2017.
 */

@Component
public class BoardValidator {

    public void checkBoardValidity(Board board) {
        if (board.getContent() != null) {

            checkContentAgainstDimensions(board);
            checkContentIntegrity(board);

        }
    }

    private void checkContentIntegrity(Board board) {
        long invalidCellCount = Arrays.stream(board.getContent()).filter(i -> i < 0 && i > 1).count();

        if (invalidCellCount > 0) {
            throw new ValidationException("Cell contents can be either one or zero");
        }
    }

    private void checkContentAgainstDimensions(Board board) {
        if (board.getHeight() * board.getWidth() != board.getContent().length) {
            throw new ValidationException("Board dimensions do not match with board content!");
        }
    }


}
