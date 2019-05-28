import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDataBase implements InterfaceDbResource, InterfaceDbReservation, InterfaceDbType {
    private List<Resource> db = new ArrayList<>();
    private Map<String,List<Resource>> resourceByTypeList;
    private List<Reservation> dbReservation = new ArrayList<>();
    private List<ResourceType> dbType = new ArrayList<>();

    public InMemoryDataBase(){
        //Create resource
        Resource Cavat = new Resource("Joel Cavat", "Professeur");
        Resource A406 = new Resource("A406","Salle");
        Resource Gluck = new Resource("Florent Gluck", "Professeur");
        this.db.add(Cavat);
        this.db.add(A406);
        this.db.add(Gluck);

        //CreateResourceType
        HashMap<String,String> h = new HashMap<>();
        h.put("nom", "String");
        h.put("âge","int");
        h.put("Doctorat","bool");
        ResourceType t = new ResourceType("Professeur",h);
        HashMap<String,String> h2 = new HashMap<>();
        h2.put("Nombre bouteille", "Int");
        h2.put("âge","int");
        h2.put("Fruit principal","String");
        ResourceType t2 = new ResourceType("Vin",h2);
        this.dbType.add(t);
        this.dbType.add(t2);
    }

    public List<Resource> getDb(){
        List<Resource> r = this.db;
        return r;
    }

    public Map<String, List<Resource>> getResourceByTypeList(){
        this.resourceByTypeList = this.db.stream()
                .collect( Collectors.groupingBy( r -> r.typeName ) );
        Map<String ,List<Resource>> r = this.resourceByTypeList;
        return r;
    }

    public void addResource(Resource r){
        this.db.add(r);
    }

    public void deleteResource(Resource r){}

    public void modifyResource(Resource r ){}

    /* METHODS FOR DATABASE RESERVATION */

    public Reservation getReservationInfos(Reservation r){
        Reservation res = this.dbReservation.get(1);
        return res;
    }
    public List<Reservation> getReservationList(){
        List<Reservation> l = this.dbReservation;
        return l;
    }
    public void addReservation(Reservation r){
        this.dbReservation.add(r);
    }

    public void deleteReservation(Reservation r){
        this.dbReservation.remove(r);
    }

    public void modifyReservation(Reservation r){}

    public void findAndDeleteReservation(String name){
        this.dbReservation.removeIf(r -> r.getName().equals(name));
    }


    /* METHODS FOR DATABASE RESOURCETYPE */

    public List<ResourceType> getAllTypes(){
        List<ResourceType> l = this.dbType;
        return l;
    }
    public void addType(ResourceType t){
        this.dbType.add(t);
    }
    public void deleteType(ResourceType t){
        this.dbType.remove(t);
    }
    public void modifyType(ResourceType t){}
}