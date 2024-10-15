package repositories;

import entities.Curso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Stateless
public class CursoRepository {

    @PersistenceContext
    public EntityManager em;

    @Serial
    private static final long serialVersionUID = 1L;

    public Curso getCursoById(Long id) {
        return em.find(Curso.class, id);
    }

    public Curso getCursoByName(String nome) {
        String jpql = "SELECT c FROM Curso c WHERE c.nome = :nome";
        TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
        query.setParameter("nome", nome);

        return query.getSingleResult();
    }

    public List<Curso> getAllCursos() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    public void createCurso(Curso curso) {
        em.persist(curso);
    }

    public void updateCurso(Curso curso) {
        em.merge(curso);
    }

    public void deleteCurso(Long id) {
        Curso curso = em.find(Curso.class, id);

        if (curso != null) {
            em.remove(curso);
        }
    }
}
