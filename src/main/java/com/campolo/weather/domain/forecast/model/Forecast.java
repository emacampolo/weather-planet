package com.campolo.weather.domain.forecast.model;

import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.calculation.model.WeatherType;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Forecast {

  private Map<Integer, WeatherInfo> forecast;

  public Forecast(final Map<Integer, WeatherInfo> forecast) {
    this.forecast = forecast;
  }

  public long getTotalSunnyDays() {
    return getTotalDaysByType(WeatherType.SUNNY);
  }

  public long getTotalRainyDays() {
    return getTotalDaysByType(WeatherType.RAIN);
  }

  public long getTotalDroughtDays() {
    return getTotalDaysByType(WeatherType.DROUGHT);
  }

  public Map<Integer, WeatherInfo> getMostRainyDays() {
    Stream<Entry<Integer, WeatherInfo>> collected = forecast.entrySet().stream()
        .filter(integerWeatherInfoEntry
            -> WeatherType.RAIN.equals(integerWeatherInfoEntry.getValue().getWeatherType()))
        .sorted(Entry.comparingByValue(
            Comparator.comparingDouble(WeatherInfo::getPrecipitation).reversed()))
        .limit(150);
    Map<Integer, WeatherInfo> mostRainyDays = new LinkedHashMap<>(10);
    collected.forEach(e -> mostRainyDays.put(e.getKey(), e.getValue()));
    return mostRainyDays;
  }

  private long getTotalDaysByType(final WeatherType weatherType) {
    return forecast.values().stream()
        .filter(weatherInfo -> weatherType.equals(weatherInfo.getWeatherType())).count();
  }
}
