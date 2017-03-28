package com.campolo.weather.domain.forecast.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.campolo.weather.domain.calculation.model.WeatherCalculator;
import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.calculation.model.WeatherResolver;
import com.campolo.weather.domain.planet.model.Planet;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastRepositoryTest {

  private static final int TEN_YEARS_IN_DAYS = 3650;

  @Autowired
  private ForecastRepository forecastRepository;

  @Test
  public void shouldCacheOnceSystemPeriodIsCompleted() {
    Planet planet1 = new Planet("Planet 1", 1, 1);
    Planet planet2 = new Planet("Planet 2", 1, 1);
    Planet planet3 = new Planet("Planet 3", 1, 1);
    StellarSystem stellarSystem = new StellarSystem(planet1, planet2, planet3);
    WeatherResolver resolver = mock(WeatherResolver.class);
    when(resolver.canResolve(stellarSystem)).thenReturn(true);
    AtomicInteger precipitation = new AtomicInteger(1);
    when(resolver.create(stellarSystem))
        .thenAnswer(
            invocation -> WeatherInfo.createRain(stellarSystem, precipitation.getAndIncrement()));
    WeatherCalculator weatherCalculator = new WeatherCalculator(
        Collections.singletonList(resolver));
    forecastRepository = new ForecastRepository(stellarSystem, weatherCalculator);
    WeatherInfo p1 = forecastRepository.findBy(361);
    WeatherInfo p2 = forecastRepository.findBy(362);
    WeatherInfo p3 = forecastRepository.findBy(363);
    assertThat(p1.getPrecipitation()).isEqualTo(1);
    assertThat(p2.getPrecipitation()).isEqualTo(2);
    assertThat(p3.getPrecipitation()).isEqualTo(3);
  }

  @Test
  public void calculate() {
    Map<Integer, WeatherInfo> forecast = forecastRepository.findAll(TEN_YEARS_IN_DAYS);
    List<WeatherInfo> droughtForecast = forecast.values().stream().filter(WeatherInfo::isDroughtDay)
        .collect(Collectors.toList());
    List<WeatherInfo> sunnyForecast = forecast.values().stream().filter(WeatherInfo::isSunnyDay)
        .collect(Collectors.toList());
    List<WeatherInfo> rainyForecast = forecast.values().stream().filter(WeatherInfo::isRainyDay)
        .collect(Collectors.toList());
    assertThat(droughtForecast).hasSize(81);
    assertThat(sunnyForecast).hasSize(0);
    assertThat(rainyForecast).hasSize(1177);
  }
}