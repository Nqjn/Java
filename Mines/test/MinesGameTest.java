/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lookin
 */
public class MinesGameTest {

    @Test
    public void testConstructors() {
       System.out.println("Constructors");
       MinesGame i;
       try{
           i=new MinesGame(1, 5, 1);
           fail("It is possible to create too small plan.");
           i=new MinesGame(5, 5, 0);
           fail("It is possible to create plan without mines.");
           i=new MinesGame(5, 5, 25);
           fail("It is possible to create plan with too much mines.");
       } catch (BadNumberException e) {
           
       } catch (Exception e) {
           fail("Wrong exception for bad numbers...");
       }

       try{
           i=new MinesGame(5, 5, 24);
       } catch (Exception e) {
           fail("It is not possible to create game with w*h-1 mines...");
       }
       
       try{
           i=new MinesGame(null);
           fail("It is possible to create game from null plan");
       } catch (NullPointerException e) {    
       } catch (Exception e) {
           fail("Wrong exception for null plan");
       }
       
       try{
           MinesPlan p = new MinesPlan(5, 5);
           p.clearAllMines();
           i=new MinesGame(p);
           fail("It is possible to create game from plan without mines");
       } catch (BadNumberException e) {    
       } catch (Exception e) {
           fail("Wrong exception for plan without mines");
       }
    }
    
    @Test
    public void testGetPlan() {
        System.out.println("getPlan");
        MinesGame g = new MinesGame(3, 4, 5);
        MinesPlan p = g.getPlan();
        assertNotNull("Plan should be not null", p);
        assertEquals("Wrong plan size", 12, p.getWidth()*p.getHeight());
        assertEquals("Wrong plan mines count", 5, p.getNumberOfMines());
        assertEquals("New plan must be covered", 12, p.getNumberOfCovered());

        p = new MinesPlan(4, 5);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        p.setMineAt(2, 4);
        g = new MinesGame(p);
        p = g.getPlan();
        assertNotNull("Plan should be not null", p);
        assertEquals("Wrong plan size", 20, p.getWidth()*p.getHeight());
        assertEquals("Wrong plan mines count", 2, p.getNumberOfMines());
        assertEquals("New plan must be covered", 20, p.getNumberOfCovered());

    }

    @Test
    public void testSetPlan() {
        System.out.println("setPlan");
        MinesPlan p = new MinesPlan(4, 5);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        p.setMineAt(2, 4);
        MinesGame g = new MinesGame(2,3,4);
        g.setPlan(p);
        MinesPlan p2 = g.getPlan();
        assertEquals("I got a different plan than I set up.", p2, p);
        assertEquals("setPlan changes number of mines", 2, p2.getNumberOfMines());
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        MinesGame g = new MinesGame(2, 3, 4);
        assertEquals("New game should be in PLAYING state." ,MinesGame.STATE_PLAYING, g.getState());

        MinesPlan p = new MinesPlan(4, 5);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        p.setMineAt(2, 4);
        g = new MinesGame(p);
        assertEquals("This plan must be in PLAYING state.",MinesGame.STATE_PLAYING, g.getState());
        try {
            g.switchMarked(0, 0);
        } catch (WrongActionException ex) {
        }
        g.uncover(0, 0);
        assertEquals("An attempt to uncover a marked field must not cause an explosion state.",MinesGame.STATE_PLAYING, g.getState());
        g.uncover(2, 4);
        assertEquals("Not exploded after uncovering bomb.",MinesGame.STATE_EXPLODED, g.getState());

        p = new MinesPlan(3, 3);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        p.setMineAt(2, 2);
        g = new MinesGame(p);
        g.uncover(0, 1);
        g.uncover(0, 2);
        g.uncover(1, 0);
        g.uncover(1, 1);
        g.uncover(1, 2);
        g.uncover(2, 0);
        g.uncover(2, 1);
        assertEquals("Not done after uncovering all free fields.",MinesGame.STATE_DONE, g.getState());

        p = new MinesPlan(3, 3);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        p.setMineAt(2, 2);
        g = new MinesGame(p);
        try {
            g.switchMarked(0, 0);
            g.switchMarked(2, 2);
        } catch (WrongActionException ex) {
        }
        assertEquals("When all bombs marked, but not all fields uncovered, state must be PLAYING",MinesGame.STATE_PLAYING, g.getState());
    }

    @Test
    public void testSwitchMarked() {
        System.out.println("switchMarked");
        MinesGame g = new MinesGame(2, 3, 4);
        MinesPlan p = g.getPlan();        
        try {
            g.switchMarked(0, 0);
            assertTrue("Not changed to marked.",g.getPlan().isMarkedAt(0, 0));
            g.switchMarked(0, 0);
            assertTrue("Not changed to unmarked.",!g.getPlan().isMarkedAt(0, 0));
        } catch (WrongActionException ex) {
        }
        
        try {
            g.switchMarked(2, 3);
            fail("Wrong coordinates allowed");
        } catch (WrongActionException ex) {
            fail("Bad exception for wrong coordinates");
        } catch (BadCoordsException e) {            
        } catch (Exception e) {
            fail("Bad exception for wrong coordinates");
        }
        
        p = new MinesPlan(3, 3);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(0, 0);
        g = new MinesGame(p);
        try {
            g.uncover(1,1);
            g.switchMarked(1, 1);
            fail("marking uncovered field not allowed");
        } catch (WrongActionException ex) {
        } catch (Exception e) {
            fail("Bad exception for wrong action");
        }
        
        p.uncover(0, 0);
        try {
            g.switchMarked(2, 2);
        } catch (WrongActionException ex) {            
        }
        assertTrue("Changing marks when game over should be not allowed.", !p.isMarkedAt(2, 2));
    }

    @Test
    public void testUncover() {
        System.out.println("uncover");
        MinesPlan p = new MinesPlan(3, 4);
        p.coverAll();
        p.clearAllMarks();
        p.clearAllMines();
        p.setMineAt(1,2);
        p.setMineAt(1,3);
        p.setMineAt(2, 2);
        MinesGame g = new MinesGame(p);
        g.uncover(0,2);
        
        assertTrue("Uncovered field still covered.", !p.isCoveredAt(0, 2));
        assertTrue("No other field should be uncovered when a mine is in the vicinity",p.isCoveredAt(0, 1));
        assertTrue("No other field should be uncovered when a mine is in the vicinity", p.isCoveredAt(0, 3));

        g.uncover(0,0);
        assertTrue("Uncovered field still covered.", !p.isCoveredAt(0, 0));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(0, 1));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(0, 2));
        assertTrue("Uncovered field in the vincinity of mines", p.isCoveredAt(0, 3));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(1, 0));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(1, 1));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(2, 0));
        assertTrue("Free fields in the vincinity of 0-surrounded field not uncovered.", !p.isCoveredAt(2, 1));
        assertTrue("Uncovered field behind mines!", p.isCoveredAt(2, 3));
        
        try {
            g.switchMarked(2, 3);
        } catch (WrongActionException ex) {
        }
        g.uncover(2, 3);
        assertTrue("Uncovered marked field.",p.isCoveredAt(2, 3));
        
        g.uncover(1, 2);
        g.uncover(1, 3);
        assertTrue("It should be not allowed to uncover field after game is over.",p.isCoveredAt(2, 3));
    }
    
}