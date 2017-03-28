package com.campolo.weather.domain.calculation.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;
import org.junit.Before;
import org.junit.Test;

public class DroughtDayWeatherResolverTest {

  private DroughtDayWeatherResolver resolver;

  @Before
  public void setup() {
    resolver = new DroughtDayWeatherResolver();
  }

  @Test
  public void create() {
    WeatherInfo weatherInfo = resolver.create(mock(StellarSystem.class));
    assertThat(weatherInfo).isNotNull();
    assertThat(weatherInfo.isDroughtDay()).isTrue();
  }

  @Test
  public void canResolve() {
    Planet firstPlanet = new Planet("firstPlanet", 1, 1);
    Planet secondPlanet = new Planet("secondPlanet", 2, 1);
    Planet thirdPlanet = new Planet("thirdPlanet", -5, 1);

    StellarSystem stellarSystem = new StellarSystem(firstPlanet, secondPlanet, thirdPlanet);

    assertThat(resolver.canResolve(stellarSystem)).isTrue();

    firstPlanet.simulateAfterDays(40);
    secondPlanet.simulateAfterDays(110);
    thirdPlanet.simulateAfterDays(28);

    assertThat(resolver.canResolve(stellarSystem)).isTrue();

    firstPlanet.simulateAfterDays(90);
    secondPlanet.simulateAfterDays(45);
    thirdPlanet.simulateAfterDays(18);

    assertThat(resolver.canResolve(stellarSystem)).isTrue();

  }

}