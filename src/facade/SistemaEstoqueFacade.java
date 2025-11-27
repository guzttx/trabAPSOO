package facade;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import factory.ProdutoFactory;
import observer.AlertaEstoque;
import service.Estoque;

public class SistemaEstoqueFacade {

    private Estoque estoque;

    public SistemaEstoqueFacade() {
        this.estoque = Estoque.getInstance();
    }

    public Categoria criarCategoria(String nome) {
        return new Categoria(nome);
    }

    public Fornecedor criarFornecedor(String nome) {
        return new Fornecedor(nome);
    }

    public Produto criarProduto(String nome, String codigo, int quantidade, Categoria categoria, Fornecedor fornecedor) {
        return ProdutoFactory.criarProduto(nome, codigo, quantidade, categoria, fornecedor);
    }

    public void adicionarProduto(Produto p) {
        estoque.addProduto(p);
    }

    public void registrarEntrada(String codigo, int qtd) {
        estoque.registrarEntrada(codigo, qtd);
    }

    public void registrarSaida(String codigo, int qtd) {
        estoque.registrarSaida(codigo, qtd);
    }

    public void listarProdutos() {
        estoque.listarProdutos();
    }

    public void registrarAlerta(AlertaEstoque alerta) {
        estoque.addObservador(alerta);
    }
}
