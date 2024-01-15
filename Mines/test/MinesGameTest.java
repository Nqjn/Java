/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC
 */
public class MinesGameTest {
    
    public MinesGameTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetPlan() {
        System.out.println("getPlan");
        MinesGame i = new MinesGame();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetPlan() {
        System.out.println("setPlan");
        MinesPlan plan = null;
        MinesGame instance = null;
        instance.setPlan(plan);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        MinesGame instance = null;
        int expResult = 0;
        int result = instance.getState();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSwitchMarked() {
        System.out.println("switchMarked");
        int x = 0;
        int y = 0;
        MinesGame instance = null;
        instance.switchMarked(x, y);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUncover() {
        System.out.println("uncover");
        int x = 0;
        int y = 0;
        MinesGame instance = null;
        instance.uncover(x, y);
        fail("The test case is a prototype.");
    }
    
}
