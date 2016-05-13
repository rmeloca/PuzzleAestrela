/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.ArrayList;
import java.util.List;
import puzzle.Coordinate;
import puzzle.Puzzle;

/**
 *
 * @author romulo
 */
public class Aestrela {

    private final Puzzle initialInstance;
    private final List<Puzzle> previousIntances;
    private final Puzzle solutionInstance;

    public Aestrela(Puzzle puzzle) {
        this.initialInstance = (Puzzle) puzzle.clone();
        this.previousIntances = new ArrayList<>();
        byte[] tabuleiro = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        this.solutionInstance = new Puzzle(tabuleiro);
    }

    public List<Puzzle> getSolution() {
        List<Puzzle> possibleInstances;
        List<Coordinate> possibleMovements;
        Puzzle clone;
        Puzzle atualInstance;

        this.previousIntances.add(this.initialInstance);
        atualInstance = this.initialInstance;
        possibleInstances = new ArrayList<>();

        while (!atualInstance.isSolved()) {
            possibleInstances.clear();
            possibleMovements = atualInstance.getPossibleMovements();
            for (Coordinate possibleMovement : possibleMovements) {
                clone = (Puzzle) atualInstance.clone();
                clone.move(possibleMovement);
                if (!this.previousIntances.contains(clone)) {
                    possibleInstances.add(clone);
                }
            }
            atualInstance = getBestInstance(possibleInstances);
            this.previousIntances.add(atualInstance);
        }
        return this.previousIntances;
    }

    //36 * NumPecasTrocadas +  18 * DistaciaManhattan + 2 * NumInversoes
    private Puzzle getBestInstance(List<Puzzle> possibleInstances) {
        Puzzle bestInstance = null;
        int minorDistance = Integer.MAX_VALUE;
        int distance;
        for (Puzzle possibleInstance : possibleInstances) {
            distance = possibleInstance.getManhattanDistance(this.solutionInstance);
            distance += possibleInstance.getManhattanDistance(this.initialInstance);
            if (distance < minorDistance) {
                minorDistance = distance;
                bestInstance = possibleInstance;
            }
        }
        return bestInstance;
    }
}
