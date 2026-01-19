package com.github.ankowals.steps;

import com.github.ankowals.domain.ApiClient;
import com.github.ankowals.domain.CallPastryService;
import com.github.ankowals.environment.UsesPastryService;
import com.github.ankowals.framework.screenplay.abilities.AssertSoftly;
import com.github.ankowals.framework.screenplay.abilities.AwaitPatiently;
import com.github.ankowals.framework.screenplay.abilities.cleanup.DoTheCleanUp;
import com.github.ankowals.framework.screenplay.abilities.cleanup.OnTeardownActions;
import com.github.ankowals.framework.screenplay.abilities.memory.Memory;
import com.github.ankowals.framework.screenplay.abilities.memory.RememberThings;
import com.github.ankowals.framework.screenplay.actor.Actor;
import com.github.ankowals.framework.screenplay.helpers.use.UseAbility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.time.Duration;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;

public record Hooks(Actor actor) implements UsesPastryService {

  @Before(order = 0)
  public void before() {
    this.actor.can(
        RememberThings.with(new Memory()),
        DoTheCleanUp.with(new OnTeardownActions()),
        AssertSoftly.with(new SoftAssertions()),
        AwaitPatiently.with(Awaitility.await().ignoreExceptions().atMost(Duration.ofSeconds(4))),
        CallPastryService.with(new ApiClient(this.getPastryUrl())));
  }

  @After
  public void after() {
    UseAbility.of(this.actor).to(DoTheCleanUp.class).onTeardownActions().runAll();
  }
}
