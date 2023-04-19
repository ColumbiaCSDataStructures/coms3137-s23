class TwoStackQueue[A](s1 : List[A], s2 : List[A]) {

  def this() = this(Nil, Nil) // auxiliary constructor

  def enqueue(x : A) : TwoStackQueue[A] = {
    new TwoStackQueue(x :: s1, s2)
  } 

  def dequeue() : (A, TwoStackQueue[A]) = {
    s2 match {
      case Nil => { 
                    val reversed_s1 = s1.foldLeft (List[A]()) ((subresult, head) => head :: subresult)
                    (reversed_s1.head, new TwoStackQueue(Nil, reversed_s1.tail))
                  }

      case head :: tail => (head, new TwoStackQueue(s1, tail)) 
    }
  } 

}


object TwoStackQueue {

  
  def main(args : Array[String]) = {
      val q1  = new TwoStackQueue[Int]();
    
      val q2 = q1.enqueue(1);
      val q3 = q2.enqueue(2);

      
      val (x,q4) = q3.dequeue()
      println(x);  // Should print 1

      val q5 = q4.enqueue(3);

      val (y,q6) = q5.dequeue()
      println(y); // Should print 2 
      val (z,q7) = q6.dequeue()
      println(z); // Should print 3

  }  



}
