package com.github.ankowals.framework.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.path.json.mapper.factory.Jackson3ObjectMapperFactory;
import java.lang.reflect.Type;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

public class ObjectMapperFactory implements Jackson3ObjectMapperFactory {

  @Override
  public ObjectMapper create(Type type, String s) {
    return JsonMapper.builder()
        .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .findAndAddModules()
        .changeDefaultPropertyInclusion(
            incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
        .build();
  }
}
