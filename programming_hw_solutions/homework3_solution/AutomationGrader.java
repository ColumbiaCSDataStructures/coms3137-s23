import java.util.*;
import java.io.*;


public class AutomationGrader
{
	public static double totalScore = 100.0;
	public static HashSet<String> errorLog = new HashSet<>();

	public static void printFinalResults()
	{
    System.out.println(" ");
		System.out.println("GRADER RESULT");
		printErrorLog();
		System.out.println(totalScore);
	}

	private static void printErrorLog()
	{ 
		for (String element : errorLog)
			System.out.println(element);
	}


  private static class TweetComparator implements Comparator<Tweet> {
    @Override
    public int compare(Tweet a, Tweet b) {
      int compareName = a.user.compareTo(b.user);
      int compareDate = a.datetime.toString().compareTo(b.datetime.toString());
      int compareContnet = a.content.compareTo(b.content);
      return 100 * compareName + 10 * compareDate + compareContnet;
    }
  }

  // Compare returned results
  private static boolean compareResults(List<Tweet> student, List<Tweet> target) {
    if (student.size() != target.size())
      return false;
    Collections.sort(student, new TweetComparator());
    for(int i = 0; i < student.size(); i++) {
      Tweet stud = student.get(i);
      Tweet targ = target.get(i);
      if (!stud.user.equals(targ.user) ||
          !stud.content.equals(targ.content) ||
          !stud.datetime.toString().equals(targ.datetime.toString())) {
        return false;
      }
    }
    return true;
  }

  private static void printDiff(List<Tweet> student, List<Tweet> target) {
    for(Tweet t: student) {
      System.out.println(t);
    }
    System.out.println("====^ student ===== > target ===");
    for(Tweet t: target) {
      System.out.println(t);
    }
  }


  // PROBLEM 1

  public static void test_bst() {
 
    // attach 1   
    try {
      if (! ScalaTest.test_attach1()) {
        totalScore -= 3;
        errorLog.add("-3 Problem 1, part 1, Failed test attach(t1,t2), where t1 = tree(3, tree(1)) and t2 = tree(6, tree(5), tree(8, tree(7)))");
      }
    } catch (Exception e) {
        errorLog.add("-3 Problem 1, part 1, Failed test 1 for attach. Exception: " + e.toString());
    }

    // attach 2   
    try {
      if (! ScalaTest.test_attach2()) {
        totalScore -= 3;
        errorLog.add("-3 Problem 1, part 1, Failed test attach(t1,t2), where t1 = tree(1) and t2 = tree(3)");
      }
    } catch (Exception e) {
        errorLog.add("-3 Problem 1, part 1, Failed test 2 for attach. Exception: " + e.toString());
    }

    // contains 1   
    try {
      if (! ScalaTest.test_contains1()) {
        totalScore -= 2;
        errorLog.add("-2 Problem 1, part 2, Failed test contains(t1,8), where t1 = tree(6, tree(5), tree(8, tree(7)))");
      }
    } catch (Exception e) {
        errorLog.add("-2 Problem 1, part 2, Failed test 1 for contains. Exception: " + e.toString());
    }

    // contains 2   
    try {
      if (! ScalaTest.test_contains2()) {
        totalScore -= 2;
        errorLog.add("-2 Problem 1, part 2, Failed test contains(t1,4), where t1 = tree(6, tree(5), tree(8, tree(7))). Returned true.");
      }
    } catch (Exception e) {
        errorLog.add("-2 Problem 1, part 2, Failed test 2 for contains. Exception: " + e.toString());
    }

    // delete  
    try {
      if (! ScalaTest.test_delete()) {
        totalScore -= 4;
        errorLog.add("-4 Problem 1, part 3, Failed test delete(t1,8), where tree(7, tree(5), tree(8, tree(6), tree(9)))");
      }
    } catch (Exception e) {
        errorLog.add("-4 Problem 1, part 3, Failed test for delete. Exception: " + e.toString());
    }

    // insert 1 
    try {
      if (! ScalaTest.test_insert1()) {
        totalScore -= 2;
        errorLog.add("-2 Problem 1, part 4, Failed test insert(t1,6), where tree(7, tree(4), tree(8, tree(5), tree(9)))");
      }
    } catch (Exception e) {
        errorLog.add("-2 Problem 1, part 4, Failed test 1 for insert. Exception: " + e.toString());
    }
    
    // insert 2 
    try {
      if (! ScalaTest.test_insert2()) {
        totalScore -= 2;
        errorLog.add("-2 Problem 1, part 4, Failed test insert(t1,8), where tree(7)");
      }
    } catch (Exception e) {
        errorLog.add("-2 Problem 1, part 4, Failed test 2 for insert. Exception: " + e.toString());
    }

    // union 1 
    try {
      if (! ScalaTest.test_union1()) {
        totalScore -= 4;
        errorLog.add("-4 Problem 1, part 5, Failed test union(t1,t2), where t1 = tree(3, tree(2, tree(1)), tree(5)) and t2 = tree(4, tree(1), tree(7, tree(5)))"); 
      }
    } catch (Exception e) {
        errorLog.add("-4 Problem 1, part 5, Failed test 1 for union. Exception: " + e.toString());
    }
    
    // union 2
    try {
      if (! ScalaTest.test_union2()) {
        totalScore -= 4;
        errorLog.add("-4 Problem 1, part 5, Failed test union(t1,t2), where t1 = tree(3) and t2 = tree(4)");
      }
    } catch (Exception e) {
        errorLog.add("-4 Problem 1, part 5, Failed test 2 for union. Exception: " + e.toString());
    }

    // intersection 1 
    try {
      if (! ScalaTest.test_intersection1()) {
        totalScore -= 4;
        errorLog.add("-4 Problem 1, part 6, Failed test intersection(t1,t2), where t1 = tree(3, tree(2, tree(1)), tree(5)) and t2 = tree(4, tree(1), tree(7, tree(5)))"); 
      }
    } catch (Exception e) {
        errorLog.add("-4 Problem 1, part 6, Failed test 1 for intersection. Exception: " + e.toString());
    }
    
    // intersection 2
    try {
      if (! ScalaTest.test_intersection2()) {
        totalScore -= 4;
        errorLog.add("-4 Problem 1, part 6, Failed test intersection(t1,t2), where t1 = tree(3) and t2 = tree(3)");
      }
    } catch (Exception e) {
        errorLog.add("-4 Problem 1, part 6, Failed test 2 for intersection. Exception: " + e.toString());
    }

  }
  


  // P2.1
  public static void test_tweetDB_user(TweetDB tdb) {

    // case 1: 1 user 1 tweet
    try {
      String user = "AlanchichenAlan";
      String tweet = "ovonikkie You are probably happy abouot Coachella2015";
      DateTime dt = new DateTime("1/6/2015 23:51");

      List<Tweet> student = tdb.getTweetsByUser(user);
      
      List<Tweet> target = new ArrayList<>();
      target.add(new Tweet(user, dt, tweet));
      
      if(!compareResults(student, target)) {
        totalScore -= 6;
        errorLog.add(" -6 Problem 2, part 1 Failed test #1.1: incorrect query result.");
      }
    } catch (Exception e) {
      totalScore -= 6;
      errorLog.add(" -6 Problem 2, part 1 Failed test #1.1: " + e.toString());
    }

    // case 2: 1 user multiple tweets and duplicate entries
    try {

      String user = "__tshepo__";
      String tweet_1 = "RT Grav I WISH I WAS AT Coachella2015 smily face";
      String tweet_2 = "RT Grav I WISH I WAS AT Coachella2015";
      DateTime dt_1 = new DateTime("1/7/2015 3:02");
      DateTime dt_2 = new DateTime("1/7/2015 4:03");
      
      List<Tweet> student = tdb.getTweetsByUser(user);
      
      List<Tweet> target = new ArrayList<>();
      
      target.add(new Tweet(user, dt_1, tweet_2));
      target.add(new Tweet(user, dt_1, tweet_1));
      target.add(new Tweet(user, dt_2, tweet_1));
      
      if(!compareResults(student, target)) {
        target.add(new Tweet(user, dt_1, tweet_1));
        Collections.sort(target, new TweetComparator());

        if (compareResults(student, target)) {
          totalScore -= 3;
          errorLog.add(" -3 Problem 2, part 4 Failed test #4.1: result contains duplicate.");
        } else {
          totalScore -= 6;
          errorLog.add(" -6 Problem 2, part 1 Failed test #1.2: incorrect query result.");
        } 
      }

    } catch (Exception e) {
      totalScore -= 6;
      errorLog.add(" -6 Problem 2, part 1 Failed test #1.2: " + e.toString());
    }
    
    // case 3: user does not exist
    try {

      String user = "nullptr";
      List<Tweet> student = tdb.getTweetsByUser(user);

      if (student != null && student.size() != 0) {
        totalScore -= 6;
        errorLog.add(" -6 Problem 2, part 1 Failed test #1.3: return non-empty list for user not in db.");
      }

    } catch (Exception e) {
      totalScore -= 6;
      errorLog.add(" -6 Problem 2, part 1 Failed test #1.3: " + e.toString());
    }

  }

  // P2.2
  public static void test_tweetDB_keyword(TweetDB tdb) {
    
    // case 1: 1 tweet
    try {
      
      String user = "AlanchichenAlan";
      String tweet = "ovonikkie You are probably happy abouot Coachella2015";
      DateTime dt = new DateTime("1/6/2015 23:51");

      List<Tweet> student = tdb.getTweetsByKeyword("happy");
      
      List<Tweet> target = new ArrayList<>();
      target.add(new Tweet(user, dt, tweet));

      if(!compareResults(student, target)) {
        totalScore -= 5;
        errorLog.add(" -5 Problem 2, part 2 Failed test #2.1: incorrect query result.");
      }

    } catch (Exception e) {
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, part 2 Failed test #2.1: " + e.toString());
    }

    // case 2: 1 keyword multiple tweets and duplicate entries
    try {

      String user = "__tshepo__";
      String tweet_1 = "RT Grav I WISH I WAS AT Coachella2015 smily face";
      DateTime dt_1 = new DateTime("1/7/2015 3:02");
      DateTime dt_2 = new DateTime("1/7/2015 4:03");
      
      List<Tweet> student = tdb.getTweetsByKeyword("smily");
      
      List<Tweet> target = new ArrayList<>();
      
      target.add(new Tweet(user, dt_1, tweet_1));
      target.add(new Tweet(user, dt_2, tweet_1));
      
      if(!compareResults(student, target)) {
        target.add(new Tweet(user, dt_1, tweet_1));
        Collections.sort(target, new TweetComparator());

        if (compareResults(student, target)) {
          totalScore -= 3;
          errorLog.add(" -3 Problem 2, part 4 Failed test #4.2: result contains duplicate.");
        } else {
          totalScore -= 5;
          errorLog.add(" -5 Problem 2, part 2 Failed test #2.2: incorrect query result.");
        } 
      }

    } catch (Exception e) {
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, part 2 Failed test #2.2: " + e.toString());
    }
    
    // case 3: keyword does not exist
    try {

      String key = "nullptr";
      List<Tweet> student = tdb.getTweetsByKeyword(key);

      if (student != null && student.size() != 0) {
        totalScore -= 5;
        errorLog.add(" -5 Problem 2, part 2 Failed test #2.3: return non-empty list for keyword not in db.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, part 2 Failed test #2.3: " + e.toString());
    }
  }


  // P2.3
  public static void test_tweetDB_datetime(TweetDB tdb) {
    
    // case 1: 1 tweet in range
    try {
      
      String user = "AlanchichenAlan";
      String tweet = "ovonikkie You are probably happy abouot Coachella2015";
      DateTime dt = new DateTime("1/6/2015 23:51");

      List<Tweet> student = tdb.getTweetsInRange(new DateTime("1/6/2015 23:51"), new DateTime("1/7/2015 0:00"));
      
      List<Tweet> target = new ArrayList<>();
      target.add(new Tweet(user, dt, tweet));

      if(!compareResults(student, target)) {
        totalScore -= 5;
        errorLog.add(" -5 Problem 2, part 3 Failed test #3.1: incorrect query result.");
      }

    } catch (Exception e) {
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, part 3 Failed test #3.1: " + e.toString());
    }

    // // case 2: 1 keyword multiple tweets and duplicate entries
    try {

      String user = "__tshepo__";
      String tweet_1 = "RT Grav I WISH I WAS AT Coachella2015 smily face";
      String tweet_2 = "RT Grav I WISH I WAS AT Coachella2015";
      DateTime dt_1 = new DateTime("1/7/2015 3:02");
      DateTime dt_2 = new DateTime("1/7/2015 4:03");
      String userB = "AlanchichenAlan";
      String tweetB = "ovonikkie You are probably happy abouot Coachella2015";
      DateTime dtB = new DateTime("1/6/2015 23:51");
      
      List<Tweet> student = tdb.getTweetsInRange(new DateTime("1/6/2015 23:00"), new DateTime("1/7/2015 17:00"));
      
      List<Tweet> target = new ArrayList<>();
      
      target.add(new Tweet(userB, dtB, tweetB));
      target.add(new Tweet(user, dt_1, tweet_2));
      target.add(new Tweet(user, dt_1, tweet_1));
      target.add(new Tweet(user, dt_2, tweet_1));
      

      if(!compareResults(student, target)) {

        target.add(new Tweet(user, dt_1, tweet_1));
        Collections.sort(target, new TweetComparator());
        if (compareResults(student, target)) {
          totalScore -= 3;
          errorLog.add(" -3 Problem 2, Part 4 Failed test #4.3: result contains duplicate.");
        } else {
          totalScore -= 5;
          errorLog.add(" -5 Problem 2, Part 3 Failed test #3.2: incorrect query result.");
        } 
      }

    } catch (Exception e) {
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, Part 3 Failed test #3.2: " + e.toString());
    }
    
    // case 3: keyword does not exist
    try {

      List<Tweet> student = tdb.getTweetsInRange(new DateTime("1/4/2015 0:00"), new DateTime("1/5/2015 0:00"));

      if (student.size() != 0) {
        totalScore -= 5;
        errorLog.add(" -5 Problem 2, Part 3 Failed test #3.3: return non-empty list for keyword not in db.");
      }

    } catch (Exception e) {
      totalScore -= 5;
      errorLog.add(" -5 Problem 2, Part 3 Failed test #3.3: " + e.toString());
    }

    // case 4: individual case for Datetime compareTo
    try {
      DateTime d1 = new DateTime("1/4/2015 11:59");
      DateTime d2 = new DateTime("1/5/2015 0:00");
      DateTime d3 = new DateTime("2/4/2015 2:00");
      DateTime d4 = new DateTime("2/4/2016 0:00");
      DateTime d5 = new DateTime("2/4/2016 1:00");
      DateTime d6 = new DateTime("2/4/2016 0:59");

      boolean goodCompare = d1.compareTo(d2) < 0 && d2.compareTo(d1) > 0 &&
                            d2.compareTo(d3) < 0 && d3.compareTo(d2) > 0 &&
                            d3.compareTo(d4) < 0 && d4.compareTo(d3) > 0 &&
                            d4.compareTo(d5) < 0 && d5.compareTo(d4) > 0 &&
                            d5.compareTo(d6) > 0 && d6.compareTo(d5) < 0 && d6.compareTo(d6) == 0;

      if (!goodCompare) {
        totalScore -= 3;
        errorLog.add(" -3 Problem 2, Part 3 Failed test #3.4: incorrect Datetime compareTo implementation.");
      }


    } catch (Exception e) {
      totalScore -= 3;
      errorLog.add(" -3 Problem 2, Part 3 Failed test #3.4: " + e.toString());

    }
  }

  // Check for hashCode() and equals() implementation of Tweet and DateTime
  public static void test_hashCodeEquals() {

    // case 1: DateTime
    try {

      DateTime d1 = new DateTime("1/4/2015 11:59");
      DateTime d2 = new DateTime("1/5/2015 0:00");
      DateTime d3 = new DateTime("1/4/2015 11:59");

      if (d1.hashCode() != d3.hashCode() ||
          d1.hashCode() == d2.hashCode() || 
          d1.equals(d2) || !d1.equals(d3)) {
        totalScore -= 3;
      errorLog.add(" -3 Problem 2, Part 4 Failed test case 4.4: incorrect implementation of DateTime equals() and hashCode().");
      }

    } catch (Exception e) {
      totalScore -= 3;
      errorLog.add(" -3 Problem 2, Part 4  Failed test #4.4: " + e.toString());
    }

    // case 2: Tweet
    try {

      String u1 = "__tshepo__";
      String u2 = "AlanchichenAlan";
      
      String c1 = "RT Grav I WISH I WAS AT Coachella2015 smily face";
      String c2 = "ovonikkie You are probably happy abouot Coachella2015";
      
      DateTime dt1 = new DateTime("1/7/2015 3:02");
      DateTime dt2 = new DateTime("1/6/2015 23:51");

      Tweet t1 = new Tweet(u1, dt1, c1);
      Tweet t2 = new Tweet(u2, dt1, c1);
      Tweet t3 = new Tweet(u1, dt2, c1);
      Tweet t4 = new Tweet(u1, dt1, c2);
      Tweet t5 = new Tweet(u2, dt2, c2);
      Tweet t6 = new Tweet(u1, dt1, c1);

      if (t1.hashCode() == t2.hashCode() ||
          t1.hashCode() == t3.hashCode() || 
          t1.hashCode() == t4.hashCode() ||
          t1.hashCode() == t5.hashCode() ||
          t1.hashCode() != t6.hashCode()) { 
        totalScore -= 3;
        errorLog.add(" -3 Problem 2, Part 4 Failed test case #4.4: incorrect implementation of Tweet hashCode().");
      }

      if (t1.equals(t2) || t1.equals(t3) || t1.equals(t4) || t1.equals(t5) || !t1.equals(t6)) {
        totalScore -= 3;
        errorLog.add(" -3 Problem 2, Part 4 Failed test case #4.4: incorrect implementation of Tweet equals().");
      }
    } catch (Exception e) {
      totalScore -= 3;
      errorLog.add(" -3 Problem 2, Part 4 Failed test #4.4: " + e.toString());
    }

  }


  public static void test_tweetDB() {

    try {
    	TweetDB tdb = new TweetDB("test_tweets.csv");

      // P2.1
      test_tweetDB_user(tdb);

      // P2.2
      test_tweetDB_keyword(tdb);

      // P2.3
      test_tweetDB_datetime(tdb);
      
      // P2.4
      test_hashCodeEquals();

            
    } catch (FileNotFoundException e) {
      System.err.println(".csv File not found.");
    } catch (IOException e) {
      errorLog.add("IOException from 2.1: " + e.toString());
      System.err.println("Error reading from .csv file.");
    }
    catch (NullPointerException e) {
      totalScore -= 4;
      errorLog.add("-4: NullPointer from TweetDB constructor: " + e.toString());
      System.err.println("NullPointer from TweetDB constructor.");
    }
    catch (StackOverflowError e) {
      totalScore -= 4;
      errorLog.add("-4: StackOverflowError: " + e.toString());
    }
    catch (Exception e) {
      totalScore -= 10;
      errorLog.add("-10: Uncaught exception from P2: " + e.toString());
    }
  }




	public static void main(String[] args) {
    // p1 
    test_bst();
    // p2 
    test_tweetDB();
		printFinalResults();
	}
}
