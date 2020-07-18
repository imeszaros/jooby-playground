package demo;

import io.jooby.Jooby;

public class Main extends Jooby {

    {
        mvc(new HelloController());
        mvc(new AlohaController());
    }

    public static void main(String[] args) {
        Jooby.runApp(args, Main::new);
    }
}
