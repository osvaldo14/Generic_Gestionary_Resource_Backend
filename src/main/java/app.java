import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.javalin.Javalin;
import java.io.File;
import java.lang.reflect.Array;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class app {

    public static void main(String[] args) {
        inMemoryDataBase db = new inMemoryDataBase();

        Javalin app = Javalin.create().enableCorsForAllOrigins().start(6999);

        app.get("/", ctx -> {
            ArrayList<String> resources_name = new ArrayList<>();
            db.get_DB().forEach((n) -> resources_name.add(n.getName()));
            ctx.json(resources_name);
        });

        app.get("/resourcebytype", ctx ->{
            Map<String, List<String>> ResourceByType = db.get_ResourceByTypeList().entrySet().stream().collect(Collectors.toMap(
                    tpl -> tpl.getKey(),
                    tpl -> tpl.getValue().stream().map( r -> r.name ).collect(Collectors.toList())
            ));
            ctx.json(ResourceByType);
        });

        app.post("/createresource", ctx ->{
            ObjectMapper mapper = new ObjectMapper();
            Resource r = mapper.readValue(ctx.body(), Resource.class);
            System.out.println(ctx.body());
            db.addResource(r);
        });

        app.post("/createreservation", ctx ->{
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Reservation r = mapper.readValue(ctx.body(), Reservation.class);

            //Set the list of resources in the reservation
            JSONObject obj = new JSONObject(ctx.body());
            JSONArray arr = obj.getJSONArray("resources");
            List<String> l = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++)
            {
                l.add(arr.getString(i));
            }
            r.setResource_list(l);
            mapper.writeValue(new File("res.json"),r);
            db.addReservation(r);
        });

        app.get("/getreservationlist", ctx->{
            ctx.json(db.get_reservationList());
        });
    }
}