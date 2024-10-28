package services;

import entities.Aluno;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlunoService {

    private List<Aluno> alunos = new ArrayList<>();

    // Salva um novo aluno
    public void saveAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // Obter um aluno pelo ID
    public Aluno getAlunoById(Long id) {
        return alunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Obter todos os alunos
    public List<Aluno> getAllAlunos() {
        return new ArrayList<>(alunos);
    }

    // Atualizar um aluno existente
    public void updateAluno(Aluno aluno) {
        Aluno existingAluno = getAlunoById(aluno.getId());
        if (existingAluno != null) {
        }
    }

    // Deletar um aluno
    public void deleteAluno(Long alunoId) {
        alunos.removeIf(a -> a.getId().equals(alunoId));
    }
}
