package com.campolo.weather.domain.forecast.model;

import org.apache.commons.lang3.Validate;

public class CalculateForecastUseCase {

  private ForecastRepository forecastRepository;

  public CalculateForecastUseCase(
      final ForecastRepository forecastRepository) {
    Validate.notNull(forecastRepository, "The forecast repository cannot be null");
    this.forecastRepository = forecastRepository;
  }

  public Forecast calculate(final int days) {
    return new Forecast(forecastRepository.findAll(days));
  }
}
