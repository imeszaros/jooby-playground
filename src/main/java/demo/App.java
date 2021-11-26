package demo;

import ch.qos.logback.core.status.Status;
import io.jooby.Jooby;
import org.gnieh.logback.config.ConfigLoader;

import java.util.ServiceLoader;

public class App extends Jooby {

  {
    getLog().info("I'm alive.");
  }

  public App() {
    get("/", ctx -> "hello");
  }

  public static void main(String[] args) {
    // acquire the config loader service
    final var loader = ServiceLoader.load(ConfigLoader.class)
        .stream()
        .map(ServiceLoader.Provider::get)
        .filter(ConfigLoaderImpl.class::isInstance)
        .map(ConfigLoaderImpl.class::cast)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Unable to load service " + ConfigLoaderImpl.class.getName()));

    // prevent logback status messages with level < WARN to appear, this is normally
    // done in logback.xml via debug=false
    StatusListenerImpl.configure(System.out::print, Status.WARN, Status.ERROR);
    System.setProperty("logback.statusListenerClass", "demo.StatusListenerImpl");

    // by specifying the environment externally we can make sure that the config
    // file is only read/parsed once
    runApp(args, () -> new App().setEnvironment(loader.getEnvironment()));
  }
}
