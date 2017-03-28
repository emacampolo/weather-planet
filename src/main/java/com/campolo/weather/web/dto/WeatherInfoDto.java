package com.campolo.weather.web.dto;

import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.calculation.model.WeatherType;

public class WeatherInfoDto {

  private int day;

  private WeatherType weatherType;

  private double precipitation;

  private StellarSystemDto stellarSystem;

  public WeatherInfoDto(final int day, final WeatherType weatherType, final double precipitation,
      final StellarSystemDto stellarSystem) {
    this.day = day;
    this.weatherType = weatherType;
    this.precipitation = precipitation;
    this.stellarSystem = stellarSystem;
  }

  public static WeatherInfoDto toDto(final int day, WeatherInfo weatherInfo) {
    return new WeatherInfoDto(day, weatherInfo.getWeatherType(),
        weatherInfo.getPrecipitation(), StellarSystemDto.toDto(weatherInfo.getStellarSystem()));
  }

  public int getDay() {
    return day;
  }

  public WeatherType getWeatherType() {
    return weatherType;
  }

  public double getPrecipitation() {
    return precipitation;
  }

  public StellarSystemDto getStellarSystem() {
    return stellarSystem;
  }
}
