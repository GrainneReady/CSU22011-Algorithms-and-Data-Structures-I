package csu22011_a3;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 29/11/22 23:37
 *
 *  @author  Grainne Ready
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                                   //  -7
                                   //   |-3
                                   //   | |-1
                                   //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                                   //   |   |    -null
                                   //   |    -null
                                   //    -8
                                   //     |-null
                                   //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

     @Test
     public void testPut() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.put(1, null);
         assertTrue("Checking put with null value on an empty tree", bst.isEmpty());

         bst.put(1, 1);
         assertEquals("Adding a value to an empty tree with put", "(()1())", bst.printKeysInOrder());

         bst.put(1, 1);
         assertEquals("Checking put using a duplicate key/value pair", "(()1())", bst.printKeysInOrder());

         BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
         bst2.put(7, 7);
         bst2.put(8, 8);
         bst2.put(3, 3);
         bst2.put(1, 1);
         bst2.put(2, 2);
         bst2.put(6, 6);
         bst2.put(4, 4);
         bst2.put(5, 5);


         /* BST 2 VISUAL
                  _7_
                 /   \
               _3_    8
             /     \
            1       6
             \     /
              2   4
                   \
                    5
         */
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst2.printKeysInOrder());
     }
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);
         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);


         /* BST VISUAL
                  _7_
                 /   \
               _3_    8
             /     \
            1       6
             \     /
              2   4
                   \
                    5
         */
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());

         BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
         bst2.put(7, 7);
         bst2.put(8, 8);
         bst2.put(3, 3);
         bst2.put(1, 1);
         bst2.put(0, 0);
         bst2.put(6, 6);
         bst2.put(4, 4);
         bst2.put(5, 5);

         /* BST 2 VISUAL
                 _7_
                /   \
              _3_    8
             /   \
            1     6
           /     /
          0     4
                 \
                  5
         */
         bst2.delete(3);
         assertEquals("Deleting key that belongs to node with left child but no right child", "(((()0())1((()4(()5()))6()))7(()8()))", bst2.printKeysInOrder());

         BST<Integer, Integer> bst3 = new BST<Integer, Integer>();
         bst3.put(50, 50);
         bst3.put(40, 40);
         bst3.put(60, 60);
         bst3.put(45, 45);
         bst3.put(30, 30);
         bst3.put(20, 20);
         bst3.put(10, 10);
         bst3.put(31, 31);
         bst3.put(32, 32);
         bst3.put(33, 33);

         /* BST 3 VISUAL
                 _50_
                /    \
               _40_   60
              /    \
             _30_   45
            /    \
           20     31
          /        \
         10         32
                     \
                      33
         */

         bst3.delete(40);
         assertEquals("Deleting key that has two children, where the right child has three larger children and the left child has one smaller child", "(((((()10())20())30(()31(()32())))33(()45()))50(()60()))", bst3.printKeysInOrder());


     }
     @Test
     public void testHeight()
     {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         assertEquals("Checking height of empty tree", -1, bst.height());
         /* BST VISUAL
                   7

         */
         bst.put(7, 7);
         assertEquals("Checking height of a tree with one node", 0, bst.height());
         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         /* BST VISUAL
                   _7_
                 /     \
               _3_      8
             /     \
            1       6
             \     /
              2   4
                   \
                    5
         */
         assertEquals("Checking height of a tree with 8 nodes", 4, bst.height());

         bst.put(11, 11);
         /* BST UPDATED VISUAL
                 _7_
               /     \
             _3_      8
           /     \     \
          1       6     11
           \     /
            2   4
                 \
                  5
         */
         assertEquals("Checking height of a tree with 9 nodes", 4, bst.height());

         bst.put(9, 9);
         bst.put(13, 13);
         bst.put(22, 22);
         bst.put(24, 24);
         /* BST UPDATED VISUAL
                 _7_
               /     \
             _3_      8
           /     \     \
          1       6     11
           \     /     /  \
            2   4     9    13
                 \          \
                  5          22
                              \
                               24
         */
         assertEquals("Checking height of a tree with 14 nodes", 5, bst.height());
     }
     @Test
     public void testMedian() {
         BST<Integer, Integer> bst = new BST();
         assertEquals("Checking median of empty tree", null, bst.median());

         bst.put(1, 1);
         assertEquals("Checking median with tree containing a single node, 1", "1", (bst.median()).toString());

         bst.put(2, 2);
         bst.put(3, 3);
         bst.put(4, 4);
         bst.put(6, 6);
         bst.put(7, 7);
         bst.put(5, 5);
         assertEquals("Checking median with a tree containing multiple nodes, specifically 7", "4", (bst.median()).toString());

         BST<Integer, Integer> bst3 = new BST<Integer, Integer>();
         bst3.put(50, 50);
         bst3.put(40, 40);
         bst3.put(60, 60);
         bst3.put(45, 45);
         bst3.put(30, 30);
         bst3.put(20, 20);
         bst3.put(10, 10);
         bst3.put(31, 31);
         bst3.put(32, 32);
         bst3.put(33, 33);
         assertEquals("Checking median with a tree containing multiple nodes, specifically 10", "32", (bst3.median()).toString());
     }

     @Test
    public void testGet() {
         BST<Integer, Integer> bst = new BST();

         assertEquals("Checking get against an empty tree", null, bst.get(1));

         bst.put(7, 7);
         assertEquals("Checking get against a tree containing a single node, which is the root", "7", (bst.get(7)).toString());

         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         assertEquals("Checking get on a node which is the left child of the root, but has no children", "3", (bst.get(3)).toString());
         assertEquals("Checking get on a node in the left subtree, which has a child that is a leaf (leaf = no children)", "1", (bst.get(1)).toString());
         assertEquals("Checking get on a node which is a leaf in the left subtree", "2", (bst.get(2)).toString());
         assertEquals("Checking get on a node which is the right child of the root and is a leaf", "8", (bst.get(8)).toString());
         assertEquals("Checking get on a node which is a child of a node and greater than its parent node's value'. It is also a leaf", "6", (bst.get(6)).toString());
         assertEquals("Checking get on a node which is a child of a node and less than its parent node's value. It also has a child", "4", (bst.get(4)).toString());
         assertEquals("Checking get on a node which is a leaf, and greater than its parent node's value", "5", (bst.get(5)).toString());

         assertEquals("Checking get when the key does not exist in the tree", null, bst.get(-1));

     }

     @Test
    public void testContains() {
         BST<Integer, Integer> bst = new BST();
         assertFalse("Checking contains against an empty tree", bst.contains(1));
         bst.put(7, 7);
         bst.put(8, 8);
         bst.put(3, 3);
         bst.put(1, 1);
         bst.put(2, 2);
         bst.put(6, 6);
         bst.put(4, 4);
         bst.put(5, 5);
         assertTrue("Checking get on a node which is the left child of the root, but has no children", bst.contains(3));
         assertTrue("Checking get on a node in the left subtree, which has a child that is a leaf (leaf = no children)", bst.contains(1));
         assertTrue("Checking get on a node which is a leaf in the left subtree", bst.contains(2));
         assertTrue("Checking get on a node which is the right child of the root and is a leaf", bst.contains(8));
         assertTrue("Checking get on a node which is a child of a node and greater than its parent node's value'. It is also a leaf", bst.contains(6));
         assertTrue("Checking get on a node which is a child of a node and less than its parent node's value. It also has a child", bst.contains(4));
         assertTrue("Checking get on a node which is a leaf, and greater than its parent node's value", bst.contains(5));

         assertFalse("Checking get when the key does not exist in the tree", bst.contains(-1));
     }
}

