package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.StellarSystem;

public class UnknownWeatherResolver implements WeatherResolver {

  @Override
  public WeatherInfo create(final StellarSystem stellarSystem) {
    return new WeatherInfo(stellarSystem, WeatherType.UNDEFINED, 0);
  }

  @Override
  public boolean canResolve(final StellarSystem stellarSystem) {
    return false;
  }
}
