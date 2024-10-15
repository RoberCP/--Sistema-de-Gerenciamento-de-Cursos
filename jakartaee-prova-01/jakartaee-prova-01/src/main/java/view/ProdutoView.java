package view;

import entities.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import entities.Produto;
import services.ProdutoService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ProdutoView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProdutoService produtoService;

    private List<Produto> produtos;
    private List<Produto> produtosFiltrados;
    private Produto selectedProduto;
    private Usuario usuario = new Usuario();

    private String filtro;

    @PostConstruct
    public void init() {
        this.produtos = produtoService.listarProdutos();
        this.produtosFiltrados = new ArrayList<>(produtos);
    }

    /*public void filtrarProdutos() {
        produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(filtro.toLowerCase()) ||
                        produto.getDescricao().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }*/

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public void login() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/produtos.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarProdutoAoCarrinho() {
        if (selectedProduto != null && usuario.getEmail() != null) {
            produtoService.adicionarProdutoAoCarrinho(usuario, selectedProduto.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto adicionado ao carrinho"));
        }
    }

    public void removerProdutoDoCarrinho(Integer idProduto) {
        if (usuario.getEmail() != null) {
            produtoService.removerProdutoDoCarrinho(usuario, idProduto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto removido do carrinho"));
        }
    }

    public void calcularTotalCarrinho() {
        if (usuario.getEmail() != null) {
            Double total = produtoService.calcularTotalCarrinho(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Total do carrinho: " + total));
        }
    }

    public void cadastrarUsuario() {
        if (usuario.getNome() != null && usuario.getEmail() != null && usuario.getSenha() != null) {
            produtoService.cadastrarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", null));

            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar. Verifique os dados.", null));
        }
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        usuario.setEmail(email);
    }

    public String getSenha() {
        return usuario.getSenha();
    }

    public void setSenha(String senha) {
        usuario.setSenha(senha);
    }

    public Produto buscarProduto(Integer id) {
        return produtoService.buscarProduto(id);
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        return produtoService.listarProdutosPorCategoria(categoria);
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getFiltro() { return filtro; }
}
