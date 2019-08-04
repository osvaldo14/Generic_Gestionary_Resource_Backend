import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDataBase implements InterfaceDbResource, InterfaceDbReservation, InterfaceDbType {
    private List<Resource> db = new ArrayList<>();
    private Map<String,List<Resource>> resourceByTypeList;
    private List<Reservation> dbReservation = new ArrayList<>();
    private List<ResourceType> dbType = new ArrayList<>();

    public InMemoryDataBase(){
        //Create resource
        HashMap<String, String> cavatCarac = new HashMap<>();
        cavatCarac.put("nom", "Cavat");
        cavatCarac.put("âge","30");
        cavatCarac.put("Doctorat","Oui");
        HashMap<String, String> gluckCarac = new HashMap<>();
        gluckCarac.put("nom", "Gluck");
        gluckCarac.put("âge","35");
        gluckCarac.put("Doctorat","Oui");
        HashMap<String, String> A406Carac = new HashMap<>();
        A406Carac.put("Nombre de place", "50");
        A406Carac.put("étage","4");
        A406Carac.put("Bimeur","Oui");
        Resource Cavat = new Resource("Joel Cavat", "Professeur", cavatCarac);
        Resource A406 = new Resource("A406","Salle", A406Carac);
        Resource Gluck = new Resource("Florent Gluck", "Professeur", gluckCarac);
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
        h2.put("Nombre de place", "Int");
        h2.put("étage","int");
        h2.put("Bimeur","bool");
        ResourceType t2 = new ResourceType("Salle",h2);
        this.dbType.add(t);
        this.dbType.add(t2);
    }

    public List<Resource> getDb(){
        List<Resource> r = this.db;
        return new ArrayList<>(r);
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

    public void deleteResource(String resourceName){
        this.db.removeIf(r -> r.getName().equals(resourceName));
    }

    public void modifyResource(Resource r ){

    }

    /* METHODS FOR DATABASE RESERVATION */

    public Reservation getReservationInfos(Reservation r){
        Reservation res = this.dbReservation.get(1);
        return res;
    }
    public List<Reservation> getReservationList(){
        List<Reservation> l = this.dbReservation;
        return new ArrayList<>(l);
    }
    public void addReservation(Reservation r){
        this.dbReservation.add(r);
    }

    public void deleteReservation(Reservation r){
        this.dbReservation.remove(r);
    }

    public void modifyReservation(Integer id, Date newStart, Date newEnd){
        this.dbReservation.get(id).updateReservation(newStart, newEnd);
    }
    public void modifyReservation(Integer id, Date newStart, Date newEnd, String name, List<String> resources){
        this.dbReservation.get(id).updateReservation(newStart, newEnd, name, resources);
    }

    public void findAndDeleteReservation(Integer id){
        this.dbReservation.removeIf(r -> r.getID().equals(id));
    }

    public List<String> reservationNames(){
        List<String> l = new ArrayList<>();
        this.dbReservation.forEach((r) -> l.add(r.getName()));
        return l;
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