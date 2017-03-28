package com.campolo.weather.domain.planet.model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.math3.util.Precision;

public class Planet {

  private String name;

  private int velocityInDegreesPerDay;

  private double radiusInKm;

  private long angleInDegrees;

  private double angleInRadians;

  private Point2D position;

  public Planet(final String name, final int velocityInDegreesPerDay, final double radiusInKm) {
    this.name = name;
    this.velocityInDegreesPerDay = velocityInDegreesPerDay;
    this.radiusInKm = radiusInKm;
    this.position = new Double(radiusInKm, 0.0);
  }

  public Planet copy() {
    Planet planet = new Planet(name, velocityInDegreesPerDay, radiusInKm);
    planet.angleInDegrees = this.angleInDegrees;
    planet.angleInRadians = this.angleInRadians;
    planet.position = new Double(position.getX(), position.getY());
    return planet;
  }

  public void simulateAfterDays(final int days) {
    angleInDegrees = (velocityInDegreesPerDay * days) % 360;
    if (velocityInDegreesPerDay < 0) {
      angleInDegrees = 360 + angleInDegrees;
    }
    angleInRadians = Math.toRadians(angleInDegrees);

    double xAxis = Precision.round(Math.cos(angleInRadians) * radiusInKm, 12);
    double yAxis = Precision.round(Math.sin(angleInRadians) * radiusInKm, 12);

    this.position = new Double(xAxis, yAxis);
  }

  public double getAngleInRadians() {
    return angleInRadians;
  }

  public long getAngleInDegrees() {
    return angleInDegrees;
  }

  public Point2D getPosition() {
    return position;
  }

  public double getX() {
    return this.position.getX();
  }

  public double getY() {
    return this.position.getY();
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("name", name)
        .append("velocityInDegreesPerDay", velocityInDegreesPerDay)
        .append("radiusInKm", radiusInKm)
        .append("angleInDegrees", angleInDegrees)
        .append("angleInRadians", angleInRadians)
        .append("position", position)
        .toString();
  }
}
