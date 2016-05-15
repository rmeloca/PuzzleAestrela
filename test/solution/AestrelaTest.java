/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.Puzzle;

/**
 *
 * @author romulo
 */
public class AestrelaTest {
    
    public AestrelaTest() {
    }
    
    @Test
    public void testSomeMethod() {
        byte[] tabuleiro = {2, 3, 1, 9, 5, 6, 4, 7, 8};
        Puzzle initial = new Puzzle(tabuleiro);
        Aestrela aestrela = new Aestrela(initial);
        
        System.out.println("Inicial");
        initial.print();
        List<Puzzle> solutionTrace = aestrela.getSolution();
        System.out.println("Inicial");
        initial.print();
        
        Puzzle solution = solutionTrace.get(solutionTrace.size() - 1);
        
        System.out.println("Solution");
        solution.print();
        System.out.println("Step 1");
        for (Puzzle puzzle : solutionTrace) {
            puzzle.print();
        }
        System.out.println("Steps: " + solutionTrace.size());
        assertTrue(solution.isSolved());
    }
    
    @Test
    public void testAverage() {
        Aestrela aestrela;
        int media = 0;
        int solved = 0;
        List<Puzzle> solucao;
        for (int i = 0; i < 100; i++) {
            aestrela = new Aestrela(new Puzzle());
            solucao = aestrela.getSolution();
            System.out.println("steps: " + solucao.size() + " solved: " + solucao.get(solucao.size() - 1).isSolved());
            media += solucao.size();
            if (solucao.get(solucao.size() - 1).isSolved()) {
                solved++;
            }
        }
        System.out.println("media: " + media / 100);
        System.out.println("solved: " + solved);
        assertTrue(solved > 0);
    }
    
}
