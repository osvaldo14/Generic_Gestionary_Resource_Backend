import java.util.List;
import java.util.Map;

public interface Interface_db_resource {
    List<Resource> get_DB();
    Map<String,List<Resource>> get_ResourceByTypeList();
    void addResource(Resource r);
    void deleteResource(Resource r);
    void modifyResource(Resource r);
}
