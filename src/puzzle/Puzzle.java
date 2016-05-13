/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author romulo
 */
public class Puzzle {

    private byte[] tabuleiro;
    private Tabuleiro tabuleiroMatriz;
    private byte nineIndex;

    public Puzzle() {
        this.tabuleiro = new byte[9];
        inicializar();
        embaralhar();
        sincronizarNineIndex();
    }

    public Puzzle(byte[] tabuleiro) {
        this.tabuleiro = tabuleiro;
        sincronizarNineIndex();
    }

    public Puzzle(byte[][] tabuleiro) {
        for (byte i = 0; i < tabuleiro.length; i++) {
            for (byte j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == 9) {
                    this.nineIndex = i;
                }
            }

        }
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

    public List<Coordinate> getPossibleMovements() {
        List<Coordinate> coordinates = new ArrayList<>();
        if (canMove(Coordinate.NORTH)) {
            coordinates.add(Coordinate.NORTH);
        }
        if (canMove(Coordinate.SOUTH)) {
            coordinates.add(Coordinate.SOUTH);
        }
        if (canMove(Coordinate.EAST)) {
            coordinates.add(Coordinate.EAST);
        }
        if (canMove(Coordinate.WEST)) {
            coordinates.add(Coordinate.WEST);
        }
        return coordinates;
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

    public int getNumeroPecasTrocadas() {
        int numeroPecasTrocadas = 0;
        for (byte i = 0; i < tabuleiro.length; i++) {
            if (this.tabuleiro[i] != i + 1) {
                numeroPecasTrocadas++;
            }
        }
        return numeroPecasTrocadas;
    }

    public int getManhattanDistance(Puzzle puzzle) {
        int sum = 0;
        for (byte i = 0; i < this.tabuleiro.length; i++) {
            sum += getDistance(i, puzzle.getIndex(this.tabuleiro[i]));
        }
        return sum;
    }

    private int getManDist() {
        int index = 0;
        int manDist = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, index++) {
                int posicaoObjetivo = (tabuleiro[index] - 1);
                int colunaObjetivo = posicaoObjetivo % 3;
                int linhaObjetivo = posicaoObjetivo / 3;

                manDist += Math.abs(linhaObjetivo - i) + Math.abs(colunaObjetivo - j);
            }
        }
        return manDist;
    }

    private int getDistance(int indexA, int indexB) {
        if (indexA == indexB) {
            return 0;
        }
        if (indexA - indexB == 1 || indexB - indexA == 1) {
            return 1;
        }
        if (indexA - indexB == 2 || indexB - indexA == 2) {
            return 2;
        }

        if (indexA < indexB) {
            if (indexA < 3 || indexB < 3) {

            }
            if (indexA > 5 || indexB > 5) {
            }
        }
        throw new UnsupportedOperationException();
    }

    private int getIndex(int valor) {
        for (byte i = 0; i < tabuleiro.length; i++) {
            if (this.tabuleiro[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public boolean isSolved() {
        return getNumeroPecasTrocadas() > 0;
    }

    @Override
    public Object clone() {
        return new Puzzle(this.tabuleiro);
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
