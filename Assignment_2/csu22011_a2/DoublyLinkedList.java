package csu22011_a2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Grainne Ready
 *  @version 06/11/22 @ 19:24
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    private static int length = 0;

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    public static void main(String[] args) {
        Integer[] input = {2, 0, 3, 3, 2, 7, 0, 6};
        DoublyLinkedList buffer = new DoublyLinkedList();
        for (int i = 0; i < input.length; i++)
        {
            buffer.insertBefore(100, input[i]);
            if (input[i] % 3 == 0)
                System.out.println(buffer.pop());
            else if (input[i] % 2 == 0)
                System.out.println(buffer.dequeue());
        }
        while (length > 0)
        {
            System.out.println(buffer.dequeue());
        }
    }


    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The sole line of code in the method is to return (head == null), an equal-to operation, which runs at a constant time.
     *
     */
    public boolean isEmpty()
    {
        return (head == null);
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The code consists of a line before the if-elseif-elseif-else statement, the if-elseif-elseif-else statement itself, and a line after the if-elseif-elseif-else statement
     *
     *  The line before the if-elseif-elseif-else statement is an assignment, which will run at Theta(1)
     *
     *  The if-elseif-elseif-else consists of:
     *  * The if-statement, with the condition isEmpty(), which will run at Theta(1)
     *    - Two assignments in the if statement, which each run at Theta(1). Theta(1) + Theta(1) = Theta(1)
     *    Thus the if statement will run at Theta(1) + Theta(1) = Theta(1)
     *
     *  * The first else-if statement, with the condition (pos <= 0), which has Theta(1) run time
     *    - Three assignments inside this else-if statement, which each run at Theta(1). Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *    Thus, the first else-if statement will run at Theta(1) + Theta(1) = Theta(1)
     *
     *  * The second else-if statement, with the condition (pos > length - 1), which will run at Theta(1)
     *    - Inside the second else-if statement are three assignments, which each run at Theta(1). Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *    Thus, the second else-if statement will run at Theta(1) + Theta(1) = Theta(1) time
     *
     *  * The else statement, which will check the last three conditions, which run at Theta(1)
     *    Inside the else statement are 5 assignments, one of which uses getNode(pos) which runs at Theta(N)
     *    The rest of the assignments run at Theta(1).
     *    Theta(N) + Theta(1) + Theta(1) + Theta(1) + Theta(1) = Theta(N)
     *
     *  Thus, the if-elseif-elseif-else statement has a worst case running time of Theta(N)
     *
     *  The line after the if-elseif-elseif-else statement increments length, which will run at Theta(1)
     *
     *  Therefore, the method's worst case running time is Theta(1) + Theta(N) + Theta(1) = Theta(N)
     */
    public void insertBefore( int pos, T data ) 
    {
        DLLNode newNode = new DLLNode(data, null, null);
        if (isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else if (pos <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        else if (pos > length - 1)
        {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        else {
            DLLNode tempNode = getNode(pos);
            tempNode.prev.next = newNode;
            newNode.prev = tempNode.prev;
            newNode.next = tempNode;
            tempNode.prev = newNode;
        }
        length++;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *  The code consists of an if statement, a nested if-else statement, and a return null statement after the if statement
     *  The nested if-else statement consists of the following:
     *  - The if part
     *    * condition (pos == 0) which will run at Theta(1) time
     *    * The inside of the if statement, which returns head.data which also runs at Theta(1)
     *    * Thus, the if part of the if-else statement will run at Theta(1) + Theta(1) = Theta(1) time.
     *  - The else if part
     *    * Condition (pos < length), which will run at Theta(1) time
     *    * A DLLNode creation which uses getNode(pos), which will run at Theta(N) time
     *    * A return statement, which will run at Theta(1) time
     *    * Thus the else if part of the if-else statement will run at Theta(1) + Theta(1) + Theta(N) + Theta(1) = Theta(N)
     *
     *  The line after the first if statement is a return statement, which runs at Theta(1) time
     *
     *  Furthermore, the method will run at Theta(1) + Theta(N) = Theta(N) at the worst case scenario
     *
     *
     */
    public T get(int pos) 
    {
        if (!isEmpty() && pos >= 0)
        {
            if (pos == 0)
                return head.data;
            else if (pos < length)
            {
                DLLNode nodeToGet = getNode(pos);
                return nodeToGet.data;
            }
        }
        return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The code consists of an if statement, an if-else statement inside that if statement, an if-else_if-else statement inside that if statement, an if statement, and a return statement
     *  Outside the if statement is a return statement
     *
     *  The if statement condition will run isEmpty(), which runs at Theta(1)
     *
     *  The code inside the if statement consists of the following:
     *  - An if-else statement which has "pos < 0 || pos > length - 1" as its condition, which runs at Theta(1)
     *    * The if part of this if-else statement has a return statement, which runs at Theta(1) time
     *    Therefore the if part of the nested if-else statement has Theta(1) + Theta(1) = Theta(1) run time
     *
     *    The else part of the nested if-else statement consists of the following:
     *    * An if-elseif-else statement
     *      The if part of this has "pos == 0" as its condition, which runs at Theta(1)
     *      - Inside the if part of this has an assignment, which runs at Theta(1)
     *      - Thus, the if part will run at Theta(1) + Theta(1) = Theta(1)
     *
     *      The else if part of this has "pos == length - 1" as its condition, which will also run at Theta(1)
     *      - Inside the if else statement are two assignments, which both run at Theta(1)
     *      - Thus, the else if part will run at Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *
     *      The else part consists of three assignments, two of which will run at Theta(1), the other will run at Theta(N)
     *      - Thus, the else part will run at Theta(1) + Theta(1) + Theta(N) = Theta(N)
     *
     *      Furthermore, the if-elseif-else statement will run at worst case of Theta(N)
     *   * Decrementing length, which runs at Theta(1)
     *   * An if statement to check if head != null, which will run at Theta(1)
     *   * A return statement, which also runs at theta(1)
     *
     *   Thus, the nested if-else statement will run at Theta(N) + Theta(1) + Theta(1) + Theta(1) = Theta(N) at worst case scenario
     *
     *   Outside the first if statement is a return statement, which runs at Theta(1)
     *
     *   Thus, the entire method's worst case scenario is Theta(1) + Theta(N) + Theta(1) = Theta(N) run time.
     *
     */
    public boolean deleteAt(int pos)
    {
        if (!isEmpty()) { //y to both
            if (pos < 0 || pos > length - 1) // FF FT TF
                return false;
            else {
                if (pos == 0) { // TF
                    head = head.next;
                }
                else if (pos == length - 1) { // TF
                    tail.prev.next = null;
                    tail = tail.prev;
                } else {
                    DLLNode tempNode = getNode(pos);
                    tempNode.next.prev = tempNode.prev;
                    tempNode.prev.next = tempNode.next;
                }
                length--;
                if (head != null) // T
                    head.prev = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The code consists of an if statement and the lines inside the if statement which also includes a while-loop
     *
     *  The if statement condition checks isEmpty() and length > 1, which both run at Theta(1) time
     *
     *  Inside the if statement is the following:
     *  - The creation of a DLLNode, which runs at Theta(1) time
     *  - A while loop which runs until the condition (currentNode != null) is false
     *    This while loop consists of the following:
     *    *  Four assignments, which all run at Theta(1)
     *    Thus each iteration of the while loop will be Theta(1) + Theta(1) + Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *    The while loop will run at Theta(N) (Theta(1) * Theta(N))
     *  - An assignment which runs at Theta(1) time
     *  Thus, the inside of the if statement will run at Theta(1) + Theta(N) + Theta(1) = Theta(N)
     *
     *  As a result, the methods worst case running time is Theta(1) + Theta(N) = Theta(N)
     */
    public void reverse()
    {
        if (!isEmpty() && length > 1)
        {
            DLLNode currentNode = head, temp = null, tail = head;
            while (currentNode != null)
            {
                temp = currentNode.prev;
                currentNode.prev = currentNode.next;
                currentNode.next = temp;
                currentNode = currentNode.prev;
            }
            head = temp.prev;
        }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements unique.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(N^2)
     *
     * Justification:
     *  The code consists of the lines before the while-loop, a while-loop with condition (node1 != null), a nested while-loop with condition (node2 != null)
     *
     *  The lines before the first while loop involve
     *  - An if statement which runs isEmpty(), which costs Theta(1)
     *  - Two creations of nodes, node1 and node2, which also cost Theta(1)
     *  - Two creations of integers, pos and startingPosition, which will take Theta(1) time to run
     *  Thus the code before the while-loop will take Theta(1) time to run.
     *
     *  The nested while loop will have the following in each iteration
     *  * An if statement that checks if node1.data == node2.data, if so, it will run deleteAt(pos). An if statement takes Theta(1), deleteAt costs Theta(N). Theta(1) + Theta(N) = Theta(N)
     *    If they are not equal, it will increment pos, which costs Theta(1)
     *  Therefore each iteration of the nested while-loop will cost Theta(N).
     *  Thus, the lines involving the nested while-loop will run in Theta(N)*Theta(1) = Theta(N).
     *
     *  The first while-loop itself will iterate over all elements of the list until node1 == null. Thus it will perform Theta(N) iterations.
     *  Each iteration will involve:
     *   * Assigning node2 = node1.next, which will cost Theta(1)
     *   * Assigning pos = startingPosition, which will also cost Theta(1)
     *   * A while loop iterating over all elements of the list except the head until node2 == null. which runs at Theta(N)
     *   * After the while loop ends, startingPosition is incremented which will cost Theta(1)
     *   * After the while loop ends, node1 = node1.next which will cost Theta(1)
     *   Thus, each iteration of the while-loop runs at Theta(1) + Theta(1) + Theta(N) + Theta(1) = Theta(N)
     *   As such, this while loop will cost Theta(N) * Theta(N) = Theta(N^2)
     *
     * Therefore, this method will run in Theta(1) + Theta(N) + Theta(N^2) = Theta(N^2) time in the worst case.
     *
     */
     public void makeUnique()
    {
        if (!isEmpty()) { // TT
            DLLNode node1 = head;
            DLLNode node2 = null;
            int pos = 0;
            int startingPosition = 1;
            while (node1 != null) { //T
                node2 = node1.next;
                pos = startingPosition;
                while (node2 != null) { //T
                    if (node1.data == node2.data) {
                        deleteAt(pos);

                    }
                    else pos++; //T
                    node2 = node2.next;
                }
                startingPosition++;
                node1 = node1.next;
            }
        }
    }

    /**
     * @return If the pos is within the list's bounds, we return the DLLNode at the pos described in the parameter
     *         If the pos is outside the list's bounds, we return null
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  The code consists of the lines before the for-loop, the for-loop itself and the return statement after the for-loop.
     *
     *  The lines before the for loop involve
     *  - the creation of a DLLNode object which does not depend on the length of the list. Therefore it takes Theta(1) time.
     *  - An if statement which checks if pos is greater than length - 1. Therefore it takes Theta(1) time
     *  - Inside the if statement is a return null statement, which also runs on Theta(1) time
     *  Thus the code before the for-loop will take Theta(1) time to run.
     *
     *  The line after the for-loop involves a single return instruction and thus take Theta(1) time.
     *
     *  The for-loop itself will iterate over all elements of the list until i < pos. Thus it will perform Theta(N) iterations.
     *  Each iteration will involve:
     *   * Assigning tempNode = tempNode.next, which will cost Theta(1)
     *  Therefore each iteration of the loop will cost Theta(1).
     *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
     *
     *
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) + Theta(1) = Theta(N) time in the worst case.
     *
     */
    public DLLNode getNode(int pos)
    {
        DLLNode tempNode = head;
        if (pos > length - 1)
            return null;
        for (int i = 0; i < pos; i++)
        {
            tempNode = tempNode.next;
        }
        return tempNode;
    }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The code consists of a line before the if-else statement, the if-else statement itself, and a line after the if-else statement
     *
     *  The line before the if-else statement is a creation of a DLLNode, which will run at Theta(1)
     *
     *  The if-else statement will include the following:
     *  - The condition (isEmpty()), which will run at Theta(1)
     *  - The if statements internal lines, which consist of the following:
     *    * Two assignments, which both run at Theta(1) time
     *    Thus the if part of the if-else statement will run at Theta(1) + Theta(1) = Theta(1)
     *  - The else statements internal lines, which consist of the following:
     *    * Two assignments, which both run at Theta(1) time
     *    Thus the else part of the if-else statement will run at Theta(1) + Theta(1) = Theta(1)
     *  The if-else statement will therefore run at Theta(1) time in the worst case
     *
     *  The line after the if-else statement increments length, which will run at Theta(1)
     *
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(1) = Theta(1) time in the worst case.
     *
     */
    public void push(T item) 
    {
        DLLNode newItem = new DLLNode(item, tail, null);
        if (isEmpty())
        {
            head = newItem;
            tail = newItem;
        }
        else
        {
            tail = newItem;
            tail.prev.next = newItem;
        }
        length++;
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty. (LIFO)
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The code consists an if statement, the lines inside the if statement which also includes an if-else statement, and a return null statement after the first if statement
     *
     *  The if statement's condition will run isEmpty(), which will cost Theta(1)
     *  Inside the if statement is the following:
     *  - The creation of T data, which will cost Theta(1)
     *  - An if-else statement, which consists of the following:
     *     * The if condition will check length == 1, which will run at Theta(1)
     *     * Inside the if statement part are two assignments, head = null and tail = null, which will also run at Theta(1)
     *     * The if part of the if-else statement will therefore run at Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *
     *     * The else condition will run three assignments, which will all run at Theta(1) time. Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *     Thus, the if-else statement will cost Theta(1) to run
     *
     *  - Decrementing length, which will run at Theta(1)
     *  - A return statement, which also runs at Theta(1)
     *  Thus, the if statement will run at Theta(1) + Theta(1) + Theta(1) + Theta(1) = Theta(1)
     *
     *  After the if statement is a return null statement which will run at Theta(1)
     *
     * Therefore, this method will run in Theta(1) + Theta(1) + Theta(1) = Theta(1) time in the worst case.
     *
     */
    public T pop() 
    {
        if (!isEmpty())
        {
            T data = head.data;
            if (length == 1) {
                head = null;
                tail = null;
            }
            else {
                data = tail.data;
                tail = tail.prev;
                if (tail != null)
                    tail.next = null;
            }
            length--;
            return data;
        }
      return null;
    }



    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  The code consists of a line before the if-else statement, and two lines after the if-else statement
     *
     *  The line before the if statement is the creation of a DLLNode, which runs at Theta(1)
     *
     *  The if statement condition will run isEmpty(), which runs at Theta(1)
     *  Inside the if statement is the following:
     *  - head = newItem, which will run at Theta(1)
     *  Thus, the if statement will run at Theta(1) + Theta(1) = Theta(1)
     *
     *  Inside the else statement is the following:
     *  - tail.next = newItem, which will run at Theta(1) time.
     *  Thus, the else statement will run at Theta(1)
     *
     *  After the if-else statement is the following:
     *  - tail = newItem, which will run at Theta(1)
     *  - head.prev = null, which will run at Theta(1)
     *  Thus, the lines after the if-else statement will run at Theta(1) + Theta(1) = Theta(1)
     *
     * Therefore, this method will run in Theta(1) + Theta(1) + Theta(1) = Theta(1) time in the worst case.
     *
     */
    public void enqueue(T item) 
    {
        DLLNode newItem = new DLLNode(item, tail, null);
        if (isEmpty())
        {
            head = newItem;
        }
        else
        {
            tail.next = newItem;
        }
        tail = newItem;
        head.prev = null;
        length++;
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an enqueue; or null when the list is empty. (FIFO)
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
      * Justification:
      *  The code consists of an if statement, inside this if statement is the creation of a T type called 'data', an if-else statement, and a return statement.
      *  Outside the if statement is a return null statement
      *
      *  The first if statement runs isEmpty(), which will cost Theta(1)
      *
      *  If the if statement is true, we have:
      *  - The creation of a type T called 'data', which is assigned the value of head.data, this will cost Theta(1)
      *  - An if-else statement which checks if tail.prev is null or not
      *      * Inside this if statement is two assignments, which will cost Theta(1) each
      *      * Thus, the if statement which checks if tail.prev is null will cost Theta(1) to run
      *      * The else part of the if-else statement involves setting head to null, which also runs on Theta(1) time.
      *    Thus, the if-else statement will run at Theta(1)
      *  - A return statement, which will run at Theta(1)
      *  The worst case running time of this if statement will be Theta(1) + Theta(1) + Theta(1) = Theta(1)
      *
      *  If the if statement is false, we have only one line, a return null statement, which will run at Theta(1)
      *
      *  Thus, this method will run at Theta(1) + Theta(1) + Theta(1) + Theta(1) = Theta(1)
      */
    public T dequeue() 
    {
        if (!isEmpty())
        {
            T data = head.data;
            if (tail.prev != null)
            {
                head = head.next;
                head.prev = null;
            }
            else head = null;
            length--;
            return data;
        }
        return null;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
     *
     *  The lines before the for loop involve 
     *  - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
     *  - the allocation and assignment of two variables which are constant time operations.
     *  Thus the code before the for-loop will take Theta(1) time to run.
     *
     *  The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
     *
     *  The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
     *  Each iteration will involve:
     *   * The if-conditional will run:
     *       - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
     *       - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
     *       - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
     *     Therefore the if-then-else conditions will cost Theta(1) in the worst case.
     *   * The final call to StringBuilder's append will cost again Theta(1)
     *   * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list). 
     *     Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
     *  Therefore each iteration of the loop will cost Theta(1).
     *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
     *
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
     *
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }

}



