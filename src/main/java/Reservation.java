import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {
    private Integer id;
    private String name;
    private List<String> resourceList;
    private Date start;
    private Date end;

    public Reservation(){}

    public Reservation(String name, List<String> resources, Date start, Date end){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        s.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.name = name;
        this.resourceList = resources;
        this.start = start;
        this.end = end;
    }

    public Integer getID(){
        Integer i = this.id;
        return i;
    }
    public String getName(){
        String n = this.name;
        return n;
    }
    public List<String>getResourceList(){
        List<String> l = this.resourceList;
        return l;
    }
    public Date getStart(){
        Date d = this.start;
        return d;
    }
    public Date getEnd(){
        Date d = this.end;
        return d;
    }

    public void setResource_list(List<String> l){
        this.resourceList = l;
    }
    public void setID(Integer id){this.id = id;}


}
