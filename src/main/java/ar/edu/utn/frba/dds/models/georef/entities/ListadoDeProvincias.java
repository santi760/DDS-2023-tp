package ar.edu.utn.frba.dds.models.georef.entities;
import ar.edu.utn.frba.dds.models.georef.Provincia;

import java.util.List;

public class ListadoDeProvincias {
  public int cantidad;
  public int inicio;
  public int total;
  public Parametro parametro;
  public List<Provincia> provincias;


  // inner class
  private class Parametro{
    public List<String> campos;

  }

}



// notas :
// todo lo que me olvide de modelar, no va a romper pero no se va a usar.
// las variables de la api se tienen que llamar igual que en mi modelo