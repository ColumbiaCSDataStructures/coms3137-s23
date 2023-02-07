object Hanoi {

  /** 
   * Solve the towers of hanoi problem for n disks. Return the 
   * number of individual moves required. 
   * Print out the actual moves. 
   */
  def hanoi(n : Int, source : String, buffer : String, target : String) : Int = {

    if (n == 1) {
      println("Move " + source + " to "+target);
      1;
    } else {
      // step 1: Move top n-1 disks from source to buffer, using the original
      //   buffer as the target
      val step1 = hanoi(n-1, source, target, buffer);     

      // step 2: move bottom disk from source to target
      println("Move " + source + " to "+target);

      // step 3: move n-1 disks from buffer to taget
      val step2 = hanoi(n-1, buffer, source, target);     

      step1 + 1 + step2;
    }
  }
  
  def main(args : Array[String]) = {
    
    println(hanoi(6, "A", "B", "C"));

  }


}
