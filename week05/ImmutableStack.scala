class ImmutableStack[T](data : List[T]) {

    def this() = this(Nil);

    def push(element : T) : ImmutableStack[T] = {    
      ImmutableStack[T](element :: data); 
    }

    def pop() : (T, ImmutableStack[T]) = {
      (data.head, ImmutableStack(data.tail)); 
    }

}

object ImmutableStack { // companion object

    def main(argv : Array[String]) : Unit = {
        val myStack = ImmutableStack[Int]();  

        val stack2 : ImmutableStack[Int] = myStack.push(1);
        val stack3 : ImmutableStack[Int] = stack2.push(2);

        val result = stack3.pop(); // result is an (Int, Stack[Int]) pair
        val popped = result._1; // gets first element of the pair 
        val stack4 = result._2; // gets second element of the pair 
        println(popped); // print 2
        println(stack4.pop()._1);  // print 1
    }

} 

