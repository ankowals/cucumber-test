package com.github.ankowals.domain.questions;

import com.github.ankowals.domain.CallPastryService;
import com.github.ankowals.domain.model.Pastry;
import com.github.ankowals.framework.client.Expect;
import com.github.ankowals.framework.screenplay.Question;
import com.github.ankowals.framework.screenplay.helpers.use.UseAbility;
import java.util.List;
import org.apache.http.HttpStatus;

public class ThePastries {
  public static Question<List<Pastry>> all() {
    return actor ->
        UseAbility.of(actor)
            .to(CallPastryService.class)
            .apiClient()
            .getPastries()
            .execute(Expect.status(HttpStatus.SC_OK));
  }
}
