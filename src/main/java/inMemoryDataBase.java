import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class inMemoryDataBase implements Interface_db_resource, Interface_db_reservation {
    private List<Resource> DB = new ArrayList<>();
    private Map<String,List<Resource>> ResourceByTypeList;

    private List<Reservation> db_reservation = new ArrayList<>();

    public inMemoryDataBase(){
        //Create resource
        Resource Cavat = new Resource("Joel Cavat", "Professeur");
        Resource A406 = new Resource("A406","Salle");
        Resource Gluck = new Resource("Florent Gluck", "Professeur");
        this.DB.add(Cavat);
        this.DB.add(A406);
        this.DB.add(Gluck);
        //DB = List.of(Cavat, A406, Gluck);


    }

    public List<Resource> get_DB(){
        List<Resource> r = this.DB;
        return r;
    }

    public Map<String, List<Resource>> get_ResourceByTypeList(){
        this.ResourceByTypeList = this.DB.stream()
                .collect( Collectors.groupingBy( r -> r.TypeName ) );
        Map<String ,List<Resource>> r = this.ResourceByTypeList;
        return r;
    }

    public void addResource(Resource r){
        this.DB.add(r);
    }

    public void deleteResource(Resource r){}

    public void modifyResource(Resource r ){}

    /* METHOD FOR DATABASE RESERVATION */

    public Reservation get_reservationInfos(Reservation r){
        Reservation res = this.db_reservation.get(1);
        return res;
    }
    public List<Reservation> get_reservationList(){
        List<Reservation> l = this.db_reservation;
        return l;
    }
    public void addReservation(Reservation r){
        this.db_reservation.add(r);
    }

    public void deleteReservation(Reservation r){}

    public void modifyReservation(Reservation r){}
}












/*
*
*  private void RegroupResourceByType() {
        List<String> temp = new ArrayList<>();
        this.DB.forEach((r)->{
            System.out.println(r.TypeName);
            if (this.ResourceByTypeList.containsKey(r.TypeName)) {

                //this.ResourceByTypeList.get(r.TypeName).add(r.name);
                //System.out.println(this.ResourceByTypeList.get(r.TypeName));

            }else {

                this.ResourceByTypeList.put(r.TypeName, new ArrayList<>());
                //this.ResourceByTypeList.get(r.TypeName).add(r.name);
            }
        });
    }
    * */