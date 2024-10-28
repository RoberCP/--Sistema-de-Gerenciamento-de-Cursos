import entities.Professor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.ProfessorService;

import java.util.List;

@Named
@RequestScoped
public class ProfessorBean {

    @Inject
    private ProfessorService service;

    public Professor getProfessorById(Long id) {
        return service.getProfessorById(id);
    }

    public List<Professor> getAllProfessores() {
        return service.getAllProfessores();
    }

    public void createProfessor(Professor professor) {
        service.saveProfessor(professor);
    }

    public void updateProfessor(Professor professor) {
        service.updateProfessor(professor);
    }

    public void deleteProfessor(Long professorId) {
        service.deleteProfessor(professorId);
    }
}
