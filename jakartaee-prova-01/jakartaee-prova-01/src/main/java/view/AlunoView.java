package view;

import entities.Aluno;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.AlunoService;

import java.util.List;

@Named
@RequestScoped
public class AlunoView {

    private Aluno aluno;
    private List<Aluno> alunos;

    @Inject
    private AlunoService alunoService;

    public AlunoView() {
        aluno = new Aluno();
        alunos = alunoService.getAllAlunos(); // Carrega todos os alunos ao inicializar
    }

    public void createAluno(Aluno aluno) {
        alunoService.saveAluno(aluno);
        this.aluno = new Aluno(); // Reseta o aluno após a criação
        alunos = alunoService.getAllAlunos(); // Atualiza a lista de alunos
    }

    public void deleteAluno(Aluno aluno) {
        alunoService.deleteAluno(aluno.getId());
        alunos = alunoService.getAllAlunos(); // Atualiza a lista de alunos
    }

    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAllAlunos() {
        return alunos;
    }
}

