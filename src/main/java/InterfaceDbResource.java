import java.util.List;
import java.util.Map;

public interface InterfaceDbResource {
    List<Resource> getDb();
    Map<String,List<Resource>> getResourceByTypeList();
    void addResource(Resource r);
    void deleteResource(Resource r);
    void modifyResource(Resource r);
}
