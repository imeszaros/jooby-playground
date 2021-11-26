package demo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.jooby.Environment;
import io.jooby.EnvironmentOptions;
import org.gnieh.logback.config.ConfigLoader;

import java.util.Map;

public class ConfigLoaderImpl implements ConfigLoader {

  private volatile Environment environment;

  public Environment getEnvironment() {
    if (environment == null) {
      synchronized (this) {
        if (environment == null) {
          environment = Environment.loadEnvironment(new EnvironmentOptions());
        }
      }
    }
    return environment;
  }

  @Override
  public Config load() throws Exception {
    return getEnvironment().getConfig()
        // specify the first segment of all logback configuration entries
        .withFallback(ConfigFactory.parseMap(Map.of("logback-root", "logback")));
  }
}
