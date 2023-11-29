package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.repositories.entities.locations.DepartmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.LocalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.MunicipalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.ProvinceRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationsRepositoryTest {
    @Test
    public void DepartamentTest(){
        Long n = 1L;
        Department departament = DepartmentRepository.getInstance().read(n);

        assertEquals("La Matanza", departament.getName());

    }
    @Test
    public void LocalityTest(){
        Long n = 1L;
        Locality locality = LocalityRepository.getInstance().read(n);

        assertEquals("Ciudad Evita", locality.getName());

    }
    @Test
    public void MunicipalityTest(){
        Long n = 1L;
        Municipality municipality = MunicipalityRepository.getInstance().read(n);

        assertEquals("La Matanza", municipality.getName());

    }
    @Test
    public void ProvinceTest(){
        Long n = 1L;
        Province province = ProvinceRepository.getInstance().read(n);

        assertEquals("Buenos Aires", province.getName());

    }
}
