package com.github.ankowals.framework.screenplay.actor;

import com.github.ankowals.framework.screenplay.Consequence;
import com.github.ankowals.framework.screenplay.Question;
import org.assertj.core.api.ObjectAssert;

public interface PerformsChecks {
  void should(Consequence... consequences) throws Exception;

  <T> ObjectAssert<T> should(Question<T> question) throws Exception;
}
