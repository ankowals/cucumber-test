package com.github.ankowals.steps;

import com.github.ankowals.domain.ApiClient;
import com.github.ankowals.domain.CallPastryService;
import com.github.ankowals.domain.questions.ThePastry;
import com.github.ankowals.environment.UsesPastryService;
import com.github.ankowals.framework.screenplay.abilities.cleanup.DoTheCleanUp;
import com.github.ankowals.framework.screenplay.abilities.cleanup.OnTeardownActions;
import com.github.ankowals.framework.screenplay.abilities.memory.Memory;
import com.github.ankowals.framework.screenplay.abilities.memory.RememberThat;
import com.github.ankowals.framework.screenplay.abilities.memory.RememberThings;
import com.github.ankowals.framework.screenplay.abilities.memory.TheRemembered;
import com.github.ankowals.framework.screenplay.actor.Actor;
import com.github.ankowals.framework.screenplay.helpers.use.UseAbility;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

// per feature hooks example
public record GetPastryDetailsFeatureHooks(Actor actor) implements UsesPastryService {

  // additional actor used to share the state and clean up at the end
  private static Actor parent;

  // will be called only before features tagged with value
  // order has to be higher than in Hooks.class
  @Before(order = 1, value = "@get-pastry-details")
  public void before() throws Exception {
    parent = new Actor();
    parent.can(
        RememberThings.with(new Memory()),
        DoTheCleanUp.with(new OnTeardownActions()),
        CallPastryService.with(new ApiClient(this.getPastryUrl())));

    // do stuff before feature
    this.actor.attemptsTo(RememberThat.valueOf("pastry").is(ThePastry.details("Millefeuille")));

    // share the state with the actor injected into steps
    Memory memories = parent.asksFor(TheRemembered.memories());
    this.actor.can(RememberThings.with(memories));
  }

  // clean will be called after all scenarios
  @AfterAll
  public static void after() {
    UseAbility.of(parent).to(DoTheCleanUp.class).onTeardownActions().runAll();
  }
}
