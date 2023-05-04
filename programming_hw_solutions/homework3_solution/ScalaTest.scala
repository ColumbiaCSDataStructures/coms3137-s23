import Bst.tree;
import Bst.attach;
import Bst.contains;
import Bst.delete;
import Bst.insert;
import Bst.intersection;
import Bst.union;

import scala.collection.immutable.Set; 

object ScalaTest {


  def compare_tree(t1 : Option[TreeNode], t2 : Option[TreeNode]) : Boolean = {
    
     (t1, t2) match {
        case (None, None) => true;
        case (Some(t1root), Some(t2root)) => t1root.data == t2root.data && 
                                         compare_tree(t1root.left, t2root.left) && 
                                         compare_tree(t1root.right, t2root.right);
        case _ => false;
     }
          

  }


  def tree_to_list(t : Option[TreeNode]) : List[Int] = {
    
    t match {
      case None => Nil 
      case Some(root) => root.data :: (tree_to_list(root.left) ++ tree_to_list(root.right))
    }
        

  }

  //attach
  def test_attach1 : Boolean = {

    val t1 : Option[TreeNode] = tree(3, tree(1));
    val t2 : Option[TreeNode] = tree(6, tree(5), tree(8, tree(7)));
   
    val target : Option[TreeNode] = tree(6, tree(5, tree(3, tree(1))), tree(8, tree(7))); 

    val student = attach(t1, t2)
    compare_tree(target, student);
  }
  

  def test_attach2 : Boolean = {

    val t1 : Option[TreeNode] = tree(1);
    val t2 : Option[TreeNode] = tree(3);
   
    val target : Option[TreeNode] = tree(3, tree(1));

    val student = attach(t1, t2)
    compare_tree(target, student);
  }


  // contains
  def test_contains1 : Boolean = {
    val t2 : Option[TreeNode] = tree(6, tree(5), tree(8, tree(7)));
    return contains(t2, 8);
  }
  
  def test_contains2 : Boolean = {
    val t : Option[TreeNode] = tree(8, tree(5), tree(9, tree(7)));
    return !contains(t, 4);
  }

  // delete
  def test_delete: Boolean = {
    val t : Option[TreeNode] = tree(7, tree(5), tree(8, tree(6), tree(9)));
    val new_t = delete(t, 8);
    val values : List[Int] = tree_to_list(new_t);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(5,6,7,9)
  }

  
  // insert 
  def test_insert1: Boolean = {
    val t : Option[TreeNode] = tree(7, tree(4), tree(8, tree(5), tree(9)));
    val new_t = insert(t, 6);
    val values : List[Int] = tree_to_list(new_t);
    val set = values.toSet 

    set.size == values.size && set == Set(4,5,6,7,8,9)
  }
  
  def test_insert2: Boolean = {
    val t : Option[TreeNode] = tree(7)
    val new_t = insert(t, 8);
    val values : List[Int] = tree_to_list(new_t);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(7,8)
  }


  // union
  def test_union1 : Boolean = {
     
    val t1 : Option[TreeNode] = tree(3, tree(2, tree(1)), tree(5));
    val t2 : Option[TreeNode] = tree(4, tree(1), tree(7, tree(5))); 

    val t3 = union(t1,t2);

    val values : List[Int] = tree_to_list(t3);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(1,2,3,4,5,7)
  }

  def test_union2 : Boolean = {
     
    val t1 : Option[TreeNode] = tree(3)
    val t2 : Option[TreeNode] = tree(4)

    val t3 = union(t1,t2);

    val values : List[Int] = tree_to_list(t3);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(3,4);
  }

  // intersection 
  def test_intersection1 : Boolean = {
     
    val t1 : Option[TreeNode] = tree(3, tree(2, tree(1)), tree(5));
    val t2 : Option[TreeNode] = tree(4, tree(1), tree(7, tree(5))); 

    val t3 = intersection(t1,t2);

    val values : List[Int] = tree_to_list(t3);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(1,5);

  }
  
  def test_intersection2 : Boolean = {
     
    val t1 : Option[TreeNode] = tree(3);
    val t2 : Option[TreeNode] = tree(3);

    val t3 = intersection(t1,t2);

    val values : List[Int] = tree_to_list(t3);
    val set : Set[Int] = values.toSet 

    set.size == values.size && set == Set(3);

  }
 
 
  def main(args : Array[String]) = {
    
    println(test_attach1);   
    println(test_attach2);   

    println(test_contains1);   
    println(test_contains2);   
   
    println(test_delete);

    println(test_insert1);
    println(test_insert2);
    
    println(test_union1);
    println(test_union2);

    println(test_intersection1);
    println(test_intersection2);
 
  } 


}

