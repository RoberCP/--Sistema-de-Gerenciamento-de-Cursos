package services;

import entities.Curso;
import jakarta.inject.Inject;
import repositories.CursoRepository;

import java.util.List;

public class CursoService {

    @Inject
    CursoRepository repository;

    public Curso getCursoById(Long id) {
        return repository.getCursoById(id);
    }

    public Curso getCursoByName(String name) {
        return repository.getCursoByName(name);
    }

    public List<Curso> getAllCursos() {
        return repository.getAllCursos();
    }

    public void createCurso(Curso curso) {
        repository.createCurso(curso);
    }

    public void updateCurso(Curso curso) {
        repository.updateCurso(curso);
    }

    public void deleteCurso(Long id) {
        repository.deleteCurso(id);
    }

    public void subscribe(Long alunoId, Long cursoId) {
        repository.subscribe(alunoId, cursoId);
    }
}
