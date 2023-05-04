public class DateTime implements Comparable<DateTime> {
    
    public int year;
    public int month;
    public int day; 
    public int hours;
    public int minutes;    
    public int seconds;
    public boolean pm; 

    
    public DateTime(int year, int day, int month, int h, int m, int sec, boolean pm) {
        
        this.year = year; 
        this.month = month; 
        this.day = day;
        
        this.hours = h; 
        this.minutes = m;
        
        this.seconds = sec; 
        
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
        seconds = 0;
        
        
    }
    
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }
    
    public int compareTo(DateTime other) {
        if (this.year == other.year)
            if (this.month == other.month) {
                    if (this.day == other.day) {
                        int thistime = hours*60*60+minutes*60+seconds;
                        int othertime = other.hours *60*60 +other.minutes*60+other.seconds;
                        return thistime-othertime;
                    } else return this.day-other.day;
            } 
            else return this.month-other.month;
            
            
        else 
            return this.year-other.year;
            
    }
    
    @Override 
    public boolean equals(Object other) {
        if (! (other instanceof DateTime))
            return false;
        DateTime otherDt = (DateTime) other; 
        return otherDt.year == this.year && otherDt.month == this.month && 
               otherDt.hours == this.hours && otherDt.minutes == this.minutes &&
               otherDt.seconds == this.seconds; 
    }
    
    @Override
    public int hashCode() {
        return year * 13 +month * 251 +day * 479+hours * 563+minutes * 877 + seconds * 1093; 
    }
    
}
