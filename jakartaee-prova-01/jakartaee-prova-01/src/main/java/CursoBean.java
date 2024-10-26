import entities.Curso;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.CursoService;

import java.util.List;

@Named
@RequestScoped
public class CursoBean {

    @Inject
    private CursoService service;

    public Curso getCursoById(Long id) {
        return service.getCursoById(id);
    }

    public Curso getCursoByName(String name) {
        return service.getCursoByName(name);
    }

    public List<Curso> getAllCursos() {
        return service.getAllCursos();
    }

    public void createCurso(Curso curso) {
        service.createCurso(curso);
    }

    public void updateCurso(Curso curso) {
        service.updateCurso(curso);
    }

    public void deleteCurso(Curso curso) {
        service.deleteCurso(curso.getId());
    }

    public void subscribe(Long alunoId, Long cursoId) {
        service.subscribe(alunoId, cursoId);
    }
}