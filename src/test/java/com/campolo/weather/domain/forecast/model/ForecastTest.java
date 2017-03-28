package com.campolo.weather.domain.forecast.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class ForecastTest {

  private Forecast forecast;

  @Test
  public void getMostRainyDays() {
    Map<Integer, WeatherInfo> weatherByDay = new LinkedHashMap<>();
    StellarSystem stellarSystem = mock(StellarSystem.class);

    weatherByDay.put(3, WeatherInfo.createRain(stellarSystem, 23443.4345));
    weatherByDay.put(1, WeatherInfo.createSunny(stellarSystem));
    weatherByDay.put(2, WeatherInfo.createRain(stellarSystem, 4344.5));
    weatherByDay.put(5, WeatherInfo.createRain(stellarSystem, 4557));
    weatherByDay.put(6, WeatherInfo.createRain(stellarSystem, 1456.32533));
    weatherByDay.put(9, WeatherInfo.createRain(stellarSystem, 72345.84574));
    weatherByDay.put(13, WeatherInfo.createRain(stellarSystem, 353456.6534));
    weatherByDay.put(65, WeatherInfo.createRain(stellarSystem, 4556));

    forecast = new Forecast(weatherByDay);
    Map<Integer, WeatherInfo> mostRainyDays = forecast.getMostRainyDays();
    List<Integer> days = mostRainyDays.keySet().stream().collect(Collectors.toList());

    assertThat(days.get(0)).isEqualTo(13);
    assertThat(days.get(1)).isEqualTo(9);
    assertThat(days.get(2)).isEqualTo(3);
    assertThat(days.get(3)).isEqualTo(5);
    assertThat(days.get(4)).isEqualTo(65);
    assertThat(days.get(5)).isEqualTo(2);
    assertThat(days.get(6)).isEqualTo(6);
  }

}