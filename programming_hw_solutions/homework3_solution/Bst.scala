class TreeNode (val data : Int, val left : Option[TreeNode], val right: Option[TreeNode]) {

    def printTree() : String = 
        (left, right) match {
            case (None, None) => data.toString;
            case (Some(l), None) => "(" + data.toString() + " " + l.printTree()+")";
            case (None, Some(r)) => "(" + data.toString() + " * " + r.printTree()+")";
            case (Some(l), Some(r)) => "(" + data.toString() + " "+l.printTree()+ " " + r.printTree() +")";
        }

    /** 
     * Print the set represented by this BST. 
     */ 
    override def toString() : String = "{"+inorder()+"}" 

    def inorder() : String = 
        (left, right) match {
            case (None, None) => data.toString() 
            case (Some(l), None) => l.inorder() + " " + data.toString() 
            case (None, Some(r)) => data.toString() + " " + r.inorder() 
            case (Some(l), Some(r)) => l.inorder() + " " + data.toString() + " " + r.inorder() 
        }

}

object Bst {
    /** 
     * Helper factory methods
     */  
    def tree(value : Int, left : Option[TreeNode], right : Option[TreeNode]) : Option[TreeNode]= {
        Some(new TreeNode(value , left, right));
    }
    
    def tree(value : Int, left : Option[TreeNode]) : Option[TreeNode] = {
        tree(value, left, None)
    }
    
    def tree(value : Int ) : Option[TreeNode] = {
        tree(value, None, None) 
    }

    /**
     * Returns two BSTs (A,B) such that A contains all keys less than x and B 
     * contains all keys greater than x. The additional boolean indicates if the value 
     * x appeared in the original tree or not. 
     */ 
    def split(tree: Option[TreeNode], x : Int) : (Option[TreeNode], Option[TreeNode], Boolean) = 
        tree match  {

            case None => (None, None, false)

            case Some(root) => {
                if (root.data == x)
                    (root.left, root.right, true)
                
                else if (x > root.data) {
                    val (rLeft, rRight, b) = split(root.right,x) 
                    (Some(new TreeNode(root.data, root.left, rLeft)), rRight, b | false)
                }
                else {
                    val (lLeft, lRight, b) = split(root.left, x)
                    (lLeft, Some(new TreeNode(root.data, lRight, root.right)), b | false)
                }
            }   
        }

    /** 
     * Assume that all keys in t2 are greater than keys in t1. 
     * Attach t1 to t2 in the appropriate position. 
     */ 
    def attach(t1: Option[TreeNode], t2: Option[TreeNode]) : Option[TreeNode]= {
      
      
      t2 match {
        
        case None => t1

        case Some(t2root) => {
            Some(new TreeNode(t2root.data, attach(t1, t2root.left), t2root.right))
        }        

      } 
    }

    /**
     * Return true if x is a key in the BST. 
     */ 
    def contains(tree : Option[TreeNode], x : Int) : Boolean = {
      val (left, right, b) =  split(tree, x)
      b
    }

    /**
     * Return a new BST in which x has been deleted. 
     */
    def delete(tree : Option[TreeNode], x : Int)  : Option[TreeNode] = {
        val (left, right, b) = split(tree, x)
        attach(left, right)
    }

    /**
     * Return a new BST with x inserted. 
     */
    def insert(tree : Option[TreeNode], x : Int) : Option[TreeNode] = {
       val (left, right, b) = split(tree, x) 
       Some(new TreeNode(x, left, right))
    }

    /**
     * Return a new BST that represents the union of t1 and t2. 
     */ 
    def union(t1 : Option[TreeNode], t2 : Option[TreeNode]) : Option[TreeNode] = {
      (t1, t2) match {
        case (None, Some(tr)) => Some(tr)
        case (Some(tl), None) => Some(tl)
        case (Some(tl), Some(tr)) => {
          val (left, right, b) = split(t2, tl.data)
          Some(new TreeNode(tl.data, union(tl.left, left), union(tl.right, right)))
        }
        case _ => None
      }
    }
    
    /**
     * Return a new BST that represents the intersection of t1 and t2. 
     */ 
    def intersection(t1 : Option[TreeNode], t2 : Option[TreeNode]) : Option[TreeNode] = {
      (t1, t2) match {
        case (None, Some(tr)) => None 
        case (Some(tl), None) => None 
        case (Some(tl), Some(tr)) => {
          val (left, right, b) = split(t2, tl.data)
          if (b)  // b exists in both trees, include it in the result
            Some(new TreeNode(tl.data, intersection(tl.left, left), intersection(tl.right, right)))
          else  
            attach(intersection(tl.left, left), intersection(tl.right, right))
        }
        case _ => None
      }
    }
      
    def main(args : Array[String]) = {
        val t : Option[TreeNode] = tree(6, tree(3, tree(1), tree(5)), tree(8,tree(7))); 
        val (t1, t2, b) = split(t,4)
        /*println(t1.get);
        println(t1.get.printTree())
        println(t2.get);
        println(t2.get.printTree())

        val t3 = attach(t1, t2);
        println(t3.get.printTree());
        println(t3.get);
        */

        val t4 : Option[TreeNode] = tree(8, tree(5, tree(3), tree(6)), tree(10)); 
        println(intersection(t,t4))
        //println(union(t,t4));
        val (t4left, t4right, x) = split(t4, 6)
        //println(t4left)
        //println(t4right)

    } 

}

