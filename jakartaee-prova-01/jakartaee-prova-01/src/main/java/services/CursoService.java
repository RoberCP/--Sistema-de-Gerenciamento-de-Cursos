package services;

import entities.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import repositories.CursoRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CursoService {

        private List<Curso> cursos = new ArrayList<>();

        public CursoService() {
        }

        //Salvar curso
        public void saveCurso(Curso curso) {
        cursos.add(curso); // Aqui você deve implementar a lógica de persistência no banco de dados
    }

        //Buscar curso pelo ID
        public Curso getCursoById(Long id) {
            return cursos.stream()
                    .filter(curso -> curso.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        //Buscar curso pelo nome
        public Curso getCursoByName(String name) {
            return cursos.stream()
                    .filter(curso -> curso.getNome().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
        }

        //Lista todos os cursos
        public List<Curso> getAllCursos() {
            return cursos;
        }

        //Adiciona um novo curso
        public void createCurso(Curso curso) {
            cursos.add(curso);
        }

        //Atualiza um curso existente
        public void updateCurso(Curso curso) {
            for (int i = 0; i < cursos.size(); i++) {
                if (cursos.get(i).getId().equals(curso.getId())) {
                    cursos.set(i, curso);
                    return;
                }
            }
        }

        //Deleta um curso
        public void deleteCurso(Long id) {
            cursos.removeIf(curso -> curso.getId().equals(id));
        }

        //Inscreve um aluno em um curso
        public void subscribe(Long alunoId, Long cursoId) {
        }
    }