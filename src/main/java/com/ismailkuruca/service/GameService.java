package com.ismailkuruca.service;

import com.ismailkuruca.model.Board;
import com.ismailkuruca.simulator.GameSimulator;
import com.ismailkuruca.validation.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ismailkuruca on 17/04/2017.
 */

@Service
public class GameService {

    private final BoardValidator boardValidator;

    private final GameSimulator gameSimulator;

    @Autowired
    public GameService(BoardValidator boardValidator, GameSimulator gameSimulator) {
        this.boardValidator = boardValidator;
        this.gameSimulator = gameSimulator;
    }

    public Board calculateNextGeneration(Board board) {
        boardValidator.checkBoardValidity(board);

        return gameSimulator.calculateNextGeneration(board);
    }

    public Board calculateSpecificGenerations(Board board, @Valid @Min(value = 1) Integer generation) {
        boardValidator.checkBoardValidity(board);

        for (int i = 0; i < generation; i++) {
            board = calculateNextGeneration(board);
        }
        return board;
    }

    public List<Board> calculateAllGenerations(Board board) {
        boardValidator.checkBoardValidity(board);

        ArrayList<Board> boardCollection = new ArrayList<>();

        while (!isStalled(boardCollection)) {
            board = calculateNextGeneration(board);
            boardCollection.add(board);
        }

        return boardCollection;
    }

    private boolean isStalled(List<Board> boardList) {
        if (boardList.size() >= 2) {
            int[] lastBoardContent = boardList.get(boardList.size() - 1).getContent();
            int[] previousBoardContent = boardList.get(boardList.size() - 2).getContent();
            return Arrays.equals(lastBoardContent, previousBoardContent);
        }
        return false;
    }
}
