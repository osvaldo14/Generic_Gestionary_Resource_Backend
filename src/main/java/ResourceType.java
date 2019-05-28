import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceType {
    private String name;
    private HashMap<String, String> typeCaracteristics;
    private List<String> resources;

    public ResourceType(){}

    public ResourceType(String name, HashMap<String, String> carac){
        this.name = name;
        this.typeCaracteristics = carac;
        this.resources = new ArrayList<>();
    }

    public String getName(){
        String name = this.name;
        return name;
    }

    public HashMap<String, String> getTypeCaracteristics(){
        HashMap<String, String> caracteristics = this.typeCaracteristics;
        return caracteristics;
    }

    public List<String> getResources(){
        List<String> resources = this.resources;
        return resources;
    }

    public void addResource(Resource r){
        this.resources.add(r.name);
    }
}
