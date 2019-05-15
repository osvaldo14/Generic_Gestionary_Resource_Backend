import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Resource {
    //Random rand = new Random();
    public Integer ID;
    public String name;
    public String TypeName;
    //public HashMap<String, Serializable> caracteristics;
    //public Integer GroupID;
    //public String GroupName;

    public Resource(){}

    public Resource(String name, String TypeName){
        this.ID = 1; //rand.nextInt(10000-0) + 1;
        this.name = name;
        this.TypeName = TypeName;
        //this.GroupName = GroupName;
        //this.caracteristics = Create_Carac();
        //this.GroupID = Find_GroupID(GroupName);

    }

    public String getName(){
        String r = this.name;
        return r;
    }

    Integer Find_GroupID(String GroupName){
       //Request group id in database with group name
       //if not exist add groupname to database.
       //Return ID created for groupname
       return 0;
    }

    HashMap Create_Carac(){
        HashMap<String,String> carac = new HashMap<>();
        //Feed the hashmap EXEMPLE : carac.put("projecteur","oui");
        return carac;
    }

}
