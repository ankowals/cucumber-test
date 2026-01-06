package com.github.ankowals.environment;

import java.net.URI;

// in this way container will be started only once
public interface UsesPastryService {

  PastryService PASTRY_SERVICE = PastryService.start();

  default URI getPastryUrl() {
    return PASTRY_SERVICE.getBaseUrl();
  }
}
