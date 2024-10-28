package services;

import entities.Professor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProfessorService {

    private List<Professor> professores = new ArrayList<>();

    // Salva um novo professor
    public void saveProfessor(Professor professor) {
        professores.add(professor);
    }

    // Busca professor pelo ID
    public Professor getProfessorById(Long id) {
        return professores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Obter todos os professores
    public List<Professor> getAllProfessores() {
        return new ArrayList<>(professores);
    }

    // Atualiza um professor existente
    public void updateProfessor(Professor professor) {
        Professor existingProfessor = getProfessorById(professor.getId());
        if (existingProfessor != null) {
            // Aqui você deve implementar a lógica para atualizar o professor
            // Por exemplo, você pode copiar os campos de `professor` para `existingProfessor`
        }
    }

    // Deleta um professor
    public void deleteProfessor(Long professorId) {
        professores.removeIf(p -> p.getId().equals(professorId));
    }
}
