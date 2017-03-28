package com.campolo.weather.domain.forecast;

import com.campolo.weather.domain.calculation.model.WeatherCalculator;
import com.campolo.weather.domain.forecast.model.CalculateForecastUseCase;
import com.campolo.weather.domain.forecast.model.ForecastRepository;
import com.campolo.weather.domain.planet.model.StellarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForecastConfig {

  @Autowired
  private StellarSystem stellarSystem;

  @Autowired
  private WeatherCalculator weatherCalculator;

  @Bean
  public ForecastRepository forecastRepository() {
    return new ForecastRepository(stellarSystem, weatherCalculator);
  }

  @Bean
  public CalculateForecastUseCase calculateForecastUseCase() {
    return new CalculateForecastUseCase(forecastRepository());
  }
}
