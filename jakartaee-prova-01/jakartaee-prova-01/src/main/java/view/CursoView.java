package view;

import entities.Curso;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.CursoService;

import java.util.List;

@Named
@RequestScoped
public class CursoView {
    private Curso curso;
    private List<Curso> allCursos;

    @Inject
    private CursoService cursoService;

    public CursoView() {
        curso = new Curso();
        allCursos = cursoService.getAllCursos(); // Carrega todos os cursos ao inicializar
    }

    public void createCurso(Curso curso) {
        cursoService.saveCurso(curso);
        this.curso = new Curso();
        allCursos = cursoService.getAllCursos(); // Atualiza a lista de cursos
    }

    public void deleteCurso(Curso curso) {
        cursoService.deleteCurso(curso.getId());
        allCursos = cursoService.getAllCursos(); // Atualiza a lista de cursos
    }

    // Getters e Setters
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getAllCursos() {
        return allCursos;
    }
}
