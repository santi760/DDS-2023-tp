package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.models.services.Service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceImporter {

    private BufferedReader reader;
    private String line;
    private String data[];
    ArrayList<String[]> lines = new ArrayList<>();


    public ArrayList<Service> importServices(InputStream inputStream) {
        ArrayList<Service> services = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.readLine(); //asi me salteo la primera linea
            while ((line = reader.readLine()) != null) {
                data = line.split(";");
                String[] data1 = data[0].split(",");
                String name = data1[0];

                Service service = new Service();
                service.setName(name);

                services.add(service);
            }
        } catch (Exception fileException) {
            JOptionPane.showMessageDialog(null, fileException);
        }

        return services;
    }
}
