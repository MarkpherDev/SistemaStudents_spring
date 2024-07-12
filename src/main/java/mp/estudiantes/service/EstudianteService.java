package mp.estudiantes.service;

import java.util.List;
import mp.estudiantes.model.Estudiante;
import mp.estudiantes.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {

  @Autowired
  private EstudianteRepository estudianteRepository;

  @Override
  public List<Estudiante> listarEstudiante() {
    return estudianteRepository.findAll();
  }

  @Override
  public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
    return estudianteRepository.findById(idEstudiante).orElse(null);
  }

  @Override
  public void guardarEstudiante(Estudiante estudiante) {
    estudianteRepository.save(estudiante);
  }

  @Override
  public void eliminarEstudiante(Estudiante estudiante) {
    estudianteRepository.delete(estudiante);
  }
}
