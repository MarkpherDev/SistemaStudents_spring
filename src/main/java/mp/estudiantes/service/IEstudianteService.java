package mp.estudiantes.service;

import java.util.List;
import mp.estudiantes.model.Estudiante;

public interface IEstudianteService {

  List<Estudiante> listarEstudiante();

  Estudiante buscarEstudiantePorId(Integer idEstudiante);

  void guardarEstudiante(Estudiante estudiante);

  void eliminarEstudiante(Estudiante estudiante);
}
