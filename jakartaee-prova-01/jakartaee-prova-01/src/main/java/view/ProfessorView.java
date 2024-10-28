package view;

import entities.Professor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.ProfessorService;

import java.util.List;

@Named
@RequestScoped
public class ProfessorView {

    private Professor professor;
    private List<Professor> allProfessores;

    @Inject
    private ProfessorService professorService;

    public ProfessorView() {
        professor = new Professor();
        allProfessores = professorService.getAllProfessores(); // Carrega todos os professores ao inicializar
    }

    public void createProfessor(Professor professor) {
        professorService.saveProfessor(professor);
        this.professor = new Professor(); // Reseta o professor após a criação
        allProfessores = professorService.getAllProfessores(); // Atualiza a lista de professores
    }

    public void deleteProfessor(Professor professor) {
        professorService.deleteProfessor(professor.getId());
        allProfessores = professorService.getAllProfessores(); // Atualiza a lista de professores
    }

    // Getters e Setters
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getAllProfessores() {
        return allProfessores;
    }
}
