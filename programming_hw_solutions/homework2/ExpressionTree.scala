/** 
 * A simple binary tree that stores strings. 
 * This is an abstract base class for the concrete case classes Node and Leaf.
 */
abstract class ExpressionTree { 
 
    val content : String

    // methods for parts (b), (c), and (d)
    def postfix() : String = {
      val combiner = (left : String, right : String, content : String) => left + " " +
                                                                          right + " " + content;
      val extractor = (content : String) => content;
      traverse(combiner, extractor); 
    } 

    def infix() : String = {
      val combiner = (left : String, right : String, content : String) => "("+left + " " + content +
                                                                             " " + right + ")";
      val extractor = (content : String) => content;
      traverse(combiner, extractor); 
    }

    def eval() : Double = {
      val extractor = (content : String) => content.toDouble;
      val combiner = (left : Double, right: Double, content : String) => content match {
                        case "+" => left + right 
                        case "-" => left - right
                        case "*" => left * right
                        case "/" => left / right
                        case _ => throw new IllegalArgumentException
                     }
      traverse(combiner, extractor); 
    }  

    /** 
     * A higher-order generalization for tree operations.
     *                   
     * This method implements tree traversal as an 
     * abstraction over different tree operations.
     * Tree operations can be implemented by creating function objects
     * proc_node and proc_leaf and passing them to traverse.
     * The abstract method is implemented in Node and Leaf. 
     */                     
    def traverse[A](proc_node: (A,A,String) => A, proc_leaf: String=>A) : A 

}

object ExpressionTree {

    // method for part (a)
    def apply(expression : String ) : ExpressionTree = {
      
      var stack : List[ExpressionTree] = List(); 
      
      for (symbol : String <- expression.split(" ")){
      
        if (symbol == "+" | symbol == "-" | symbol == "*" | symbol == "/") {
          val right = stack.head
          stack = stack.tail
          val left = stack.head
          stack = stack.tail
          stack = Node(symbol, left, right) :: stack
        } else {
          stack = Leaf(symbol) :: stack
        }
      
      } 
      stack.head // pop the final result 
    }  


    // You can run ExpressionTree to test your code.
    def main(args : Array[String]) = {

        // Uncomment to test part (a)
        val tree : ExpressionTree = ExpressionTree("3 5 6 * + 7 -"); 
            
        // Uncomment to test part (b)
        println(tree.postfix())    


        // Uncomment to test part (c)
        println(tree.infix())    
    
        // Uncomment to test part(d) 
        println(tree.eval())
    }


}


/** 
 * A node with exactly two subtrees. 
 */ 
case class Node(val content: String, val left: ExpressionTree, val right: ExpressionTree) extends ExpressionTree {

     /** 
      * The traverse implementation for Node calls proc_node on the 
      * results returned by calling traverse recusively on each 
      * subtree and the content of this node. 
      */
    def traverse[A](proc_node : (A,A,String) =>A, proc_leaf: String=>A) = 
            proc_node(left.traverse(proc_node, proc_leaf), 
                     right.traverse(proc_node, proc_leaf),
                     content)
}

/** 
 * A node that does not have any further subtrees (i.e. a single leaf node).
 */
case class Leaf(val content : String) extends ExpressionTree {
    /** 
     * The traverse implementation for Leaf calls proc_laf on the content of
     * the node. proc_leaf usually just converts the content into the correct
     * result type.
     */
    def traverse[A](proc_node : (A,A,String)=>A, proc_leaf: String=>A) =
        proc_leaf(content) 
}

