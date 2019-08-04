import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ReservationUpdates {
    private Integer reservationID;
    private Date start;
    private Date end;
    private String name;
    private List<String> resources;

    public ReservationUpdates(){}

    /*public ReservationUpdates(Integer reservationID, Date start, Date end){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        s.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.reservationID = reservationID;
        this.start = start;
        this.end = end;
    }*/

    public ReservationUpdates(Integer reservationID, Date start, Date end, String name, List<String> resources){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        s.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.reservationID = reservationID;
        this.start = start;
        this.end = end;
        this.name = name;
        this.resources = resources;
    }


    public Integer getReservationID() {
        return reservationID;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnd() {
        return end;
    }
    public String getName() {return name;}
    public List<String> getResources() {return resources;}


}
