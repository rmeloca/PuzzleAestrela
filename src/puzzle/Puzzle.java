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

    @Deprecated
    private int getManDist() {
        int index = 0;
        int manDist = 0;

        for (int linhaAtual = 0; linhaAtual < 3; linhaAtual++) {
            for (int colunaAtual = 0; colunaAtual < 3; colunaAtual++, index++) {
                int posicaoObjetivo = (tabuleiro[index] - 1);
                int colunaObjetivo = posicaoObjetivo % 3;
                int linhaObjetivo = posicaoObjetivo / 3;

                manDist += Math.abs(linhaObjetivo - linhaAtual) + Math.abs(colunaObjetivo - colunaAtual);
            }
        }
        return manDist;
    }

    private int getDistance(int indexA, int indexB) {
        int colunaObjetivo = indexB % 3;
        int linhaObjetivo = indexB / 3;

        int colunaAtual = indexA % 3;
        int linhaAtual = indexA / 3;

        return Math.abs(linhaObjetivo - linhaAtual) + Math.abs(colunaObjetivo - colunaAtual);
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

    @Override
    public boolean equals(Object obj) {
        Puzzle instance = (Puzzle) obj;
        for (byte i = 0; i < tabuleiro.length; i++) {
            if (instance.tabuleiro[i] != this.tabuleiro[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        return new Puzzle(this.tabuleiro);
    }

}
