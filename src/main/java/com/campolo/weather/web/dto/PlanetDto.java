package com.campolo.weather.web.dto;

import com.campolo.weather.domain.planet.model.Planet;
import java.awt.geom.Point2D;

public class PlanetDto {

  private String name;

  private long degrees;

  private Point2D position;

  public PlanetDto(final String name, final long degrees, final Point2D position) {
    this.name = name;
    this.degrees = degrees;
    this.position = position;
  }

  public static PlanetDto toDto(final Planet planet) {
    return new PlanetDto(planet.getName(), planet.getAngleInDegrees(), planet.getPosition());
  }

  public String getName() {
    return name;
  }

  public long getDegrees() {
    return degrees;
  }

  public Point2D getPosition() {
    return position;
  }
}
