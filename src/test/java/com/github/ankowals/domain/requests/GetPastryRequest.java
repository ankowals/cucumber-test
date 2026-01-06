package com.github.ankowals.domain.requests;

import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.framework.client.request.ResponseSpecificationAcceptingExecutableRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class GetPastryRequest implements ResponseSpecificationAcceptingExecutableRequest<Pastry> {

  private final RequestSpecBuilder requestSpecBuilder;

  public GetPastryRequest(RequestSpecBuilder requestSpecBuilder, String name) {
    this.requestSpecBuilder = requestSpecBuilder;
    this.requestSpecBuilder.setAccept("application/json");
    this.requestSpecBuilder.addPathParam("name", name);
  }

  @Override
  public Response execute() {
    return RestAssured.given().spec(this.requestSpecBuilder.build()).when().get("/pastry/{name}");
  }

  @Override
  public Pastry execute(ResponseSpecification responseSpecification) {
    return this.execute().then().spec(responseSpecification).extract().body().as(Pastry.class);
  }
}
