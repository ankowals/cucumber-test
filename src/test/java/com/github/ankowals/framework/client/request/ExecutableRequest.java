package com.github.ankowals.framework.client.request;

import io.restassured.response.Response;

@FunctionalInterface
public interface ExecutableRequest {
  Response execute();
}
