import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.javalin.Handler;
import io.javalin.Javalin;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.javalin.security.Role;
import javalinjwt.JWTAccessManager;
import javalinjwt.JWTGenerator;
import javalinjwt.JWTProvider;
import javalinjwt.JavalinJWT;
import javalinjwt.examples.JWTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.javalin.security.SecurityUtil.roles;


public class app {
    private static Integer idResource = 0;
    private static Integer idReservation = 0;

    public static void main(String[] args) {
        InMemoryDataBase db = new InMemoryDataBase();

        Javalin app = Javalin.create().enableCorsForAllOrigins().start(6999);

        /*----------------------------------------AUTHENTIFICATION----------------------------------------*/




        /*----------------------------------------ROUTES----------------------------------------*/

        app.get("/", ctx -> {
            ArrayList<String> resources_name = new ArrayList<>();
            db.getDb().forEach((n) -> resources_name.add(n.getName()));
            ctx.json(resources_name);
        });

        app.get("/resourcebytype", ctx ->{
            Map<String, List<String>> ResourceByType = db.getResourceByTypeList().entrySet().stream().collect(Collectors.toMap(
                    tpl -> tpl.getKey(),
                    tpl -> tpl.getValue().stream().map( r -> r.name ).collect(Collectors.toList())
            ));
            ctx.json(ResourceByType);
        });

        app.post("/createresource", ctx ->{
            ObjectMapper mapper = new ObjectMapper();
            Resource r = mapper.readValue(ctx.body(), Resource.class);
            r.setID(idResource);
            idResource += 1;
            db.addResource(r);
        });

        app.post("/createreservation", ctx ->{
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Reservation r = mapper.readValue(ctx.body(), Reservation.class);
            r.setID(idReservation);
            idReservation += 1;
            db.addReservation(r);
        });

        app.get("/getreservationlist", ctx->{
            ctx.json(db.getReservationList());
        });

        app.post("types",ctx->{
            ObjectMapper mapper = new ObjectMapper();
            ResourceType t = mapper.readValue(ctx.body(), ResourceType.class);
            db.addType(t);
        });

        app.get("gtypes", ctx->{
            List<String> typesNames = new ArrayList<>();
            List<ResourceType> l = new ArrayList<>();
            l = db.getAllTypes();
            l.forEach((n) -> typesNames.add(n.getName()));
            ctx.json(l);
        });

        app.post("/deletereservation", ctx->{
            System.out.println(ctx.body());
            db.findAndDeleteReservation(ctx.body());
        });
    }
}