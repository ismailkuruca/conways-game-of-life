package com.ismailkuruca.simulator;

import com.ismailkuruca.model.Board;
import org.springframework.stereotype.Component;

/**
 * Created by ismailkuruca on 17/04/2017.
 */
@Component
public class GameSimulator {

    public Board calculateNextGeneration(Board board) {
        Board nextGen = board.duplicate();
        nextGen.setContent(markNextGen(nextGen.getContent(), nextGen.getWidth(), nextGen.getHeight()));
        return nextGen;
    }

    private int[] markNextGen(int[] content, int width, int height) {
        int[] newContent = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int cell = content[j * height + i];
                int aliveNeighborsCount = countAliveNeighbors(content, width, height, i, j);
                newContent[j * height + i] = isAlive(cell, aliveNeighborsCount) ? 1 : 0;
            }
        }
        return newContent;
    }

    private boolean isAlive(int self, int neighborCount) {
        if (self == 0) {
            return neighborCount == 3;
        } else {
            return neighborCount == 2 || neighborCount == 3;
        }
    }

    private static int countAliveNeighbors(int[] content, int width, int height, int i, int j) {
        int startPosX = (i - 1 < 0) ? i : i - 1;
        int startPosY = (j - 1 < 0) ? j : j - 1;
        int endPosX = (i + 1 >= width) ? i : i + 1;
        int endPosY = (j + 1 >= height) ? j : j + 1;

        int count = 0;

        for (int row = startPosX; row <= endPosX; row++) {
            for (int col = startPosY; col <= endPosY; col++) {
                if (!(row == i && col == j)) { //exclude self
                    count += content[row * height + col];
                }
            }
        }
        return count;
    }

}
