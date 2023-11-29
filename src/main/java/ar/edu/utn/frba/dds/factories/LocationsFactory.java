package ar.edu.utn.frba.dds.factories;

import ar.edu.utn.frba.dds.models.locations.Coordinate;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;

public class LocationsFactory {

  public static Province createProvince(int id, String name) {
    Province province = new Province();
    province.setIdProvince(id);
    province.setName(name);
    return province;
  }

  public static Municipality createMunicipality(int id, String name) {
    Municipality municipality = new Municipality();
    municipality.setId(id);
    municipality.setName(name);
    return municipality;
  }

  public static Locality createLocality(String id, String name) {
    Locality locality = new Locality();
    locality.setId(id);
    locality.setName(name);
    return locality;
  }

  public static Department createDepartment(int id, String name) {
    Department department = new Department();
    department.setId(id);
    department.setName(name);
    return department;
  }

  public static Coordinate createCoordinate(double latitud, double longitude) {
    Coordinate coordinate = new Coordinate();
    coordinate.setLatitude(latitud);
    coordinate.setLongitude(longitude);
    return coordinate;
  }
}