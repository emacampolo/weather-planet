package com.campolo.weather.domain.calculation.model;

import com.campolo.weather.domain.planet.model.StellarSystem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WeatherInfo {

  private StellarSystem stellarSystem;

  private WeatherType weatherType;

  private double precipitation;

  WeatherInfo(final StellarSystem stellarSystem,
      final WeatherType weatherType, final double precipitation) {
    this.stellarSystem = stellarSystem.copy();
    this.weatherType = weatherType;
    this.precipitation = precipitation;
  }

  public static WeatherInfo createSunny(final StellarSystem stellarSystem) {
    return new WeatherInfo(stellarSystem, WeatherType.SUNNY, 0.0);
  }

  public static WeatherInfo createDrought(final StellarSystem stellarSystem) {
    return new WeatherInfo(stellarSystem, WeatherType.DROUGHT, 0.0);
  }

  public static WeatherInfo createRain(final StellarSystem stellarSystem,
      final double precipitation) {
    return new WeatherInfo(stellarSystem, WeatherType.RAIN, precipitation);
  }

  public boolean isSunnyDay() {
    return WeatherType.SUNNY.equals(weatherType);
  }

  public boolean isDroughtDay() {
    return WeatherType.DROUGHT.equals(weatherType);
  }

  public boolean isRainyDay() {
    return WeatherType.RAIN.equals(weatherType);
  }

  public WeatherType getWeatherType() {
    return weatherType;
  }

  public StellarSystem getStellarSystem() {
    return stellarSystem;
  }

  public double getPrecipitation() {
    return precipitation;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("stellarSystem", stellarSystem)
        .append("weatherType", weatherType)
        .append("precipitation", precipitation)
        .toString();
  }
}
