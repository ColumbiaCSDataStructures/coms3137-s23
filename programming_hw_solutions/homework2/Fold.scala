object Fold {

  
  /**
   * Count the number of times that element appears in list li
   */ 
  def countFold[T](li : List[T], element : T) : Int = { 

      // your solution MUST use either foldLeft or foldRight
      li.foldRight(0)((current : T , count : Int) =>  if (current == element) count + 1 else count);
  }
  

  /**
   * Return a reversed version of the list li. 
   */ 

  def reverseFold[T](li : List[T]) : List[T] = { 

      // your solution MUST use either foldLeft or foldRight
      li.foldRight(Nil)((curr, subresult : List[T]) => curr :: subresult)

  }
  

  def main(args : Array[String]) = {

    val testList = List(1,2,2,3,4,2,1);

    val count = countFold(testList, 2); 
    println(count); // should print 3 

    val reversed = reverseFold(testList);
    println(reversed);  // should print 1 2 4 3 2 2 1 
    

  


  }



}
