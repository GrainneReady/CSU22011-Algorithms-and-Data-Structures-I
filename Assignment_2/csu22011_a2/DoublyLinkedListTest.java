package csu22011_a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Grainne Ready
 *  @version 6/11/22 19:24
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    @Test
    public void testTGet()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

        //test empty list

        assertEquals("Checking get to an empty list at position 5 - expecting null as it is empty", null, testDLL.get(5));

        //test non-empty list
        testDLL.insertBefore(0,0);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(6,2);

        assertEquals( "Checking get to a list containing 3 elements at position 0 - expecting the data at the head of the list", "0", testDLL.get(0).toString());
        assertEquals( "Checking get to a list containing 3 elements at position 1 - expecting the data at position 1 in the list", "1", testDLL.get(1).toString());
        assertEquals( "Checking get to a list containing 3 elements at position 2 - expecting the data at the tail of the list", "2", testDLL.get(2).toString());

        assertEquals("Checking get to a list containing 3 elements at position -1 - expecting null as it is out of bounds", null, testDLL.get(-1));
        assertEquals("Checking get to a list containing 3 elements at position 7 - expecting null as it is out of bounds", null, testDLL.get(7));

    }

    @Test
    public void testDeleteAt()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

        assertFalse("Checking deleteAt on a list containing 0 elements - Expecting false as there are no elements", testDLL.deleteAt(0));

        testDLL.insertBefore(0,0);
        testDLL.insertBefore(1,1);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,3);
        testDLL.insertBefore(4,4);
        testDLL.deleteAt(4);
        assertEquals("Checking deleteAt on a list containing 5 elements - Expecting element at pos 4 (tail) to be removed", "0,1,2,3", testDLL.toString());
        testDLL.deleteAt(0);
        assertEquals("Checking deleteAt to a list containing 4 elements - Expecting element at pos 0 (head) to be removed", "1,2,3", testDLL.toString());
        testDLL.deleteAt(1);
        assertEquals("Checking deleteAt to a list containing 3 elements - Expecting element at pos 1 to be removed", "1,3", testDLL.toString());
        assertFalse("Checking deleteAt to a list containing 2 elements - Expecting no change to list as pos 5 is out of bounds.", testDLL.deleteAt(5));
        assertFalse("Checking deleteAt to a list containing 2 elements - Expecting no change to list as pos -1 is out of bounds", testDLL.deleteAt(-1));
        assertEquals("Checking deleteAt to a list containing 2 elements - Expecting no change to list as pos -1 and -5 were out of bounds", "1,3", testDLL.toString());

        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0,1);
        testDLL2.insertBefore(1,7777);
        testDLL2.insertBefore(2,16);
        testDLL2.deleteAt(1);
        assertEquals("Checking deleteAt on a list containing 3 elements - Expecting element at pos 1 to be removed", "1,16", testDLL2.toString());

        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
        testDLL3.insertBefore(0,1);
        testDLL3.insertBefore(1,7777);
        testDLL3.insertBefore(2,16);
        testDLL3.deleteAt(0);
        assertEquals("Checking deleteAt on a list containing 3 elements - Expecting element at pos 1 to be removed", "7777,16", testDLL3.toString());

        DoublyLinkedList<Integer> testDLL4 = new DoublyLinkedList<Integer>();
        testDLL4.insertBefore(0,1);
        testDLL4.insertBefore(1,7777);
        testDLL4.insertBefore(2,16);
        testDLL4.deleteAt(2);
        assertEquals("Checking deleteAt on a list containing 3 elements - Expecting element at pos 1 to be removed", "1,7777", testDLL4.toString());

        DoublyLinkedList<Integer> testDLL5 = new DoublyLinkedList<Integer>();
        testDLL5.insertBefore(0,1);
        assertTrue("Checking deleteAt on a list containing 3 elements - Expecting element at pos 1 to be removed", testDLL5.deleteAt(0));
    }

    @Test
    public void testReverse()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,0);
        testDLL.insertBefore(1,1);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,3);
        testDLL.insertBefore(4,4);
        testDLL.insertBefore(5,5);
        testDLL.reverse();
        assertEquals("Checking reverse on a list containing 6 elements, Expecting change from \"0,1,2,3,4,5\" to \"5,4,3,2,1,0\"", "5,4,3,2,1,0", testDLL.toString());

        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0,0);
        testDLL2.reverse();
        assertEquals("Checking reverse on an empty list", "0", testDLL2.toString());

        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
        testDLL3.insertBefore(0,0);
        testDLL3.insertBefore(1,1);
        testDLL3.reverse();
        assertEquals("Checking reverse on a list with 2 elements", "1,0", testDLL3.toString());

        DoublyLinkedList<Integer> testDLL4 = new DoublyLinkedList<Integer>();
        testDLL4.insertBefore(0,0);
        testDLL4.reverse();
        assertEquals("Checking reverse on a list with only one element", "0", testDLL4.toString());

        DoublyLinkedList<Integer> testDLL5 = new DoublyLinkedList<Integer>();
        testDLL5.reverse();
        assertEquals("Checking reverse on an empty list", "", testDLL5.toString());


    }

    @Test
    public void testMakeUnique()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,3);
        testDLL.insertBefore(0,3);
        testDLL.insertBefore(0,3);
        testDLL.makeUnique();
        assertEquals("Checking makeUnique on a list with three elements, where no element is unique aka there are three of the same element - expecting two of the elements to be removed", "3", testDLL.toString());

        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0,3);
        testDLL2.makeUnique();
        assertEquals("Checking makeUnique on a list with only one element - Expecting zero change to the list", "3", testDLL2.toString());

        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
        testDLL3.insertBefore(0,7);
        testDLL3.insertBefore(1,1);
        testDLL3.insertBefore(2,5);
        testDLL3.insertBefore(3,5);
        testDLL3.insertBefore(4,7);
        testDLL3.makeUnique();
        assertEquals("Checking makeUnique on a list with 5 elements where there are two non-unique pairs - expecting the copies to be removed", "7,1,5", testDLL3.toString());

        DoublyLinkedList<Integer> testDLL4 = new DoublyLinkedList<Integer>();
        testDLL4.insertBefore(0, 1);
        testDLL4.insertBefore(1, 2);
        testDLL4.insertBefore(2, 2);
        testDLL4.insertBefore(3, 2);
        testDLL4.insertBefore(4, 1);
        testDLL4.insertBefore(5, 1);
        testDLL4.insertBefore(6, 2);
        testDLL4.makeUnique();
        assertEquals("Checking makeUnique on a list with 7 elements, where there are three elements with value 1, and four elements with value 2 - Expecting copies to be removed", "1,2", testDLL4.toString());

        DoublyLinkedList<Integer> testDLL5 = new DoublyLinkedList<Integer>();
        testDLL5.makeUnique();
        assertEquals("Checking makeUnique on an empty list, expecting no modifications to the list", "", testDLL5.toString());
    }

    @Test
    public void testPush()
    {
        DoublyLinkedList<Integer> stack = new DoublyLinkedList<Integer>();
        stack.push(15);
        assertEquals("Checking push on an empty list, pushing one value onto the stack - Expecting one element to be pushed onto the stack", "15", stack.toString());

        stack.push(23);
        assertEquals("Checking push on a list with one element, pushing one value onto the stack - Expecting the list to contain the first element pushed followed by the second element", "15,23", stack.toString());

        stack.push(47);
        assertEquals("Checking push on a list with two elements, pushing one value onto the stack - Expecting the list to contain the first two elements followed by the item we push", "15,23,47", stack.toString());

        stack.push(99);
        assertEquals("Checking push on a list with three elements, pushing one value onto the stack - Expecting the list to contain the first three elements followed by the item we push onto the stack", "15,23,47,99", stack.toString());
    }

    @Test
    public void testPop()
    {
        DoublyLinkedList<Integer> stack = new DoublyLinkedList<Integer>();
        assertEquals("Checking pop on an empty stack, expecting a return of null as there are no elements", null, stack.pop());
        stack.push(15);
        assertEquals("checking pop on a stack with only one element, expecting that element to be returned", "15", stack.pop().toString());
        stack.push(18);
        stack.push(23);
        assertEquals("Checking pop on a stack with two elements, expecting the most recent element to be popped following the LIFO rule", "23", stack.pop().toString());
        stack.push(19);
        stack.push(24);
        stack.push(37);
        assertEquals("Checking pop on a stack with four elements, expecting the most recent element to be popped following the LIFO rule", "37", stack.pop().toString());
    }

    @Test
    public void testEnqueue()
    {
        DoublyLinkedList<Integer> queue = new DoublyLinkedList<Integer>();
        queue.enqueue(15);
        assertEquals("Checking enqueue on an empty queue, enqueueing one item onto the queue", "15", queue.toString());

        queue.enqueue(24);
        queue.enqueue(37);
        assertEquals("Checking enqueue on a queue with one element, expecting two more items to be added onto the queue", "15,24,37", queue.toString());
    }

    @Test
    public void testDequeue()
    {
        DoublyLinkedList<Integer> queue = new DoublyLinkedList<Integer>();
        assertEquals("Checking dequeue on an empty list - expecting to return null", null, queue.dequeue());

        queue.enqueue(15);
        assertEquals("Checking dequeue on a queue with only one element, expecting said element to be dequeued and returned", "15", queue.dequeue().toString());

        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);
        assertEquals("Checking dequeue on a queue with five elements, expecting the first element to be returned following FIFO ruling", "14", queue.dequeue().toString());
        assertEquals("Checking that dequeue did not modify the rest of the queue after first element was dequeued following FIFO ruling", "15,16,17,18", queue.toString());
    }

    @Test
    public void testGetNode()
    {
        DoublyLinkedList<Integer> DLL = new DoublyLinkedList<Integer>();
        assertEquals(null, DLL.getNode(5));

    }

}

