package com.campolo.weather.web;

import com.campolo.weather.domain.calculation.model.WeatherInfo;
import com.campolo.weather.domain.forecast.model.CalculateForecastUseCase;
import com.campolo.weather.domain.forecast.model.Forecast;
import com.campolo.weather.domain.forecast.model.ForecastRepository;
import com.campolo.weather.web.dto.ForecastDto;
import com.campolo.weather.web.dto.WeatherInfoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ForecastController {

  @Autowired
  private CalculateForecastUseCase calculateForecastUseCase;

  @Autowired
  private ForecastRepository forecastRepository;

  @ApiOperation(notes = "Returns the extended forecast for the next N>0 days.", value = "Find extended forecast", nickname = "calculateForcast")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Success", response = ForecastDto.class),
      @ApiResponse(code = 400, message = "Invalid days supplied", response = HttpApiError.class)
  })
  @RequestMapping(value = "/forecast/{days}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<ForecastDto> calculateForcast(
      @ApiParam(value = "Number of days to be calculated", allowableValues = "range[1,infinity]", required = true) @PathVariable("days") Integer days)
      throws Exception {
    if (days < 1) {
      throw new IllegalArgumentException("Days must be greater than or equal to 1");
    }

    Forecast forecast = calculateForecastUseCase.calculate(days);
    return ResponseEntity.ok(ForecastDto.toDto(forecast));
  }

  @ApiOperation(notes = "Returns the weather for a given day", value = "Weather for a given day", nickname = "calculateWeatherDay")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Success", response = WeatherInfoDto.class),
      @ApiResponse(code = 400, message = "Invalid days supplied", response = HttpApiError.class)
  })
  @RequestMapping(value = "/weather/{day}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<WeatherInfoDto> calculateWeatherDay(
      @ApiParam(value = "A day in the future", allowableValues = "range[1,infinity]", required = true) @PathVariable("day") Integer day)
      throws Exception {
    if (day < 1) {
      throw new IllegalArgumentException("Day must be greater than or equal to 1");
    }

    WeatherInfo weatherInfo = forecastRepository.findBy(day);
    return ResponseEntity.ok(WeatherInfoDto.toDto(day, weatherInfo));
  }

  @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
  @ResponseBody
  public ResponseEntity<?> handleBadRequests(final Exception ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    HttpApiError error = HttpApiError.create(status, ex.getMessage());
    return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
  }
}
