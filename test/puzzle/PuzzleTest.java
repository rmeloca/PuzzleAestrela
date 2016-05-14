/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class PuzzleTest {

    Puzzle puzzle;

    public PuzzleTest() {
        puzzle = new Puzzle();
    }

    @Test
    public void testSomeMethod() {
        puzzle.print();
        puzzle.move(Coordinate.NORTH);
        puzzle.move(Coordinate.NORTH);
        puzzle.move(Coordinate.NORTH);
        puzzle.print();
    }

}
