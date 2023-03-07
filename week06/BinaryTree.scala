/** 
 * A simple binary tree that stores strings. 
 * This is an abstract base class for the concrete case classes Node and Leaf.
 */
abstract class BinaryTree[A]{ 
 
    /** 
     * A higher-order generalization for tree operations.
     *                   
     * This method implements tree traversal as an 
     * abstraction over different tree operations.
     * Tree operations can be implemented by creating function objects
     * proc_node and proc_leaf and passing them to traverse.
     * The abstract method is implemented in Node and Leaf. 
     */                     
    def traverse[B](proc_node : (B, B, A) => B, proc_leaf : A=>B) : B = {
      
      this match {
        case Leaf(content) => proc_leaf(content); 
        case Node(content, left, right) => proc_node(left.traverse(proc_node, proc_leaf),
                                                right.traverse(proc_node, proc_leaf),
                                                content);
      }

    } 

}

/** 
 * A node with exactly two subtrees. 
 */ 
case class Node[A](val content: A, val left: BinaryTree[A], val right: BinaryTree[A]) extends BinaryTree[A] {

     /** 
      * The traverse implementation for Node calls proc_node on the 
      * results returned by calling traverse recusively on each 
      * subtree and the content of this node. 
      */

}

/** 
 * A node that does not have any further subtrees (i.e. a single leaf node).
 */
case class Leaf[A](val content : A) extends BinaryTree[A] {
    /** 
     * The traverse implementation for Leaf calls proc_laf on the content of
     * the node. proc_leaf usually just converts the content into the correct
     * result type.
     */
}

object BinaryTree {


  def postorder(t : BinaryTree[String]) : String = {
    
    t match {
      case Leaf(c) => c;  
      case Node(c, l, r) => postorder(l) + postorder(r) + c; 
    }

  }

  /*def sumList(li : List[Integer]) : Integer = {

    if (li == Nil) 
      0
    else 
      sumList(li.tail) + li.head;

    li match {
      case Nil => 0;
      case ::(head, tail)  => sum_list(tail) + head; 
    }

  }*/



  def main(args : Array[String]) = {

     val t : BinaryTree[Int]  = Node(2, Leaf(3), Node(1, Leaf(2), Leaf(2)));

     val tsum = t.traverse((parent : Int , lsum : Int, rsum : Int) => parent + lsum + rsum, (a : Int) => a); 

     println(tsum); 
  }

}
