package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;

public class RainyDayWeatherResolver implements WeatherResolver {

  private static boolean isSunInsideTriangle(final StellarSystem stellarSystem) {

    Planet planet1 = stellarSystem.getPlanet1();
    Planet planet2 = stellarSystem.getPlanet2();
    Planet planet3 = stellarSystem.getPlanet3();

    double denominator = ((planet2.getY() - planet3.getY())
        * (planet1.getX() - planet3.getX())
        + (planet3.getX() - planet2.getX())
        * (planet1.getY() - planet3.getY()));

    double a = ((planet2.getY() - planet3.getY())
        * (0.0 - planet3.getX())
        + (planet3.getX() - planet2.getX()) * (0.0
        - planet3.getY()))
        / denominator;

    double b = ((planet3.getY() - planet1.getY())
        * (0.0 - planet3.getX())
        + (planet1.getX() - planet3.getX()) * (0.0
        - planet3.getY()))
        / denominator;

    double c = 1 - a - b;

    return 0 <= a && a <= 1 && 0 <= b && b <= 1 && 0 <= c && c <= 1;
  }

  @Override
  public WeatherInfo create(final StellarSystem stellarSystem) {
    return WeatherInfo
        .createRain(stellarSystem, getPrecipitation(stellarSystem));
  }

  private double getPrecipitation(final StellarSystem stellarSystem) {

    Planet planet1 = stellarSystem.getPlanet1();
    Planet planet2 = stellarSystem.getPlanet2();
    Planet planet3 = stellarSystem.getPlanet3();

    return planet1.getPosition().distance(planet2.getPosition())
        + planet2.getPosition().distance(planet3.getPosition())
        + planet3.getPosition().distance(stellarSystem.getPlanet1().getPosition());
  }

  @Override
  public boolean canResolve(final StellarSystem stellarSystem) {
    return isSunInsideTriangle(stellarSystem);
  }
}
