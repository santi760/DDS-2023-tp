package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.builders.EstablishmentBuilder;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstablishmentsImporter {

    private BufferedReader reader;
    private String line;
    private String data[];
    ArrayList<String[]> lines = new ArrayList<>();


    public ArrayList<Establishment> importEstablishments(InputStream inputStream, Entity entity) {
        ArrayList<Establishment> establishments = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine(); //asi me salteo la primera linea
            while ((line = reader.readLine()) != null) {
                data = line.split(";");
                String[] data1 = data[0].split(",");
                String name = data1[0];
                String description = data1[1];
                EstablishmentType type_establishment = EstablishmentType.valueOf(data1[2]);

                Establishment establishment = new EstablishmentBuilder()
                    .withName(name)
                    .withDescription(description)
                    .withEstablishmentType(type_establishment)
                    .build();

               // entity.addEstablishment(establishment);

                establishments.add(establishment);
            }
        } catch (Exception fileException) {
            JOptionPane.showMessageDialog(null, fileException);
        }

        return establishments;
    }
}
