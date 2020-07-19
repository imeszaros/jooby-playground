package demo;

import io.jooby.annotations.GET;
import io.jooby.annotations.POST;
import io.jooby.annotations.Path;
import io.jooby.annotations.QueryParam;

@Path("/hello")
public class HelloController extends AbstractController {

    @GET
    public String hello(@QueryParam String name) {
        return "Hello, " + name + "!";
    }

    @POST
    @Path("/doit")
    public void doSomething() {}
}