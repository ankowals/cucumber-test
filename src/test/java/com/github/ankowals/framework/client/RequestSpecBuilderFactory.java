package com.github.ankowals.framework.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.ObjectMapperType;
import java.net.URI;

public class RequestSpecBuilderFactory {
  public static RequestSpecBuilder create(URI baseUri) {
    return new RequestSpecBuilder()
        .setBaseUri(baseUri)
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .setConfig(
            RestAssuredConfig.config()
                .objectMapperConfig(
                    ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapperType(ObjectMapperType.JACKSON_3)
                        .jackson3ObjectMapperFactory(new ObjectMapperFactory())));
  }
}
