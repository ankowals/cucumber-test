package com.github.ankowals.domain.requests;

import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.framework.client.request.ExecutableRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class PatchPastryRequest implements ExecutableRequest {

  private final RequestSpecBuilder requestSpecBuilder;

  public PatchPastryRequest(RequestSpecBuilder requestSpecBuilder, String name, Pastry pastry) {
    this.requestSpecBuilder = requestSpecBuilder;
    this.requestSpecBuilder.setContentType("application/json");
    this.requestSpecBuilder.setAccept("application/json");
    this.requestSpecBuilder.setBody(pastry);
    this.requestSpecBuilder.addPathParam("name", name);
  }

  @Override
  public Response execute() {
    return RestAssured.given().spec(this.requestSpecBuilder.build()).when().patch("/pastry/{name}");
  }
}
