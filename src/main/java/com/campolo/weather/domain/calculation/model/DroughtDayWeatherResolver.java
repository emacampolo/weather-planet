package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;
import org.apache.commons.math3.util.Precision;

public class DroughtDayWeatherResolver implements WeatherResolver {

  private static boolean areOpposites(final Planet planet, final Planet otherPlanet) {
    double thisPosInRadSin = Precision.round(Math.sin(planet.getAngleInRadians()), 12);
    double otherPlanetInRadSin = Precision.round(Math.sin(otherPlanet.getAngleInRadians()), 12);

    return thisPosInRadSin == otherPlanetInRadSin || (thisPosInRadSin + otherPlanetInRadSin) == 0;
  }

  @Override
  public WeatherInfo create(final StellarSystem stellarSystem) {
    return WeatherInfo.createDrought(stellarSystem);
  }

  @Override
  public boolean canResolve(final StellarSystem stellarSystem) {
    return areOpposites(stellarSystem.getPlanet1(), stellarSystem.getPlanet2())
        && areOpposites(stellarSystem.getPlanet2(), stellarSystem.getPlanet3());
  }
}
