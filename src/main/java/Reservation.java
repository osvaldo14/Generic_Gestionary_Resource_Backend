import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;

public class Reservation {
    private Integer ID;
    private String name;
    private List<String> resource_list;
    private Date start;
    private Date end;

    public Reservation(){}

    public Reservation(String name, List<String> resources, Date start, Date end){
        this.name = name;
        this.resource_list = resources;
        this.start = start;
        this.end = end;
    }

    public Integer getID(){
        Integer i = this.ID;
        return i;
    }
    public String getName(){
        String n = this.name;
        return n;
    }
    public List<String>getResource_list(){
        List<String> l = this.resource_list;
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
        this.resource_list = l;
    }


}
