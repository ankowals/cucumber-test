package com.github.ankowals.domain;

import com.github.ankowals.domain.requests.GetPastriesRequest;
import com.github.ankowals.domain.requests.GetPastryRequest;
import com.github.ankowals.framework.client.RequestSpecBuilderFactory;
import io.restassured.builder.RequestSpecBuilder;
import java.net.URI;
import java.util.function.Supplier;

public class ApiClient {

  private final Supplier<RequestSpecBuilder> requestSpecBuilderSupplier;

  public ApiClient(URI baseUri) {
    this.requestSpecBuilderSupplier = () -> RequestSpecBuilderFactory.create(baseUri);
  }

  public GetPastriesRequest getPastries() {
    return new GetPastriesRequest(this.requestSpecBuilderSupplier.get());
  }

  public GetPastryRequest getPastry(String name) {
    return new GetPastryRequest(this.requestSpecBuilderSupplier.get(), name);
  }
}
