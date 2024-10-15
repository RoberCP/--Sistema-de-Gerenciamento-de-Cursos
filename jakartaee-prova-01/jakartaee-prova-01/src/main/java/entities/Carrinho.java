package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Produto> produtos;
    private Double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produto) {
        produtos.add(produto);
        calcularTotal();
    }

    public Double getTotal() {
        return total;
    }

    public void calcularTotal() {
        total = produtos.stream().mapToDouble(Produto::getPreco).sum();
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        calcularTotal();
    }
}
