package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.function.Function;

public class SunnyDayWeatherResolver implements WeatherResolver {

  @Override
  public WeatherInfo create(final StellarSystem stellarSystem) {
    return WeatherInfo.createSunny(stellarSystem);
  }

  @Override
  public boolean canResolve(final StellarSystem stellarSystem) {
    Planet planet1 = stellarSystem.getPlanet1();
    Planet planet2 = stellarSystem.getPlanet2();
    Planet planet3 = stellarSystem.getPlanet3();

    if ((planet1.getX() == planet2.getX() && planet1.getX() == planet3.getX()
        && planet1.getX() != 0)
        || (planet1.getY() == planet2.getY() && planet1.getY() == planet3.getY()
        && planet3.getY() != 0)) {
      return true;
    }

    double deltaY = planet1.getY() - planet2.getY();
    double deltaX = planet1.getX() - planet2.getX();

    double slope = deltaY / deltaX;
    double independent = planet1.getY() - (slope * planet1.getX());

    Function<Double, Double> line2d = d -> (slope * d) + independent;
    return line2d.apply(planet3.getX()) == planet3.getY() && line2d.apply(0.0) != 0.0;
  }
}
