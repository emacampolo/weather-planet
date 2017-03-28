package com.campolo.weather.domain.calculation;

import com.campolo.weather.domain.calculation.model.DroughtDayWeatherResolver;
import com.campolo.weather.domain.calculation.model.RainyDayWeatherResolver;
import com.campolo.weather.domain.calculation.model.SunnyDayWeatherResolver;
import com.campolo.weather.domain.calculation.model.WeatherCalculator;
import com.campolo.weather.domain.planet.model.StellarSystem;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculationConfig {

  @Autowired
  private StellarSystem stellarSystem;

  @Bean
  public WeatherCalculator weatherCalculator() {
    return new WeatherCalculator(Arrays.asList(
        new SunnyDayWeatherResolver(),
        new RainyDayWeatherResolver(),
        new DroughtDayWeatherResolver()));
  }
}
