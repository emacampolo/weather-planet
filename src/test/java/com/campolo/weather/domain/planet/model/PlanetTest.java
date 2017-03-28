package com.campolo.weather.domain.planet.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.within;

import org.junit.Test;

public class PlanetTest {

  @Test
  public void shouldRotate_positiveVelocity() {
    int velocityInDegreesPerDays = 1;
    Planet planet = new Planet("test", velocityInDegreesPerDays, 1);

    planet.simulateAfterDays(10);
    assertThat(planet.getAngleInDegrees()).isEqualTo(10);

    planet.simulateAfterDays(180);
    assertThat(planet.getAngleInDegrees()).isEqualTo(180);

    planet.simulateAfterDays(360);
    assertThat(planet.getAngleInDegrees()).isEqualTo(0);

    planet.simulateAfterDays(721);
    assertThat(planet.getAngleInDegrees()).isEqualTo(1);

    velocityInDegreesPerDays = 3;
    planet = new Planet("test", velocityInDegreesPerDays, 1);

    planet.simulateAfterDays(10);
    assertThat(planet.getAngleInDegrees()).isEqualTo(30);

    planet.simulateAfterDays(180);
    assertThat(planet.getAngleInDegrees()).isEqualTo(180);

    planet.simulateAfterDays(360);
    assertThat(planet.getAngleInDegrees()).isEqualTo(0);

    planet.simulateAfterDays(721);
    assertThat(planet.getAngleInDegrees()).isEqualTo(3);
  }

  @Test
  public void shouldRotate_negativeVelocity() {
    int velocityInDegreesPerDays = -1;
    Planet planet = new Planet("test", velocityInDegreesPerDays, 1);

    planet.simulateAfterDays(10);
    assertThat(planet.getAngleInDegrees()).isEqualTo(350);

    planet.simulateAfterDays(180);
    assertThat(planet.getAngleInDegrees()).isEqualTo(180);

    planet.simulateAfterDays(190);
    assertThat(planet.getAngleInDegrees()).isEqualTo(170);

    planet.simulateAfterDays(721);
    assertThat(planet.getAngleInDegrees()).isEqualTo(359);
  }

  @Test
  public void positions() {
    Planet planet = new Planet("test", 1, 2000);
    planet.simulateAfterDays(20);
    assertThat(planet.getX()).isEqualTo(1879.385241571817);
    assertThat(planet.getY()).isEqualTo(684.040286651337);

    planet.simulateAfterDays(90);
    assertThat(planet.getX()).isZero();
    assertThat(planet.getY()).isEqualTo(2000);

    planet.simulateAfterDays(180);
    assertThat(planet.getX()).isEqualTo(-2000);
    assertThat(planet.getY()).isZero();

    planet.simulateAfterDays(270);
    assertThat(planet.getX()).isCloseTo(0, within(0.));
    assertThat(planet.getY()).isEqualTo(-2000);

    planet.simulateAfterDays(360);
    assertThat(planet.getX()).isEqualTo(2000);
    assertThat(planet.getY()).isZero();

    planet = new Planet("test", -5, 500);
    planet.simulateAfterDays(20);
    assertThat(planet.getX()).isEqualTo(-86.824088833465);
    assertThat(planet.getY()).isEqualTo(-492.403876506104);
  }

}
