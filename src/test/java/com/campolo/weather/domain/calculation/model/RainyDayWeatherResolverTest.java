package com.campolo.weather.domain.calculation.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;
import org.junit.Before;
import org.junit.Test;

public class RainyDayWeatherResolverTest {

  private RainyDayWeatherResolver resolver;

  private StellarSystem stellarSystem;

  @Before
  public void setup() {
    resolver = new RainyDayWeatherResolver();
    Planet firstPlanet = new Planet("firstPlanet", 1, 100);
    Planet secondPlanet = new Planet("secondPlanet", 2, 300);
    Planet thirdPlanet = new Planet("thirdPlanet", -5, 500);
    stellarSystem = new StellarSystem(firstPlanet, secondPlanet, thirdPlanet);
  }

  @Test
  public void create() throws Exception {
    WeatherInfo weatherInfo = resolver.create(stellarSystem);
    assertThat(weatherInfo).isNotNull();
    assertThat(weatherInfo.isRainyDay()).isTrue();
  }

  @Test
  public void canResolve() throws Exception {
    stellarSystem.getPlanet1().simulateAfterDays(45);
    stellarSystem.getPlanet2().simulateAfterDays(70);
    stellarSystem.getPlanet3().simulateAfterDays(10);

    assertThat(resolver.canResolve(stellarSystem)).isTrue();
  }

}