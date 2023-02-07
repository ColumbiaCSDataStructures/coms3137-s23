public class Fibonacci { 


  public static int fib(int k) {

    System.out.println(k);
    if (k == 1 | k == 2)
      return 1;
    else 
      return fib(k-1) + fib(k-2);
  }

  public static long fib_iter(int k) {

    long a = 1;  // k-2'nd fib number 
    long b = 1;  // k-1'st fib number

    if (k == 1 | k == 2)
      return 1;
   
    long c;
    for (int i = 3; i <= k; i++) {
      c = a + b; 
      a = b; 
      b = c; 
      System.out.println(c);
    } 
    return c; 
    
  }

  public static void main(String[] args) {
    System.out.println(fib_iter(50));
  }


}
