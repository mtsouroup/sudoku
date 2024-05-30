import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SudokulogicTest {
    private static final int[][] BOARD = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };
    private Sudokulogic s=new Sudokulogic(9,BOARD);
    @Test
    public void isInRow() {
        boolean b=s.isInRow(2,3);
        assertFalse(b);
        b=s.isInRow(2,7);
        assertTrue(b);
    }

    @Test
    public void isInCol() {
        boolean b=s.isInCol(1,7);
        assertEquals(true,b);
        b=s.isInCol(1,1);
        assertEquals(false,b);

    }

    @Test
    public void isInBox() {
        boolean b=s.isInBox(0,0,9);
        assertFalse(b);
        b=s.isInBox(0,0,8);
        assertTrue(b);
    }

    @Test
    public void isAcceptable() {
        boolean b=s.isAcceptable(2,3,5);
        assertFalse(b);
        b=s.isAcceptable(2,3,4);
    }

    @Test
    public void getElement() {
        int b=8;
        assertEquals(b,s.getElement(0,0));
    }


    @Test
   public void isEmpty() {
        boolean b = s.isEmpty(0, 0);
        assertFalse(b);
        b = s.isEmpty(0, 1);
        assertTrue(b);
    }


    @Test
    public void helpdis() {
       String a= "help:    1   2   4   6";
       String b=s.helpdis(0,1);
       assertEquals(a,b);

    }

    @Test
    public void getSize() {
       assertEquals(9,s.getSize());
    }

    @Test
    public void help() {
        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(2);
        b.add(4);
        b.add(6);
        ArrayList<Integer> a=s.help(0,1);
        assertArrayEquals(b.toArray(), a.toArray());
    }

    @Test
    public void computersMove() {
    }
}