package com.campolo.weather.domain.planet.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StellarSystem {

  private Planet planet1;

  private Planet planet2;

  private Planet planet3;

  public StellarSystem(final Planet planet1, final Planet planet2, final Planet planet3) {
    Validate.notNull(planet1, "Planet 1 cannot be null");
    Validate.notNull(planet2, "Planet 2 cannot be null");
    Validate.notNull(planet3, "Planet 3 cannot be null");

    this.planet1 = planet1;
    this.planet2 = planet2;
    this.planet3 = planet3;
  }

  public StellarSystem copy() {
    return new StellarSystem(planet1.copy(), planet2.copy(), planet3.copy());
  }

  public Planet getPlanet1() {
    return planet1;
  }

  public Planet getPlanet2() {
    return planet2;
  }

  public Planet getPlanet3() {
    return planet3;
  }

  public void simulateAfterDays(final int days) {
    this.planet1.simulateAfterDays(days);
    this.planet2.simulateAfterDays(days);
    this.planet3.simulateAfterDays(days);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("planet1", planet1)
        .append("planet2", planet2)
        .append("planet3", planet3)
        .toString();
  }
}
