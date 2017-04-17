package com.ismailkuruca.controller;

import com.ismailkuruca.model.Board;
import com.ismailkuruca.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by ismailkuruca on 17/04/2017.
 */

@Controller(value = "/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<Board> nextGeneration(@Valid @RequestBody Board board) {
        return ResponseEntity.ok(gameService.calculateNextGeneration(board));
    }

    @RequestMapping(value = "/board/{generation}", method = RequestMethod.POST)
    public ResponseEntity<Board> specificGeneration(@Valid @RequestBody Board board, @PathVariable("generation") Integer generation) {
        return ResponseEntity.ok(gameService.calculateSpecificGenerations(board, generation));
    }

}
