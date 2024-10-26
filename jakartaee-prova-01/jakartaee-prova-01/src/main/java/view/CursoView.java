package view;

import entities.Curso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.CursoService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CursoView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CursoService service;

    private List<Curso> cursos;

    @PostConstruct
    public void init() {
        this.cursos = this.service.getAllCursos();
    }

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
