/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lookin
 */
public class MinesPlanTest {
    
    public MinesPlanTest() {
    }

    @Test
    public void testConstructor0args() {
        System.out.println("Testing default constructor");
        MinesPlan i = new MinesPlan();
        assertNotNull("Instance not created.", i);
        assertEquals("Height is not 2", 2, i.getHeight());
        assertEquals("Width is not 2", 2, i.getWidth());
    }
    
    @Test
    public void testConstructor2args() {
        System.out.println("Testing constructor for width/height");
        MinesPlan i = new MinesPlan(5,8);
        assertNotNull("Instance not created.", i);
        assertEquals("Height is not correct", 8, i.getHeight());
        assertEquals("Width is not correct", 5, i.getWidth());
        try {
            i = new MinesPlan(1, 5);
            fail("Not giving BadNumberException for width<2");
        } catch (BadNumberException e) {
        } catch (Exception e) {
            fail("Wrong exception for bad width! Should be BadNumberException.");
        }
        try {
            i = new MinesPlan(15,0);
            fail("Not giving BadNumberException for height<2");
        } catch (BadNumberException e) {
        } catch (Exception e) {
            fail("Wrong exception for bad height! Should be BadNumberException.");
        }
    }

    @Test
    public void testGetWidth() {
        System.out.println("Testing getWidth.");
        MinesPlan instance = new MinesPlan(10,15);
        assertEquals("Wrong size returned.", 10, instance.getWidth());
    }

    @Test
    public void testGetHeight() {
        System.out.println("Testing getWidth.");
        MinesPlan instance = new MinesPlan(10,15);
        assertEquals("Wrong size returned.", 15, instance.getHeight());
    }

    @Test
    public void testSetMineAtIsMineAt() {
        System.out.println("Testing setMineAt and isMineAt.");
        MinesPlan instance = new MinesPlan(8,10);
        try {
           instance.setMineAt(3, 10);
           fail("For wrong position should be BadCoordsException thrown by setMineAt.");
        } catch (BadCoordsException e) {            
        } catch (Exception e) {
           fail("Wrong exception for wrong position by setMineAt.");
        }
        try {
           instance.setMineAt(8, 2);
           fail("For wrong position should be BadCoordsException thrown by setMineAt.");
        } catch (BadCoordsException e) {            
        } catch (Exception e) {
           fail("Wrong exception for wrong position by setMineAt.");
        }

        try {
           instance.isMineAt(3, 10);
           fail("For wrong position should be BadCoordsException thrown by isMineAt.");
        } catch (BadCoordsException e) {            
        } catch (Exception e) {
           fail("Wrong exception for wrong position by isMineAt.");
        }
        try {
           instance.isMineAt(8, 1);
           fail("For wrong position should be BadCoordsException thrown by isMineAt.");
        } catch (BadCoordsException e) {            
        } catch (Exception e) {
           fail("Wrong exception for wrong position by isMineAt.");
        }
        
        assertFalse("isMineAt returns true for clear plan", instance.isMineAt(2, 3));
        instance.setMineAt(2, 3);
        assertTrue("isMineAt returns false for previously added mine", instance.isMineAt(2, 3));
    }

    @Test
    public void testGetNumberOfMines_0args() {
        System.out.println("Testing getNumberOfMines()");
        MinesPlan instance = new MinesPlan(5,6);
        assertEquals("New plan should have 0 mines.",0, instance.getNumberOfMines());
        try{
            instance.setMineAt(0, 0);
            instance.setMineAt(0, 0);
            instance.setMineAt(0, 0);
            instance.setMineAt(2, 3);
            instance.setMineAt(5, 6);
        } catch(Exception e) {            
        }
        assertEquals("Wrong number of mines.",2, instance.getNumberOfMines());
    }     
    
    @Test
    public void testClearAllMines() {
        System.out.println("Testing clearAllMines");
        MinesPlan instance = new MinesPlan(4,5);
        try{
            instance.setMineAt(0, 0);
            instance.setMineAt(0, 0);
            instance.setMineAt(0, 0);
            instance.setMineAt(2, 3);
            instance.setMineAt(5, 6);
        } catch(Exception e) {            
        }

        instance.clearAllMines();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                if(instance.isMineAt(x, y)) {
                    fail("Mine detected after clearing.");
                }
            }
        }
        
        assertEquals("Number of mines is not 0 after clearing.", 0, instance.getNumberOfMines());
    }
    
    @Test
    public void testCoverAll() {
        System.out.println("Testing coverAll");
        MinesPlan instance = new MinesPlan(4, 6);
        instance.coverAll();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 6; y++) {
                if(!instance.isCoveredAt(x, y)) {
                    fail("Uncovered field detected.");
                }
            }
        }
    }

    @Test
    public void testUncoverAll() {
        System.out.println("Testing uncoverAll");
        MinesPlan instance = new MinesPlan(4, 6);
        instance.coverAll();
        instance.uncoverAll();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 6; y++) {
                if(instance.isCoveredAt(x, y)) {
                    fail("Covered field detected.");
                }
            }
        }
    }
    
    @Test
    public void testClearAllMarks() {
        System.out.println("Testing clearAllMarks");
        MinesPlan instance = new MinesPlan(3, 5);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
              instance.mark(x, y, true);
            }
        }
        instance.clearAllMarks();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                if(instance.isMarkedAt(x, y)) {
                    fail("Marked field detected.");
                }
            }
        }
    }

    @Test
    public void testIsMineAt() {
        System.out.println("Testing isMineAt");
        MinesPlan instance = new MinesPlan(3, 5);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                if(instance.isMineAt(x, y)) {
                    fail("There is a mine in newly created plan.");
                }
            }
        }
        
        instance.setMineAt(1, 2);
        assertTrue("Mine not added or not detected?", instance.isMineAt(1, 2));
        
        try {
          instance.setMineAt(-3, 8);
          fail("No exception for wrong coordinates");
        } catch (BadCoordsException e) {
            
        } catch (Exception e) {
          fail("Bad exception for wrong coordinates");
        }
        
    }
    
    @Test
    public void testIsCoveredAt() {
        System.out.println("Testing isCoveredAt");
        MinesPlan instance = new MinesPlan(3, 5);
        instance.coverAll();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                if(!instance.isCoveredAt(x, y)) {
                    fail("Some cell not covered after coverAll.");
                }
            }
        }
        
        instance.uncover(2, 3);
        assertTrue("Selected cell not uncovered.", !instance.isCoveredAt(2, 3));
        
        try {
          instance.isCoveredAt(-3, 8);
          fail("No exception for wrong coordinates");
        } catch (BadCoordsException e) {
            
        } catch (Exception e) {
          fail("Bad exception for wrong coordinates");
        }
        
    }
    
    @Test
    public void testIsMarkedAt() {
        System.out.println("Testing isMarkedAt");
        MinesPlan instance = new MinesPlan(3, 5);
       
        instance.mark(1, 2, false);
        assertTrue("unmarked cell has a mark", !instance.isMarkedAt(1, 2));
        instance.mark(1, 2, true);
        assertTrue("marked cell not detected", instance.isMarkedAt(1, 2));
        instance.mark(1, 2, false);
        assertTrue("unmarking not working", !instance.isMarkedAt(1, 2));
        
        try {
          instance.isMarkedAt(-3, 8);
          fail("No exception for wrong coordinates");
        } catch (BadCoordsException e) {
            
        } catch (Exception e) {
          fail("Bad exception for wrong coordinates");
        }
    }
    
    @Test
    public void testGetNumberOfCovered() {
        System.out.println("Testing getNumberOfCovered");
        MinesPlan instance = new MinesPlan(3, 5);
        instance.coverAll();
        assertEquals("Bad count after covering all",15, instance.getNumberOfCovered());
        instance.uncover(2, 2);
        assertEquals("Bad count after uncovering one cell",14, instance.getNumberOfCovered());
        instance.uncover(2, 2);
        assertEquals("Bad count after uncovering one cell multiple times",14, instance.getNumberOfCovered());
        instance.uncoverAll();
        assertEquals("Bad count after uncovering all",0, instance.getNumberOfCovered());
    }
    
    @Test
    public void testUncover() {
        System.out.println("Testing uncover");
        MinesPlan instance = new MinesPlan(3, 5);
        instance.coverAll();
        instance.uncover(2, 3);
        assertTrue("Uncovered cell covered.",!instance.isCoveredAt(2, 3));
        try {
          instance.uncover(-3, 8);
          fail("No exception for wrong coordinates");
        } catch (BadCoordsException e) {
            
        } catch (Exception e) {
          fail("Bad exception for wrong coordinates");
        }
    }
    
    @Test
    public void testMark() {
        System.out.println("Testing mark");
        MinesPlan instance = new MinesPlan(3, 5);
        instance.mark(2, 3, false);
        instance.mark(2, 3, true);
        assertTrue("Cell not marked.",instance.isMarkedAt(2, 3));
        try {
          instance.mark(-3, 8, true);
          fail("No exception for wrong coordinates");
        } catch (BadCoordsException e) {
            
        } catch (Exception e) {
          fail("Bad exception for wrong coordinates");
        }
    }

    @Test
    public void testGetNumberOfMines_int_int() {
        System.out.println("Testing getNumberOfMines(int, int)");
        MinesPlan instance = new MinesPlan(3, 5);
        assertEquals("New plan should be clear",0, instance.getNumberOfMines(2, 3));
        instance.setMineAt(0, 1);
        instance.setMineAt(0, 2);
        instance.setMineAt(1, 1);
        instance.setMineAt(1, 2);
        instance.setMineAt(2, 4);

        try{
            instance.getNumberOfMines(0, 0);
        } catch (Exception e) {
            fail("There was an exception thrown for correct position!");
        }
        
        assertNotEquals("Don't count selected field, only surrounding!",1, instance.getNumberOfMines(2,4));
        assertEquals("Wrong number of mines in surrounding area!",0, instance.getNumberOfMines(2,4));
        assertEquals("Wrong number of mines in surrounding area!",3, instance.getNumberOfMines(1,2));
        assertEquals("Wrong number of mines in surrounding area!",2, instance.getNumberOfMines(0,0));
    }

    
}