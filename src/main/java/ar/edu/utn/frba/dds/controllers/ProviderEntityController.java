package ar.edu.utn.frba.dds.controllers;


import ar.edu.utn.frba.dds.builders.ProvisionOfServiceBuilder;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.Importers.EntityImporter;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.EstablishmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.ProvisionOfServiceRepository;
import ar.edu.utn.frba.dds.repositories.entities.ServiceRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.Importers.EstablishmentsImporter;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Report;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.users.ControlOrganismRepository;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import com.itextpdf.text.DocumentException;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;


import static ar.edu.utn.frba.dds.models.users.RolType.*;


public class ProviderEntityController extends Controller implements ICrudViewsHandler {


  public ProviderEntityController(ProviderEntityRepository repositorioDeEntidadesPrestadoras, ControlOrganismRepository repositorioDeOrganismosDeControl) {
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

  public void deleteEntity(Context context){
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);
    User user = super.usuarioLogueado(context);

    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    Entity entity = EntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

    EntityRepository.getInstance().delete(entity);
    ProviderEntityRepository.getInstance().update(providerEntity);

    EntityRepository repository = EntityRepository.getInstance();

    List<Entity> entities = repository.getEntitiesFromProvider(providerEntity);

    Map<String, Object> model = new HashMap<>();
    model.put("providerEntity", providerEntity);
    model.put("entities",entities );
    context.render("entities/entitiesFromProviderEntity.hbs", model);

  }

  public void deleteEstablishment(Context context){
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    Entity entity = EntityRepository.getInstance().read(Long.parseLong(context.pathParam("otherId")));
    Establishment establishment = EstablishmentRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

    EstablishmentRepository.getInstance().removeRelationWithEntity(establishment);
    EntityRepository.getInstance().update(entity);

    EstablishmentRepository repository = EstablishmentRepository.getInstance();
    repository.entityManager().clear();
    List<Establishment> establishments = repository.getEstablishmentsFromEntity(entity);
    Map<String, Object> model = new HashMap<>();
    model.put("entity", entity);
    model.put("establishments", establishments);
    context.render("entities/establishmentsFromEntity.hbs", model);
  }

  public void deleteProvisionOfService(Context context){
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    ProvisionOfServiceRepository repository = ProvisionOfServiceRepository.getInstance();
    ProvisionOfService provisionOfService = repository.read(Long.parseLong(context.pathParam("id")));
    repository.delete(provisionOfService);
    repository.entityManager().clear();

    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);
    List<Establishment> establishments = new ArrayList<>();
    List<ProvisionOfService> provisionOfServices = new ArrayList<>();
    EstablishmentRepository repositoryOfEstablishments = EstablishmentRepository.getInstance();

    entities.forEach(entity -> establishments.addAll(repositoryOfEstablishments.getEstablishmentsFromEntity(entity)));
    ProvisionOfServiceRepository provisionOfServiceRepository = ProvisionOfServiceRepository.getInstance();

    establishments.forEach(establishment -> provisionOfServices.addAll(provisionOfServiceRepository.getProvisionsOfServiceByEstablishmentId(establishment.getId())));

    Map<String, Object> model = new HashMap<>();
    model.put("prestaciones", provisionOfServices);
    context.render("entities/provisionOfServices.hbs", model);

  }

  public void showToAddProviderEntities(Context context){

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    List<Service> services = ServiceRepository.getInstance().readAll();
    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);
    List<Establishment> establishments = new ArrayList<>();
    EstablishmentRepository repository = EstablishmentRepository.getInstance();
    repository.entityManager().clear();
    entities.forEach(entity -> establishments.addAll(repository.getEstablishmentsFromEntity(entity)));

    Map<String, Object> model = new HashMap<>();
    model.put("services", services);
    model.put("establishments",establishments);
    context.render("entities/addProvisionOfService.hbs",model);
  }

  public void addProvisionOfService(Context context){

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);
    Service service = ServiceRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    Establishment establishment = EstablishmentRepository.getInstance().read(Long.parseLong(context.pathParam("otherId")));
    String name = service.getName() + " de " + establishment.getName();

    ProvisionOfService provisionOfService = new ProvisionOfServiceBuilder().
        withService(service).
        withEstablishment(establishment).
        withName(name).build();

    ProvisionOfServiceRepository.getInstance().create(provisionOfService);

    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);
    List<Establishment> establishments = new ArrayList<>();
    List<ProvisionOfService> provisionOfServices = new ArrayList<>();
    EstablishmentRepository repository = EstablishmentRepository.getInstance();

    entities.forEach(entity -> establishments.addAll(repository.getEstablishmentsFromEntity(entity)));
    ProvisionOfServiceRepository provisionOfServiceRepository = ProvisionOfServiceRepository.getInstance();

    establishments.forEach(establishment1 -> provisionOfServices.addAll(provisionOfServiceRepository.getProvisionsOfServiceByEstablishmentId(establishment1.getId())));

    Map<String, Object> model = new HashMap<>();
    model.put("prestaciones", provisionOfServices);


    context.render("entities/provisionOfServices.hbs", model);

  }

  public void showProvisionOfServices(Context context){

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);
    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);
    List<Establishment> establishments = new ArrayList<>();
    List<ProvisionOfService> provisionOfServices = new ArrayList<>();
    EstablishmentRepository repository = EstablishmentRepository.getInstance();
    repository.entityManager().clear();
    entities.forEach(entity -> establishments.addAll(repository.getEstablishmentsFromEntity(entity)));
    ProvisionOfServiceRepository provisionOfServiceRepository = ProvisionOfServiceRepository.getInstance();
    provisionOfServiceRepository.entityManager().clear();
    establishments.forEach(establishment -> provisionOfServices.addAll(provisionOfServiceRepository.getProvisionsOfServiceByEstablishmentId(establishment.getId())));

    Map<String, Object> model = new HashMap<>();
    model.put("prestaciones", provisionOfServices);


    context.render("entities/provisionOfServices.hbs", model);
  }

  public void showToUploadEstablishments(Context context) {

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);
    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);

    List<Entity> entities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);

    Map<String, Object> model = new HashMap<>();
    model.put("entidades", entities);
    context.render("entities/MassiveUploadEstablishments.hbs",model);
  }

  public void showToUploadEntities(Context context) {

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);
    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    Map<String, Object> model = new HashMap<>();
    model.put("providerEntity", providerEntity);
    context.render("entities/MassiveUploadEntities.hbs");
  }



  public void uploadEstablishments(Context context) {

    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    Entity entity = EntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    UploadedFile uploadedFile = context.uploadedFile("input-csv");
    InputStream inputStream = uploadedFile.content();

    EstablishmentsImporter csvReader = new EstablishmentsImporter();

    ArrayList<Establishment> establishmentsFromCsv = csvReader.importEstablishments(inputStream,entity);
    ArrayList<Establishment> establishments = new ArrayList<>();
    List<Establishment> allEstablishments = EstablishmentRepository.getInstance().getEstablishmentsFromEntity(entity);

    for(int i=0 ; establishmentsFromCsv.size() > i ; i++){

      int finalI = i;
      if(allEstablishments.stream().noneMatch(establishment -> establishmentsFromCsv.get(finalI).getName().equals(establishment.getName()))){
        establishments.add(establishmentsFromCsv.get(i));
        entity.addEstablishment(establishmentsFromCsv.get(i));
      }

    }

    establishments.forEach(establishment -> EstablishmentRepository.getInstance().create(establishment));
    EntityRepository.getInstance().update(entity);
    Map<String, Object> model = new HashMap<>();
    //establishments.remove(establishments.size()-1);
    model.put("entity",entity);
    model.put("establishments", establishments);
    context.render("entities/establishmentsFromFile.hbs", model);

  }

  public void uploadEntities(Context context) {

    User user = super.usuarioLogueado(context);
    super.checkRolType(user, PROVIDER);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    UploadedFile uploadedFile = context.uploadedFile("input-csv");
    InputStream inputStream = uploadedFile.content();

    EntityImporter csvReader = new EntityImporter();

    ArrayList<Entity> entitiesFromCsv = csvReader.importEntities(inputStream);
    ArrayList<Entity> entities = new ArrayList<>();
    List<Entity> allEntities = EntityRepository.getInstance().getEntitiesFromProvider(providerEntity);

    for(int i=0 ; entitiesFromCsv.size() > i ; i++){

      int finalI = i;
      if(allEntities.stream().noneMatch(establishment -> entitiesFromCsv.get(finalI).getName().toUpperCase().equals(establishment.getName().toUpperCase()))){
        entities.add(entitiesFromCsv.get(i));

      }

    }

    //entities.forEach(entity -> providerEntity.addEntity(entity));
    entities.forEach(entity -> EntityRepository.getInstance().create(entity));
    for (int i = 0; entities.size() > i;i++){
      EntityRepository.getInstance().updateProvider(entities.get(i),providerEntity);
    }
    ProviderEntityRepository.getInstance().update(providerEntity);

    Map<String, Object> model = new HashMap<>();
    model.put("entities",entities);

    context.render("entities/entitiesFromFile.hbs",model);

  }




  public void showEntitiesFromProviderEntity(Context context) {

    User user = super.usuarioLogueado(context);
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    EntityRepository repository = EntityRepository.getInstance();
    repository.entityManager().clear();
    List<Entity> entities = repository.getEntitiesFromProvider(providerEntity);

    Map<String, Object> model = new HashMap<>();
    model.put("providerEntity", providerEntity);
    model.put("entities",entities );
    context.render("entities/entitiesFromProviderEntity.hbs", model);

  }

  public void showEstablishmentsFromEntity(Context context){

    User user = super.usuarioLogueado(context);
    super.checkRolType(user, PROVIDER);

    Entity entity = EntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    EstablishmentRepository repository = EstablishmentRepository.getInstance();
    repository.entityManager().clear();
    List<Establishment> establishments = repository.getEstablishmentsFromEntity(entity);

    Map<String, Object> model = new HashMap<>();
    model.put("entity", entity);
    model.put("establishments", establishments);
    context.render("entities/establishmentsFromEntity.hbs", model);

  }

  public void updateProviderEntityEntities(Context context){
    User user = super.usuarioLogueado(context);
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);
    Map<String, Object> model = new HashMap<>();
    model.put("providerEntity", providerEntity);
    List<Entity> entities = EntityRepository.getInstance().readAll().stream().filter(entity -> !providerEntity.getEntities().contains(entity)).toList();
    model.put("providerEntities", entities);
    context.render("entities/addEntities.hbs", model);
  }


  public void addEntity(Context context){
    super.checkRolType(super.usuarioLogueado(context), PROVIDER);

    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    Entity entity = EntityRepository.getInstance().read(Long.parseLong(context.pathParam("entityId")));
    providerEntity.addEntity(entity);
    ProviderEntityRepository.getInstance().update(providerEntity);
    context.redirect("/providerEntity/entities");
  }

  public void report(Context context) {
    User user = super.usuarioLogueado(context);
    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().getProviderEntityWithUser(user);

    super.checkRolType(user, PROVIDER);

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

  public void showCreateProviderEntity(Context context) {
    User user = super.usuarioLogueado(context);


    super.checkRolType(user, PROVIDER);

    Map<String, Object> model = new HashMap<>();
    model.put("services", ServiceRepository.getInstance().readAll());
    model.put("establishments", EstablishmentRepository.getInstance().readAll());
    context.render("entities/createProviderEntity.hbs", model);
  }

  public void createProviderEntity(Context context) {
    User user = super.usuarioLogueado(context);

    super.checkRolType(user, PROVIDER);


    String name = context.formParam("name");
    Long serviceId = Long.parseLong(context.formParam("services"));
    Long selectedEstablishmentId = Long.parseLong(context.formParam("establishments"));

    Service selectedService = ServiceRepository.getInstance().read(serviceId);
    Establishment selectedEstablishment = EstablishmentRepository.getInstance().read(selectedEstablishmentId);

    ProvisionOfService provisionOfService = new ProvisionOfServiceBuilder()
        .withName(name)
        .withService(selectedService)
        .withEstablishment(selectedEstablishment)
        .build();


    ProvisionOfServiceRepository.getInstance().create(provisionOfService);

    Map<String, Object> model = new HashMap<>();
    model.put("message", "GRACIAS POR CREAR UNA PROVISIÓN DE SERVICIO");

    context.render("public/appreciate.hbs",model);

  }
}