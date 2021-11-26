package demo;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.util.StatusPrinter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

public class StatusListenerImpl implements StatusListener {

  private static final Set<Integer> levels = new HashSet<>();

  private static Consumer<String> consumer = s -> {};

  public static void configure(Consumer<String> consumer, int... levels) {
    StatusListenerImpl.consumer = requireNonNull(consumer);
    StatusListenerImpl.levels.clear();
    IntStream.of(levels).forEach(StatusListenerImpl.levels::add);
  }

  @Override
  public void addStatusEvent(Status status) {
    if (levels.contains(status.getLevel())) {
      final var sb = new StringBuilder();
      StatusPrinter.buildStr(sb, "", status);
      consumer.accept(sb.toString());
    }
  }
}
