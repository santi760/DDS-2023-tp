package ar.edu.utn.frba.dds.controllers;

import static ar.edu.utn.frba.dds.models.users.RolType.CONTROL_ORGANISM;
import ar.edu.utn.frba.dds.models.Importers.ServiceImporter;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Report;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.ServiceRepository;
import ar.edu.utn.frba.dds.repositories.users.ControlOrganismRepository;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlOrganismController extends Controller implements ICrudViewsHandler {



  public ControlOrganismController(ControlOrganismRepository instance) {

  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }

  public void updateControlOrganismProviderEntities(Context context){
    User user = super.usuarioLogueado(context);

    super.checkRolType(user, CONTROL_ORGANISM);


    ControlOrganism controlOrganism = ControlOrganismRepository.getInstance().getControlOrganismWithUser(user);
    Map<String, Object> model = new HashMap<>();
    model.put("controlOrganism", controlOrganism);
    List<ProviderEntity> providerEntities = ProviderEntityRepository.getInstance().readNotIncludedOrganism(controlOrganism);

    model.put("providerEntities", providerEntities);
    context.render("entities/addProviderEntity.hbs", model);

  }

  public void addProviderEntity(Context context){

    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);

    ControlOrganism controlOrganism = ControlOrganismRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().read(Long.parseLong(context.pathParam("providerEntityId")));

     ProviderEntityRepository.getInstance().addOrganism(providerEntity,controlOrganism);
     ControlOrganismRepository.getInstance().update(controlOrganism);

    context.redirect("/controlOrganism/providerEntities");
  }

  public void deleteProviderEntity(Context context){
    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);
    User user = super.usuarioLogueado(context);

    ProviderEntityRepository repository = ProviderEntityRepository.getInstance();
    ControlOrganism controlOrganism = ControlOrganismRepository.getInstance().getControlOrganismWithUser(user);
    ProviderEntity providerEntity = repository.read(Long.parseLong(context.pathParam("id")));

    repository.removeOrganismFromProvider(providerEntity);
    ControlOrganismRepository.getInstance().update(controlOrganism);


    List<ProviderEntity> providerEntities = repository.getEntitiesFromOrganism(controlOrganism);

    Map<String, Object> model = new HashMap<>();
    model.put("controlOrganism", controlOrganism);
    model.put("providerEntities", providerEntities);
    context.render("entities/providerEntities.hbs", model);
  }

  public void showControlOrganismProviderEntities(Context context) {

    User user = super.usuarioLogueado(context);
    super.checkRolType(user, CONTROL_ORGANISM);

    ControlOrganism controlOrganism = ControlOrganismRepository.getInstance().getControlOrganismWithUser(user);
    List<ProviderEntity> providerEntities = ProviderEntityRepository.getInstance().getEntitiesFromOrganism(controlOrganism);

    Map<String, Object> model = new HashMap<>();
    model.put("controlOrganism", controlOrganism);
    model.put("providerEntities", providerEntities);
    context.render("entities/providerEntities.hbs", model);

  }

  public void showEntitiesFromProvider(Context context) {
    User user = super.usuarioLogueado(context);

    super.checkRolType(user, CONTROL_ORGANISM);

    ProviderEntity providerEntity = (ProviderEntity) ProviderEntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);
    Map<String, Object> model = new HashMap<>();
    model.put("Entity", entities);
    context.render("entities/entitiesFromControlOrganismProvider.hbs", model);

  }

  public void showToUploadServices(Context context) {

    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);

    Map<String, Object> model = new HashMap<>();
    context.render("entities/MassiveUploadServices.hbs",model);
  }

  public void uploadServices(Context context) {

    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);

    UploadedFile uploadedFile = context.uploadedFile("input-csv");
    InputStream inputStream = uploadedFile.content();

    ServiceImporter csvReader = new ServiceImporter();

    ArrayList<Service> servicesFromCsv = csvReader.importServices(inputStream);
    ArrayList<Service> services = new ArrayList<>();
    List<Service> allServices = ServiceRepository.getInstance().readAll();

    for(int i=0 ; servicesFromCsv.size() > i ; i++){

      int finalI = i;
      if(allServices.stream().noneMatch(service -> servicesFromCsv.get(finalI).getName().toUpperCase().equals(service.getName().toUpperCase()))){
        services.add(servicesFromCsv.get(i));
      }

    }

    services.forEach(service -> ServiceRepository.getInstance().create(service));

    Map<String, Object> model = new HashMap<>();
    model.put("services",services);
    context.render("entities/servicesFromFile.hbs", model);

  }

  public void showServices(Context context) {
    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);
    ServiceRepository repository = ServiceRepository.getInstance();

    List<Service> services = repository.readAll();
    Map<String, Object> model = new HashMap<>();
    model.put("services",services);
    context.render("entities/services.hbs", model);

  }

  public void report(Context context) {
    super.checkRolType(super.usuarioLogueado(context), CONTROL_ORGANISM);

    ProviderEntity providerEntity = (ProviderEntity) ProviderEntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    List<Entity> entities = providerEntity.getEntities().stream().toList();

    try {
      ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
      Document document = new Document();
      PdfWriter pdfWriter = PdfWriter.getInstance(document, pdfOutputStream);

      document.open();
      document.addTitle(providerEntity.getName());
      // Generar el contenido del informe
      Report reporte = new Report("Reporte Semanal", providerEntity);
      reporte.generateReport(entities);
      Map<String, List<String>> reportData = reporte.getReportData();

      // Agregar título al PDF
      Paragraph title = new Paragraph("Reporte Semanal de " + providerEntity.getName());
      document.add(title);

      // Agregar fecha al PDF
      LocalDate processingDate = reporte.getProcessingDate();
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String formattedDate = processingDate.format(dateFormatter);
      Paragraph date = new Paragraph("Fecha del informe: " + formattedDate);
      document.add(date);

      // Agregar separación
      Paragraph separation = new Paragraph("--------------------------------------------------");
      document.add(separation);

      // Agregar contenido del informe
      for (Map.Entry<String, List<String>> entry : reportData.entrySet()) {
        String rankingName = entry.getKey();
        List<String> rankingData = entry.getValue();
        Paragraph rankingTitle = new Paragraph("Ranking: " + rankingName);
        document.add(rankingTitle);
        for (String entity : rankingData) {
          Paragraph entityName = new Paragraph(entity);
          document.add(entityName);
        }
      }
      document.close();

      // Convertir el documento PDF en base64
      byte[] pdfBytes = pdfOutputStream.toByteArray();
      pdfOutputStream.close();
      String base64PDF = Base64.getEncoder().encodeToString(pdfBytes);

      String pdfFileName = providerEntity.getName() + ".pdf";

      Map<String, Object> model = new HashMap<>();
      model.put("base64PDF", base64PDF);
      model.put("pdfFileName", pdfFileName);
      context.render("public/report.hbs", model);
    } catch (IOException | DocumentException e) {
      e.printStackTrace();
    }
  }

}
