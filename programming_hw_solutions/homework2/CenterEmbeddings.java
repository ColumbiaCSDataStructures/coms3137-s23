import java.util.LinkedList;
import java.util.List; 
import java.util.NoSuchElementException;

public class CenterEmbeddings {

  private static final String[] transitive = {"knew", "chased", "liked", "loved", "saw"};
  private static final String[] intransitive = {"snored", "laughed", "ran"};
  

  private static class Relation {
    
    String predicate; 
    String subject; 
    String object;    
    
    public Relation(String predicate, String subject, String object) {
      this.predicate = predicate; 
      this.subject = subject; 
      this.object = object;
    }
    
    public String toString(){
      
      if (object != null)
        return "(Pred: " + predicate+ ", Subj: " + subject +", Obj: " + object +")";
      else
        return "(Pred: " + predicate + ", Subj: " + subject+")";
    }
    
  }
  
  
  public static List<Relation> parseSentence(String sentence) throws IllegalArgumentException {
    
    List<Relation> result = new LinkedList<>();
    String[] tokens = sentence.split("\\s");
    
    LinkedList<String> stack = new LinkedList<>();

    String mainVerb = null; 
    
    try {
      for (String t : tokens) {
        if (!t.equals("the") && !(t.equals("a")) & !(t.equals("that"))) {

          boolean processed = false; 
          for (String s :transitive) {
            if (s.equals(t)) {            
              processed = true;                  

              if (stack.size() >=2) {            
                String subject = stack.pop();
                String object = stack.pop();

                Relation newRelation = new Relation(s, subject, object);
                result.add(newRelation);
                stack.push(object);
              } else { // special case: main verb, needs object on right              
                mainVerb = t;               
              }
            }

          }

          for (String s : intransitive) {
            if (s.equals(t)) { 
              processed = true; 
              Relation newRelation = new Relation(s, stack.pop(), null);
              result.add(newRelation);
            }
          }         


          if (! processed) {
            stack.push(t);
          }

        }
      }

      if (mainVerb!=null) {
        String object = stack.pop();
        String subject = stack.pop();
        Relation newRelation = new Relation(mainVerb, subject, object);
        result.add(newRelation);
      }

    } catch (NoSuchElementException e){
      throw new IllegalArgumentException("Too many verbs in the input.");
    } 
      
    if (stack.size() > 0) 
      throw new IllegalArgumentException("Too many nouns in the input.");
    
    return result;
  }
    
  
  
  public static void main(String[] args) {
    
    String test1 = "the child laughed";    
    String test2 = "the child that the the woman loved laughed";    
    String test3 = "the child that the the woman that the man knew loved laughed";    
    String test4 = "the child saw the cat"; // the main verb can be transitive 
    String test5 = "the child that the man knew saw the cat"; // relative clause in the subject of a transitive sentence
    String test6 = "the child saw the cat that the man loved"; // (challenge: relative clause in the object of a transitive sentence)
    
    List<Relation> result = parseSentence(test6);
    for (Relation r :result) {
      System.out.println(r);
    } 
  }
  
  
  
}
