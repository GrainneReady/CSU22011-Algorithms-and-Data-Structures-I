package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */

    @Test
    public void testEmpty()
    {
        int expectedResult = 0;
        assertEquals("countCollinear with 3 empty arrays should return zero",     expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast with 3 empty arrays should return zero", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // TODO: write more tests here to cover 100% of the instructions and the branches of Collinear.java
    @Test
    public void test10Values()
    {
        int[] arr1 = {1, 9, 7, 5, 49, 14, 19, 17, 2, 4};
        int[] arr2 = {2, 4, 7, 14, 25, 99, 6, 15, 18, 3};
        int[] arr3 = {3, 13, 7, 16, 26, 44, 6, 5, 20, 25};
        int expectedResult = 16;
        assertEquals("countCollinear with 3 arrays with 10 values each should return 16",     expectedResult, Collinear.countCollinear(arr1, arr2, arr3));
        assertEquals("countCollinear with 3 arrays with 10 values each should return 16",     expectedResult, Collinear.countCollinearFast(arr1, arr2, arr3));
    }

    @Test
    public void test3Values()
    {
        int[] arr1 = {1, 0, 3};
        int[] arr2 = {2, 0, 1};
        int[] arr3 = {3, 0, 1};
        int expectedResult = 4;
        assertEquals("countCollinear with 3 arrays with 3 values each should return 4",     expectedResult, Collinear.countCollinear(arr1, arr2, arr3));
        assertEquals("countCollinear with 3 arrays with 3 values each should return 4",     expectedResult, Collinear.countCollinearFast(arr1, arr2, arr3));
    }
}

