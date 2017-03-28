package com.campolo.weather.domain.planet;

import com.campolo.weather.domain.planet.model.PlanetFactory;
import com.campolo.weather.domain.planet.model.StellarSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlanetConfig {

  @Bean
  public StellarSystem stellarSystem() {
    return new StellarSystem(
        PlanetFactory.createBetasoide(),
        PlanetFactory.createFerengi(),
        PlanetFactory.createVulcano());
  }

}
