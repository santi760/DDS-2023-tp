package ar.edu.utn.frba.dds.models.Importers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvReaderOrganismControl{

    private BufferedReader reader;
    private String line;
    private String data[];
    ArrayList<String[]> lines = new ArrayList<>();

    public ArrayList<ControlOrganism> readCsv(InputStream inputStream){
        ArrayList<ControlOrganism> organismControls = new ArrayList<>();

        try{
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null){
                data = line.split(";");
                    String nombre = data[0];
                    organismControls.add(new ControlOrganism(nombre));
                }
        } catch (Exception fileException){
            JOptionPane.showMessageDialog(null,fileException);
        }
        return organismControls;
    }

}
