import entities.Aluno;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.AlunoService;

import java.util.List;

@Named
@RequestScoped
public class AlunoBean {

    @Inject
    private AlunoService service;

    public Aluno getAlunoById(Long id) {
        return service.getAlunoById(id);
    }

    public List<Aluno> getAllAlunos() {
        return service.getAllAlunos();
    }

    public void createAluno(Aluno aluno) {
        service.saveAluno(aluno);
    }

    public void updateAluno(Aluno aluno) {
        service.updateAluno(aluno);
    }

    public void deleteAluno(Long alunoId) {
        service.deleteAluno(alunoId);
    }
}
