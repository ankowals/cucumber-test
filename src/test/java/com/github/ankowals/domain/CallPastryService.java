package com.github.ankowals.domain;

import com.github.ankowals.framework.screenplay.Ability;
import com.github.ankowals.framework.screenplay.actor.Actor;
import com.github.ankowals.framework.screenplay.helpers.use.UseAbility;

public record CallPastryService(ApiClient apiClient) implements Ability {
  public static ApiClient as(Actor actor) {
    return UseAbility.of(actor).to(CallPastryService.class).apiClient();
  }

  public static CallPastryService with(ApiClient apiClient) {
    return new CallPastryService(apiClient);
  }
}
