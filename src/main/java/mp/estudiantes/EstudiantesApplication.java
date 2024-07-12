package mp.estudiantes;

import java.util.List;
import java.util.Scanner;
import mp.estudiantes.model.Estudiante;
import mp.estudiantes.service.EstudianteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {

  @Autowired
  private EstudianteService estudianteService;

  private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

  String nl = System.lineSeparator();

  public static void main(String[] args) {
    logger.info("Iniciando la Aplicacion...");
    SpringApplication.run(EstudiantesApplication.class, args);
    logger.info("Aplicacion Finalizada...");
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("{}Ejecutando metodo run de Spring...{}", nl, nl);
    boolean salir = false;
    Scanner consola = new Scanner(System.in);
    while (!salir) {
      int opcion = mostrarMenu(consola);
      salir = ejecutarOpciones(opcion, consola, estudianteService);
      logger.info(nl);
    }
  }

  private int mostrarMenu(Scanner consola) {
    logger.info("""
        *** Sistema de Estudiantes ***
        1. Listar Estudiantes
        2. Buscar Estudiante
        3. Agregar Estudiante
        4. Modificar Estudiante
        5. Eliminar Estudiante
        6. Salir
        Elige una opcion:\s""");
    return Integer.parseInt(consola.nextLine());
  }

  private boolean ejecutarOpciones(int opcion, Scanner consola,
      EstudianteService estudianteService) {
    boolean salir = false;
    switch (opcion) {
      case 1 -> {
        // Listar Estudiantes
        logger.info("{}Listado de Estudiantes: {}", nl, nl);
        List<Estudiante> estudiantes = estudianteService.listarEstudiante();
        estudiantes.forEach(System.out::println);
      }
      case 2 -> {
        // Buscar estudiante por ID
        logger.info("Introduce el id estudiante a buscar: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudiante = estudianteService.buscarEstudiantePorId(idEstudiante);
        if (estudiante != null) {
          logger.info("Estudiante Encontrado: {}{}", estudiante, nl);
        } else {
          logger.info("Estudiante no Encontrado con id: {}{}", idEstudiante, nl);
        }
      }
      case 3 -> {
        // Agregar estudiante
        logger.info("Agregar Estudiante: {}", nl);
        logger.info("Nombre: ");
        String nombre = consola.nextLine();
        logger.info("Apellido: ");
        String apellido = consola.nextLine();
        logger.info("Telefono: ");
        String telefono = consola.nextLine();
        logger.info("Email: ");
        String email = consola.nextLine();
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setTelefono(telefono);
        estudiante.setEmail(email);
        estudianteService.guardarEstudiante(estudiante);
        logger.info("Estudiante Agregado: {}{}", estudiante, nl);
      }
      case 4 -> {
        // Modificar Estudiante
        logger.info("Modificar Estudiante{}", nl);
        logger.info("Id Estudiante: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudiante = estudianteService.buscarEstudiantePorId(idEstudiante);
        if (estudiante != null) {
          logger.info("Nombre: ");
          String nombre = consola.nextLine();
          logger.info("Apellido: ");
          String apellido = consola.nextLine();
          logger.info("Telefono: ");
          String telefono = consola.nextLine();
          logger.info("Email: ");
          String email = consola.nextLine();
          estudiante.setNombre(nombre);
          estudiante.setApellido(apellido);
          estudiante.setTelefono(telefono);
          estudiante.setEmail(email);
          estudianteService.guardarEstudiante(estudiante);
          logger.info("Estudiante Modificado: {}{}", estudiante, nl);
        } else {
          logger.info("Estudiante no encontrado con id: {}{}", idEstudiante, nl);
        }
      }
      case 5 -> {
        // Eliminar Estudiante
        logger.info("Eliminar Estudiante{}", nl);
        logger.info("Id Estudiante: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudiante = estudianteService.buscarEstudiantePorId(idEstudiante);
        if (estudiante != null) {
          estudianteService.eliminarEstudiante(estudiante);
          logger.info("Estudiante Eliminado: {}{}", estudiante, nl);
        } else {
          logger.info("Estudiante no encontrado con id: {}{}", idEstudiante, nl);
        }
      }
      case 6 -> {
        logger.info("Hasta Pronto!!!{}{}", nl, nl);
        salir = true;
      }
      default -> logger.info("Opcion no reconocida: {}{}", opcion, nl);
    }
    return salir;
  }
}
