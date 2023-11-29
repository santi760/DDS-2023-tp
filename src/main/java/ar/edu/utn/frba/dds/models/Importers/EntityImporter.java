package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.builders.EntityBuilder;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityImporter {

    private BufferedReader reader;
    private String line;
    private String data[];
    ArrayList<String[]> lines = new ArrayList<>();


    public ArrayList<Entity> importEntities(InputStream inputStream) {
        ArrayList<Entity> entities = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                data = line.split(";");
                String[] data1 = data[0].split(",");
                String name = data1[0];
                String description = data1[1];
                EntityContainerType type = new EntityContainerType();
                type.setType(EntityType.valueOf(data1[2]));
                if(data1.length > 3){
                    type.setTypePublicTransport(PublicTransportTpye.valueOf(data1[3]));
                }

                Entity entity = new EntityBuilder()
                    .withName(name).withTypeEntity(type).build();
                entities.add(entity);

            }
        } catch (Exception fileException) {
            JOptionPane.showMessageDialog(null, fileException);
        }

        return entities;
    }
}
