object Functional {


  def double(li : List[Int]) : List[Int] = {

    if (li == Nil)
      Nil 
    else  
      2 * li.head :: double(li.tail)

  }


  def doubleEven(li : List[Int]) : List[Int] = {

    if (li == Nil)
      Nil 
    else  
      if (li.head % 2 == 0)
        2 * li.head :: doubleEven(li.tail)
      else
        doubleEven(li.tail)
  }

  def doubleEvenComprehension(li : List[Int]) =  {
    
    for (x : Int <- li; if x % 2 == 0) yield (x * 2)

  }


  def sumList(li : List[Int]) : Int = {
    
    if (li == Nil) 
      0 
    else 
      li.head + sumList(li.tail)
  }

/*
  def myFold(li : List[Int], baseCase : Int, combiner : (Int, Int) => Int ) = {

      if (li == Nil) 
        baseCase
      else 
        combiner( myFold(li.tail, baseCase, combiner), li.head )
  }*/


  def myMap[A, B](li : List[A], f : A => B) = {
    

    val combiner = (nextHead : A, newTail : List[B]) => f(nextHead) :: newTail; 

    li.foldRight(Nil : List[B])(combiner) 

  }


  def main(args : Array[String]) = {

    val li = List(1,2,3,4,5);

    //val res = li.foldRight("")((h : Int, tailresult : String) => h.toString() + tailresult);   

    val res = myMap(li, (a : Int) => a+2);
    println(res);

  }

}

