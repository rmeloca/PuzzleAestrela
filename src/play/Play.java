/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import java.util.Scanner;
import puzzle.Coordinate;
import puzzle.Puzzle;

/**
 *
 * @author romulo
 */
public class Play {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        char direction;
        Coordinate coordinate = Coordinate.NORTH;
        Scanner scanner = new Scanner(System.in);
        while (coordinate != null) {
            System.out.println("-----");
            puzzle.print();
            System.out.println("-----");
            System.out.print("Direção: ");
            direction = scanner.next().charAt(0);
            coordinate = parseDirection(direction);
            System.out.println(coordinate);
            puzzle.move(coordinate);
        }
    }

    public static Coordinate parseDirection(char direction) {
        switch (direction) {
            case '^':
                return Coordinate.NORTH;
            case 'v':
                return Coordinate.SOUTH;
            case 'V':
                return Coordinate.SOUTH;
            case '>':
                return Coordinate.EAST;
            case '<':
                return Coordinate.WEST;
                
            case 'w':
                return Coordinate.NORTH;
            case 'W':
                return Coordinate.NORTH;
            case 's':
                return Coordinate.SOUTH;
            case 'S':
                return Coordinate.SOUTH;
            case 'd':
                return Coordinate.EAST;
            case 'D':
                return Coordinate.EAST;
            case 'a':
                return Coordinate.WEST;
            case 'A':
                return Coordinate.WEST;
            
            default:
                return null;
        }
    }
}
