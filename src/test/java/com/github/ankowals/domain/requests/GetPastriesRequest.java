package com.github.ankowals.domain.requests;

import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.framework.client.request.ResponseSpecificationAcceptingExecutableRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import java.util.Arrays;
import java.util.List;

public class GetPastriesRequest
    implements ResponseSpecificationAcceptingExecutableRequest<List<Pastry>> {

  private final RequestSpecBuilder requestSpecBuilder;

  public GetPastriesRequest(RequestSpecBuilder requestSpecBuilder) {
    this.requestSpecBuilder = requestSpecBuilder;
    this.requestSpecBuilder.setAccept("application/json");
  }

  @Override
  public Response execute() {
    return RestAssured.given().spec(this.requestSpecBuilder.build()).when().get("/pastry");
  }

  @Override
  public List<Pastry> execute(ResponseSpecification responseSpecification) {
    // WA it seems that getList ignores default mapper config when jackson 3 in use
    return Arrays.asList(
        this.execute().then().spec(responseSpecification).extract().body().as(Pastry[].class));
    // .jsonPath()
    // .getList(".", Pastry.class);
  }
}
