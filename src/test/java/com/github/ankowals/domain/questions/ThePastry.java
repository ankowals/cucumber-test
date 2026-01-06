package com.github.ankowals.domain.questions;

import com.github.ankowals.domain.CallPastryService;
import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.framework.client.Expect;
import com.github.ankowals.framework.screenplay.Question;
import com.github.ankowals.framework.screenplay.actor.use.UseAbility;
import org.apache.http.HttpStatus;

public class ThePastry {
  public static Question<Pastry> details(String name) {
    return actor ->
        UseAbility.of(actor)
            .to(CallPastryService.class)
            .apiClient()
            .getPastry(name)
            .execute(Expect.status(HttpStatus.SC_OK));
  }
}
