import java.lang.Math; 

/**
 * Data Structures in Java 
 * COMS W3137, Columbia University - Spring 2022 
 * Basic structure of a binary tree.
 */
public class BinaryTree<T> {

    // The BinaryTree is essentially just a wrapper around the linked 
    // structure of BinaryNodes, rooted in root.
    protected BinaryNode<T> root;

    /**
     * Represents a binary subtree.
     */
    protected static class BinaryNode<T>{

        public T             data;  // the data 
        public BinaryNode<T> left;  // left subtree
        public BinaryNode<T> right; // right subtree
    
        /**
         * Construct a new binary node. 
         */
        public BinaryNode( T theData, BinaryNode<T> leftChild, 
                                      BinaryNode<T> rightChild ) {
            data    = theData; 
            left    = leftChild;
            right   = rightChild;
        }

        public BinaryNode(T theData) {
            data = theData;
            left = null;
            right = null;
        }


    } // Nested class BinaryNode ends here.
   
 
    /**
     * Construct a new empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a new BinaryTree wrapper around the BinaryNode rootNode.
     */
    public BinaryTree(BinaryNode<T> rootNode) {
        root = rootNode;
    }


    /** 
     * Factory method that creates a new BinaryTree with two subtrees, that contains theItem
     * as the data object attached to its root.  
     * The two btree methods make it possible to easily construt binary trees like this: 
     * BinaryTree<Integer> t = btree(1,btree(2,btree(3),btree(4)),btree(5));
     * @return a new BinaryTree with two children.  
     */ 
    public static <T> BinaryTree<T> btree(T theItem, BinaryTree<T> t1, BinaryTree<T> t2) {
        BinaryNode<T> root = new BinaryNode<>(theItem, t1.root, t2.root);
        return new BinaryTree<>(root); 
    }
    
    /**
     * Factory method that creates a new BinaryTree with no children, which contains 
     * theItem as data object attached to its root.
     * @return a new BinaryTree with no children.
     */
    public static <T> BinaryTree<T> btree(T theItem) {
        return new BinaryTree<T>(new BinaryNode<T>(theItem));
    }

    public String toString() {
        if (root == null) 
            return "()";
        else 
            return root.toString();
    }

  
    /**
     * Test method: Create and print a BinaryTree. 
     */ 
    public static void main(String[] args) {
        
        
         BinaryNode<Integer> n1 = new BinaryNode<>(5); 
         BinaryNode<Integer> n2 = new BinaryNode<>(6); 
         BinaryNode<Integer> n3 = new BinaryNode<>(4, n1, n2); 
         BinaryNode<Integer> n4 = new BinaryNode<>(8);
         BinaryNode<Integer> n5 = new BinaryNode<>(3, n3, n4);
         BinaryTree<Integer> tree = new BinaryTree<>(n5);
        

         //BinaryTree<Integer> tree = btree(3, btree(4, btree(5), btree(6)), btree(8)); 


        /*                         3
         *                     4       8
         *                  5    6
         * 
         */
                     
    }

}
