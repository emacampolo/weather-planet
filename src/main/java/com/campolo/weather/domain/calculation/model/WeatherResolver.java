package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.StellarSystem;

public interface WeatherResolver {

  WeatherInfo create(final StellarSystem stellarSystem);

  boolean canResolve(final StellarSystem stellarSystem);

}
