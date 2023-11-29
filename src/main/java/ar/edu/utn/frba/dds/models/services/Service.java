package ar.edu.utn.frba.dds.models.services;

import lombok.*;
import javax.persistence.*;
import java.util.logging.Logger;

@Setter
@Getter
@ToString

@Entity
@Table(name="service")
public class Service {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name="name")
  private String name;

  @Transient
  Logger logger = Logger.getLogger(Service.class.getName()); // esto es por si queremos loguear cosas como en el TP de SO


  //* ------------------------- CONSTRUCTORS ------------------------ *//

  public Service(String name){
    this.name = name;

  }

  public Service(){
    
  }
  //* ------------------------- FUNCTOIONS ------------------------ *//



} // end class Service



// siempre en pegadas a servicios, a pegadas a la DB para ver que trae. y @data es un get,set y tostring. to string me deja printear el objeto con todo lo q tiene dentro
// @noArgsConstructor me crea un constructor vacio. sin nada.
// googlear a ver si @data lo trae o no.

/** ejemplo de como usar el tostring
 *
 *  private Map<String, Boolean> subcomponents = new HashMap();
 *
 *   public void printSubcomponents() {
 *     logger.setLevel(Level.INFO);
 *     subcomponents.put("Bmujeres",true);
 *     subcomponents.put("Bhombres",true);
 *
 *     logger.info("mapita" + subcomponents.toString());
 *   }
 *
 *   public static void main(String[] args) {
 *     Service service = new Service();
 *     service.printSubcomponents();
 *   }
 *
* */
