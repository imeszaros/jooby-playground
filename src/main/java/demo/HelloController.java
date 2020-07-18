package demo;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.QueryParam;

@Path("/hello")
public class HelloController {

    @GET
    public String hello(@QueryParam String name) {
        return "Hello, " + name + "!";
    }
}