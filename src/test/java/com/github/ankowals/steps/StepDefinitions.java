package com.github.ankowals.steps;

import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.domain.questions.ThePastries;
import com.github.ankowals.domain.questions.ThePastry;
import com.github.ankowals.framework.screenplay.abilities.cleanup.DoTheCleanUp;
import com.github.ankowals.framework.screenplay.abilities.memory.Forget;
import com.github.ankowals.framework.screenplay.abilities.memory.Or;
import com.github.ankowals.framework.screenplay.abilities.memory.TheRemembered;
import com.github.ankowals.framework.screenplay.actor.Actor;
import com.github.ankowals.framework.screenplay.helpers.See;
import com.github.ankowals.framework.screenplay.helpers.use.UseAbility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public record StepDefinitions(Actor actor) {

  @Given("I can access pastry service")
  public void iCanAccessPastryService() throws Exception {
    this.actor.should(
        See.that(ThePastries.all(), pastries -> Assertions.assertThat(pastries).isNotEmpty()));
  }

  @When("I ask about availability of pastry {string}")
  public void iAskAboutAvailabilityOfPastry(String name) throws Exception {
    // optional step, take it from the memory or ask the SUT
    this.actor.asksFor(
        TheRemembered.valueOf("pastry", Pastry.class, Or.askFor(ThePastry.details(name))));

    // not really needed but whatever we add to onTeardown actions will be called in after hook
    // can be used to call delete, close driver/connection or stop a container etc.
    // it is best to use it in questions or interactions implementation
    UseAbility.of(this.actor)
        .to(DoTheCleanUp.class)
        .onTeardownActions()
        .add(() -> this.actor.attemptsTo(Forget.valueOf("pastry", Pastry.class)));
  }

  @Then("I should be told it is available")
  public void iShouldBeToldItIsAvailable() throws Exception {
    this.actor.should(
        See.that(
            TheRemembered.valueOf("pastry", Pastry.class),
            value ->
                Assertions.assertThat(value).returns(Pastry.Status.AVAILABLE, Pastry::status)));
  }
}
