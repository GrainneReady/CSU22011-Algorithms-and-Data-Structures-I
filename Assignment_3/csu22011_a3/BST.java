/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.1 29/11/22 23:37
 *
 *  @author Grainne Ready
 *
 *************************************************************************/
package csu22011_a3;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(n)
     * Recursive function, will visit each node only once.
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
        if (isEmpty())
            return -1;
        return height(root);
    }
    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right)); // Math.max to get the largest height of the child trees as needed
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty())
          return null;
      int medianIndex = ((root.N + 1) / 2) - 1;
      return select(medianIndex);
    }

    /**
     * Select key.
     * Will return the key of the Node which corresponds to the index in the parameter.
     * Makes calls to a private select(Node, index) function which navigates the tree to find the node at the given index.
     * @param index
     * @return the key of the node at the index
     */
    private Key select(int index) {
        Node node = select(root, index);
        return node.key;
    }

    /**
     * Select Node
     * Will return the Node at a given index, navigates through the tree recursively until it locates the Node at the given index.
     * @param node
     * @param index
     * @return the node at the index
     */
    private Node select(Node node, int index) {
        int leftSize = size(node.left);
        if (leftSize > index)
            return select(node.left, index);
        else if (leftSize < index)
            return select(node.right, index - leftSize - 1);
        return node;
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty())
          return "()";
      return printKeysInOrder(root);
    }

    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    private String printKeysInOrder(Node root) {
        if (root != null)
            return "(" + printKeysInOrder(root.left) + root.key + printKeysInOrder(root.right) + ")";
        return "()";
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
        if (isEmpty())
            return "-null\n";
      else return prettyPrintKeys(root, "");
    }

    private String prettyPrintKeys(Node node, String prefix)
    {
        if (node == null)
            return prefix + "-null\n";
        String nodeKeyFormatted = "-" + node.key + "\n";
        return prefix + nodeKeyFormatted + prettyPrintKeys(node.left, (prefix + " |")) + prettyPrintKeys(node.right, prefix + "  ");
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
      root = delete(root, key);
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     * @param node the node used to delete the key
     * @return node excluding the deleted key
     */

    private Node delete(Node node, Key key)
    {
        if (node == null)
            return null;
        int comparison = key.compareTo(node.key);
        if (comparison > 0)
            node.right = delete(node.right, key);
        else if (comparison < 0)
            node.left = delete(node.left, key);
        else {
            // If node lacks a child node
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            //If node has two children nodes
            Node tempNode = node;
            Node maxNode = max(tempNode.left);
            if (maxNode.right == null) // If the max node in the left subtree has no right leaf
                node = maxNode;
            else { // Else (if the max node in the left subtree has a right leaf)
                node = maxNode.right;
                maxNode.right = node.left;
                node.left = tempNode.left;
            }
            node.right = tempNode.right;

        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Finds the maximum node in the tree (aka, the largest node to the right of the node in the parameter
     * @param node parent node which we want to find the largest node from
     * @return node the largest node in the child trees of the parent tree, or the parent itself if it has no children
     */
    private Node max(Node node)
    {
        Node temp = node;
        if (temp.right != null) {
            while (temp.right.right != null) {
                temp = temp.right;
            }
        }
        return temp;
    }


}
