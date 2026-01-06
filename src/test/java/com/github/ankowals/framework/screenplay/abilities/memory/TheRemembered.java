package com.github.ankowals.framework.screenplay.abilities.memory;

import com.github.ankowals.framework.screenplay.Question;
import com.github.ankowals.framework.screenplay.actor.use.UseAbility;

public class TheRemembered {
  public static <T> Question<T> valueOf(String key, Class<T> type) {
    return actor -> {
      T value = UseAbility.of(actor).to(RememberThings.class).memory().recall(key, type);

      if (value == null) {
        throw new NoObjectToRecallException(key);
      }

      return value;
    };
  }
}
