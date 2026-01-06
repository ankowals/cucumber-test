package com.github.ankowals.framework.client.request;

import io.restassured.specification.ResponseSpecification;

public interface ResponseSpecificationAcceptingExecutableRequest<T> extends ExecutableRequest {
  T execute(ResponseSpecification responseSpecification);
}
