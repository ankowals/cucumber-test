package com.github.ankowals.environment;

import java.net.URI;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

// docker run -i --rm -p 8282:8282 quay.io/microcks/quarkus-api-pastry:latest
// https://github.com/microcks/api-lifecycle/tree/master/api-pastry-demo/api-implementations/quarkus-api-pastry
public class PastryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PastryService.class);

  private final GenericContainer<?> container;

  public PastryService() {
    this.container = this.create(DockerImageName.parse("quay.io/microcks/quarkus-api-pastry:0.1"));
    this.container.start();
  }

  public static PastryService start() {
    return new PastryService();
  }

  public URI getBaseUrl() {
    return URI.create(
        "http://%s:%s".formatted(this.container.getHost(), this.container.getMappedPort(8282)));
  }

  private GenericContainer<?> create(DockerImageName dockerImageName) {
    return new GenericContainer<>(dockerImageName)
        .withExposedPorts(8282)
        .withLogConsumer(new Slf4jLogConsumer(LOGGER))
        .withStartupTimeout(Duration.ofSeconds(4))
        .waitingFor(Wait.forHttp("/pastry"));
  }
}
