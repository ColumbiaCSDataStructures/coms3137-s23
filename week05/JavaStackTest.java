import java.util.LinkedList;

public class JavaStackTest{


  public static void main(String[] args) {

    LinkedList<Integer> s = new LinkedList<>(); 
    s.push(1);
    s.push(2);
    s.push(3);
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
  }

}
