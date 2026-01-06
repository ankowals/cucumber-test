package com.github.ankowals.framework.client;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import java.util.Arrays;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class Expect {

  @SuppressWarnings("unchecked")
  public static ResponseSpecification status(Integer... httpStatusCodes) {
    Matcher<Integer>[] matchers =
        Arrays.stream(httpStatusCodes).map(Matchers::equalTo).toArray(Matcher[]::new);

    return Expect.status(Matchers.anyOf(matchers));
  }

  public static ResponseSpecification status(Matcher<Integer> httpStatusCodeMatcher) {
    return new ResponseSpecBuilder().expectStatusCode(httpStatusCodeMatcher).build();
  }
}
