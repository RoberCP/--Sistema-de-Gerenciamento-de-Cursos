import entities.Produto;
import entities.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import jakarta.inject.Named;
import services.ProdutoService;

import java.util.List;

@Named
@RequestScoped
public class ProdutoBean {
    @Inject
    ProdutoService ps;

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Produto buscarProduto(Integer id) {
        return ps.buscarProduto(id);
    }

    public List<Produto> listarProdutos() {
        return ps.listarProdutos();
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        return ps.listarProdutosPorCategoria(categoria);
    }

    public void adicionarProdutoAoCarrinho(Usuario usuario, Integer id) {
        ps.adicionarProdutoAoCarrinho(usuario, id);
    }

    public void removerProdutoAoCarrinho(Usuario usuario, Integer idProduto) {
        ps.removerProdutoDoCarrinho(usuario, idProduto);
    }

    public void calcularTotalCarrinho(Usuario usuario) {
        ps.calcularTotalCarrinho(usuario);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario.getEmail() != null && usuario.getSenha() != null) {
            ps.cadastrarUsuario(usuario);
        }
    }

    public void login(String email, String senha) {
        ps.login(email, senha);
    }
}
