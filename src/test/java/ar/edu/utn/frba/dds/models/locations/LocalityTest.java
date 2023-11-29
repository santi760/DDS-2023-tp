package ar.edu.utn.frba.dds.models.locations;

import ar.edu.utn.frba.dds.factories.LocationsFactory;

class LocalidadesTest {

  //* usar estos objetos para testear...
  Province buenosAires = LocationsFactory.createProvince(1, "Buenos Aires");
  Municipality laMatanza = LocationsFactory.createMunicipality(1, "La Matanza");
  Locality ciudadEvita = LocationsFactory.createLocality("1", "Ciudad Evita");
  Department laMatanzaDepartment = LocationsFactory.createDepartment(1, "La Matanza");

  Province cordoba = LocationsFactory.createProvince(2, "Córdoba");
  Municipality capital = LocationsFactory.createMunicipality(2, "Capital");
  Locality cordobaCity = LocationsFactory.createLocality("2", "Córdoba City");
  Department capitalDepartment = LocationsFactory.createDepartment(2, "Capital");


}