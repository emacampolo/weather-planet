package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.List;

public class WeatherCalculator {

  private List<WeatherResolver> resolvers;

  public WeatherCalculator(final List<WeatherResolver> resolvers) {
    this.resolvers = resolvers;
  }

  public WeatherInfo calculate(final StellarSystem stellarSystem) {
    return resolvers.stream()
        .filter(weatherResolver -> weatherResolver.canResolve(stellarSystem))
        .findFirst()
        .orElse(new UnknownWeatherResolver())
        .create(stellarSystem);
  }
}
