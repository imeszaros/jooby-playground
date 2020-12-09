import demo.App;
import io.jooby.JoobyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@JoobyTest(value = App.class, port = 4321)
final class AppTest {
  private HttpClient client;

  @BeforeEach
  void initializeClient() {
    client = HttpClient.newHttpClient();
  }

  @Test
  void test_single() throws URISyntaxException, IOException, InterruptedException {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:4321/singletest")).GET().build();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    Assertions.assertEquals("Tested", response.body());
  }

  @Test
  void test_flowable() throws URISyntaxException, IOException, InterruptedException {
    var request =
        HttpRequest.newBuilder(new URI("http://localhost:4321/flowabletest")).GET().build();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    Assertions.assertEquals("Tested", response.body());
  }
}