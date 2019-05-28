import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Resource {
    //Random rand = new Random();
    public Integer id;
    public String name;
    public String typeName;
    public HashMap<String, String> caracteristics;
    //public Integer groupID;
    //public String groupName;

    public Resource(){}

    public Resource(String name, String TypeName){
        this.id = 1;
        this.name = name;
        this.typeName = TypeName;
    }

    public Resource(String name, String typeName, HashMap<String,String> caracteristics){
        this.name = name;
        this.typeName = typeName;
        this.caracteristics = caracteristics;
    }

    public Resource(String name, String TypeName, ArrayList<String> l){

    }

    HashMap<String,String> getCaracteristics(){
        HashMap<String,String> tmp = new HashMap<>();
        tmp = this.caracteristics;
        return tmp;
    }

    public String getName(){
        String r = this.name;
        return r;
    }

    Integer findGroupID(String GroupName){
       return 0;
    }



    public void setID(Integer id){this.id = id;}

}
