package com.campolo.weather.web.dto;

import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.forecast.model.Forecast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ForecastDto {

  private long totalSunnyDays;

  private long totalRainyDays;

  private long totalDroughtDays;

  private List<WeatherInfoDto> mostRainyDays;

  public static ForecastDto toDto(final Forecast forecast) {
    ForecastDto dto = new ForecastDto();
    dto.setTotalDroughtDays(forecast.getTotalDroughtDays());
    dto.setTotalRainyDays(forecast.getTotalRainyDays());
    dto.setTotalSunnyDays(forecast.getTotalSunnyDays());

    Map<Integer, WeatherInfo> mostRainyDays = forecast.getMostRainyDays();
    LinkedList<WeatherInfoDto> mostRainyDaysDto = new LinkedList<>();

    mostRainyDays.forEach(
        (day, weatherInfo) -> mostRainyDaysDto.addLast(WeatherInfoDto.toDto(day, weatherInfo)));
    dto.setMostRainyDays(mostRainyDaysDto);
    return dto;
  }

  public long getTotalSunnyDays() {
    return totalSunnyDays;
  }

  public void setTotalSunnyDays(final long totalSunnyDays) {
    this.totalSunnyDays = totalSunnyDays;
  }

  public long getTotalRainyDays() {
    return totalRainyDays;
  }

  public void setTotalRainyDays(final long totalRainyDays) {
    this.totalRainyDays = totalRainyDays;
  }

  public long getTotalDroughtDays() {
    return totalDroughtDays;
  }

  public void setTotalDroughtDays(final long totalDroughtDays) {
    this.totalDroughtDays = totalDroughtDays;
  }

  public List<WeatherInfoDto> getMostRainyDays() {
    return mostRainyDays;
  }

  public void setMostRainyDays(
      final List<WeatherInfoDto> mostRainyDays) {
    this.mostRainyDays = mostRainyDays;
  }

}