import java.util.List;
import java.util.Map;

public interface InterfaceDbResource {
    List<Resource> getDb();
    Map<String,List<Resource>> getResourceByTypeList();
    void addResource(Resource r);
    void deleteResource(String resourceName);
    void modifyResource(Resource r);
}
