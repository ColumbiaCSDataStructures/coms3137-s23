import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.HashSet;
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class TweetDB {
   
    
    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>();

        
        CsvReader reader = new CsvReader(new BufferedReader(new FileReader(pathToFile)));
        String[] next = reader.nextLine();
        Tweet tweet;
        while (next!= null) {
            tweet = getTweet(next);
            indexTweet(tweet);
            next = reader.nextLine();                      
        }
        
    }
  

    public void indexTweet(Tweet tweet) {        

        List<Tweet> tweetlist = tweetsPerUser.get(tweet.user);
        if (tweetlist==null) {
            tweetlist = new ArrayList<Tweet>();
            tweetsPerUser.put(tweet.user, tweetlist);
        }
        tweetlist.add(tweet);                
                
        String filteredContent = tweet.content.replaceAll("\\p{Punct}", "").toLowerCase();
        for (String token : filteredContent.split(" ")) {
            tweetlist = tweetsPerKeyword.get(token);
            if (tweetlist == null) {
                tweetlist = new ArrayList<Tweet>();
                tweetsPerKeyword.put(token, tweetlist);
            }
            tweetlist.add(tweet);
        }                         

        tweetlist = tweetsByTime.get(tweet.datetime);
        if (tweetlist==null) {
            tweetlist = new ArrayList<Tweet>();
            tweetsByTime.put(tweet.datetime, tweetlist);
        }
        tweetlist.add(tweet);        
    }         
        
    
    public List<Tweet> getTweetsByUser(String user) {
        HashSet<Tweet> result = new HashSet<>();
        List<Tweet> lookup_result = tweetsPerUser.get(user);
        if (lookup_result != null)
          return new ArrayList<Tweet>(new HashSet<Tweet>(lookup_result));
        return new ArrayList<Tweet>();
    }
    
    public List<Tweet> getTweetsByKeyword(String kw) {
        HashSet<Tweet> result = new HashSet<>();
        List<Tweet> lookup_result = tweetsPerKeyword.get(kw);
        if (lookup_result != null)
          return new ArrayList<Tweet>(new HashSet<Tweet>(lookup_result));
        return new ArrayList<Tweet>();
    }
    
     public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
         HashSet<Tweet> result = new HashSet<>(); 
         

         Map<DateTime, List<Tweet>> m = tweetsByTime.subMap(start, end);
         
         for (List<Tweet> tweets: tweetsByTime.subMap(start, end).values()) {
             result.addAll(tweets);
         }
         return new ArrayList<Tweet>(result); 

    }
       
    public static Tweet getTweet(String[] fields) {
        DateTime timestamp = new DateTime(fields[2]);
        return new Tweet(fields[0],timestamp, fields[1]);       
    }
    
    public static void main(String[] args) {
                
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");
            
            for(Tweet t : tdb.getTweetsInRange(new DateTime("1/6/15 00:00"), new DateTime("1/6/15 5:00"))){
            }            
            
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file coachella_tweets.csv");
        } catch (IOException e) {
            System.out.println("Error reading from file coachella_tweets.csv");
        }
        
        
        
        
    }
    
}
