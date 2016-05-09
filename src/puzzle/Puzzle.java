/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.Random;

/**
 *
 * @author romulo
 */
public class Puzzle {

    private byte[] tabuleiro;
    private byte nineIndex;

    public Puzzle() {
        this.tabuleiro = new byte[9];
        inicializar();
        embaralhar();
        sincronizarNineIndex();
    }

    private void inicializar() {
        for (byte i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i] = (byte) (i + 1);
        }
    }

    private void sincronizarNineIndex() {
        for (byte i = 0; i < tabuleiro.length; i++) {
            if (tabuleiro[i] == 9) {
                nineIndex = i;
                return;
            }
        }
    }

    private void embaralhar() {
        Random random = new Random();
        byte a, b, tmp;
        for (byte i = 0; i < tabuleiro.length; i++) {
            a = (byte) random.nextInt(tabuleiro.length);
            b = (byte) random.nextInt(tabuleiro.length);
            tmp = tabuleiro[a];
            tabuleiro[a] = tabuleiro[b];
            tabuleiro[b] = tmp;
        }
    }

    private boolean canMove(Coordinate coordinate) {
        switch (coordinate) {
            case NORTH:
                return nineIndex + 3 < tabuleiro.length;
            case SOUTH:
                return nineIndex - 3 >= 0;
            case EAST:
                return nineIndex - 1 >= 0 && nineIndex != 3 && nineIndex != 6;
            case WEST:
                return nineIndex + 1 < tabuleiro.length && nineIndex != 2 && nineIndex != 5;
            default:
                return false;
        }
    }

    private byte sumIndex(Coordinate coordinate) {
        switch (coordinate) {
            case NORTH:
                return +3;
            case SOUTH:
                return -3;
            case EAST:
                return -1;
            case WEST:
                return +1;
            default:
                return 0;
        }
    }

    public boolean move(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        if (!canMove(coordinate)) {
            return false;
        }
        tabuleiro[nineIndex] = tabuleiro[nineIndex + sumIndex(coordinate)];
        nineIndex += sumIndex(coordinate);
        tabuleiro[nineIndex] = 9;
        return true;
    }

    public void print() {
        for (byte i = 0; i < tabuleiro.length; i += 3) {
            for (byte j = i; j < i + 3; j++) {
                if (tabuleiro[j] == 9) {
                    System.out.print("*" + " ");
                } else {
                    System.out.print(tabuleiro[j] + " ");
                }
            }
            System.out.println();
        }
    }
}
