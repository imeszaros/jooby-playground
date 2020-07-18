package demo;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.QueryParam;

@Path("/aloha")
public class AlohaController {

    @GET
    public String aloha(@QueryParam String name) {
        return "Aloha, " + name + "!";
    }
}