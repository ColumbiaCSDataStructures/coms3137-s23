object SumList {


 /**
  * Sums the elements of a list of integers.
  */
  def sumList(li : List[Int]) : Int =     
    if (li == Nil) 
      0
    else li.head + sumList(li.tail)
   

 /**
  * Sums the elements of a list of integers
  * using tail recursion.
  *
  * Note the notation for total: This is a default parameter -- 
  *  when total is not passed as an argument, its value defaults
  *  to 0.
  */
  def sumListTail[T](li : List[Int], total : Int = 0) : Int = 
    if (li == Nil) 
      total
    else sumListTail(li.tail, total + li.head)


  def main(args : Array[String]) = {
    val testList = 1 :: 2 :: 3 :: 4 :: Nil;  // or val testList = List(1,2,3,4);
    println(sumList(testList)); // should print 10
    println(sumListTail(testList)); // should als print 10
  }

}
