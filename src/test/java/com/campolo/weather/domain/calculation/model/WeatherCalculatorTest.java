package com.campolo.weather.domain.calculation.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.campolo.weather.domain.planet.model.PlanetFactory;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.Collections;
import org.junit.Test;

public class WeatherCalculatorTest {

  private WeatherCalculator weatherCalculator;

  @Test
  public void shouldReturnTheDefaultResolverWhenNoResolverIsMatched() {
    WeatherResolver weatherResolver = mock(WeatherResolver.class);
    when(weatherResolver.canResolve(any(StellarSystem.class))).thenReturn(false);
    weatherCalculator = new WeatherCalculator(Collections.singletonList(weatherResolver));
    WeatherInfo weatherInfo = weatherCalculator.calculate(mock(StellarSystem.class));
    assertThat(weatherInfo.isDroughtDay()).isFalse();
    assertThat(weatherInfo.isSunnyDay()).isFalse();
    assertThat(weatherInfo.isRainyDay()).isFalse();
  }

  @Test
  public void shouldResolve() {
    StellarSystem stellarSystem = new StellarSystem(PlanetFactory.createBetasoide(),
        PlanetFactory.createBetasoide(),
        PlanetFactory.createBetasoide());
    WeatherResolver weatherResolver = mock(WeatherResolver.class);
    when(weatherResolver.canResolve(any(StellarSystem.class))).thenReturn(true);
    when(weatherResolver.create(stellarSystem))
        .thenReturn(WeatherInfo.createSunny(stellarSystem));
    weatherCalculator = new WeatherCalculator(Collections.singletonList(weatherResolver));
    WeatherInfo weatherInfo = weatherCalculator.calculate(stellarSystem);
    assertThat(weatherInfo.isDroughtDay()).isFalse();
    assertThat(weatherInfo.isSunnyDay()).isTrue();
    assertThat(weatherInfo.isRainyDay()).isFalse();
  }

}