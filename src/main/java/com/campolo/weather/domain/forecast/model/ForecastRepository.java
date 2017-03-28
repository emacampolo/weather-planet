package com.campolo.weather.domain.forecast.model;

import com.campolo.weather.domain.calculation.model.WeatherCalculator;
import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

public class ForecastRepository {

  private StellarSystem stellarSystem;

  private WeatherCalculator weatherCalculator;

  private Map<Integer, WeatherInfo> cache = new HashMap<>(360);

  public ForecastRepository(final StellarSystem stellarSystem,
      final WeatherCalculator weatherCalculator) {
    Validate.notNull(stellarSystem, "The stellar system cannot be null");
    Validate.notNull(weatherCalculator, "The weather calculator cannot be null");

    this.stellarSystem = stellarSystem;
    this.weatherCalculator = weatherCalculator;
    initCache();
  }

  private void initCache() {
    for (int day = 1; day <= 360; day++) {
      this.stellarSystem.simulateAfterDays(day);
      cache.put(day, this.weatherCalculator.calculate(stellarSystem));
    }
  }

  public Map<Integer, WeatherInfo> findAll(final int days) {
    Map<Integer, WeatherInfo> forecast = new LinkedHashMap<>(days);
    for (int i = 1; i <= days; i++) {
      forecast.put(i, getFromCache(i));
    }
    return forecast;
  }

  public WeatherInfo findBy(final int day) {
    return getFromCache(day);
  }

  private WeatherInfo getFromCache(final int day) {
    if (day % 360 != 0) {
      return cache.get(day % 360);
    } else {
      return cache.get(360);
    }
  }

}
