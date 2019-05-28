import java.util.List;
import java.util.Map;

public interface InterfaceDbType {
    List<ResourceType> getAllTypes();
    void addType(ResourceType t);
    void deleteType(ResourceType t);
    void modifyType(ResourceType t);
}
