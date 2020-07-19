package demo;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.QueryParam;

public abstract class AbstractController {

    @GET
    @Path("/print")
    void fromAbstractClass(@QueryParam int param) {
        System.out.println("param = " + param);
    }
}