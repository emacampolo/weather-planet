package com.campolo.weather.web.dto;

import com.campolo.weather.domain.planet.model.StellarSystem;

public class StellarSystemDto {

  private PlanetDto planet1;

  private PlanetDto planet2;

  private PlanetDto planet3;

  public StellarSystemDto(final PlanetDto planet1, final PlanetDto planet2,
      final PlanetDto planet3) {
    this.planet1 = planet1;
    this.planet2 = planet2;
    this.planet3 = planet3;
  }

  public static StellarSystemDto toDto(final StellarSystem stellarSystem) {
    return new StellarSystemDto(PlanetDto.toDto(stellarSystem.getPlanet1()),
        PlanetDto.toDto(stellarSystem.getPlanet2()),
        PlanetDto.toDto(stellarSystem.getPlanet3()));
  }

  public PlanetDto getPlanet1() {
    return planet1;
  }

  public PlanetDto getPlanet2() {
    return planet2;
  }

  public PlanetDto getPlanet3() {
    return planet3;
  }
}
