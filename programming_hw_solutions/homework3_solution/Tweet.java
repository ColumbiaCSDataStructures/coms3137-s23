public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Tweet))
            return false; 
        Tweet otherTweet = (Tweet) other; 
        return this.user.equals(otherTweet.user) &&
               this.content.equals(otherTweet.content) &&
               this.datetime.equals(otherTweet.datetime);
    }
    
    @Override 
    public int hashCode() {
        return 17 * user.hashCode() + 263 * content.hashCode() + 499 * datetime.hashCode(); 
    }
    
}
