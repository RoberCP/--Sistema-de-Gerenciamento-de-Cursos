package services;

import entities.Carrinho;
import entities.Produto;
import entities.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ProdutoService {
    @PersistenceContext(unitName = "jakartaee-starter")
    EntityManager em;

    public Produto buscarProduto(Integer id) {
        String jpql = "SELECT p FROM Produto p WHERE p.id = :id";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Produto> listarProdutos() {
        String jpql = "SELECT p FROM Produto p";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        return query.getResultList();
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria = :categoria";
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }

    public void adicionarProdutoAoCarrinho(Usuario usuario, Integer idProduto) {
        Produto produto = buscarProduto(idProduto);

        if (produto == null) return;

        String jpql = "SELECT c FROM Carrinho c WHERE c.usuario = :idUsuario";
        TypedQuery<Carrinho> query = em.createQuery(jpql, Carrinho.class);
        query.setParameter("idUsuario", usuario.getId());
        Carrinho c = query.getSingleResult();
        c.setProdutos(produto);
        em.merge(c);
    }

    public void removerProdutoDoCarrinho(Usuario usuario, Integer idProduto) {
        Produto produto = buscarProduto(idProduto);

        String jpql = "SELECT c  FROM Carrinho c WHERE c.usuario = :idUsuario";
        TypedQuery<Carrinho> query = em.createQuery(jpql, Carrinho.class);
        query.setParameter("idUsuario", usuario.getId());

        Carrinho c = query.getSingleResult();
        c.removerProduto(produto);
        em.merge(c);
    }

    public Double calcularTotalCarrinho(Usuario usuario) {
        String jpql = "SELECT c FROM Carrinho c WHERE c.usuario = :idUsuario";
        TypedQuery<Carrinho> query = em.createQuery(jpql, Carrinho.class);
        query.setParameter("idUsuario", usuario.getId());

        Carrinho c = query.getSingleResult();
        c.calcularTotal();
        em.merge(c);

        return c.getTotal();
    }

    public void cadastrarUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario login(String email, String senha) {
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);

        return query.getSingleResult();
    }

}
