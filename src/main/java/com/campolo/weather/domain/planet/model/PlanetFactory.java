package com.campolo.weather.domain.planet.model;

public class PlanetFactory {

  public static Planet createFerengi() {
    return new Planet("Ferengi", 1, 500);
  }

  public static Planet createBetasoide() {
    return new Planet("Betasoide", 3, 2000);
  }

  public static Planet createVulcano() {
    return new Planet("Vulcano", -5, 1000);
  }

}
