import java.util.Deque;
import java.util.LinkedList;

public class JavaDequeTest {


  public static void main(String[] args) {

    Deque<Integer> q = new LinkedList<>(); 
    q.offerLast(1);
    q.offerLast(2);
    q.offerLast(3);
    System.out.println(q.pollLast());
    System.out.println(q.pollLast());
    System.out.println(q.pollLast());
    System.out.println(q.pollLast());
  }

}
