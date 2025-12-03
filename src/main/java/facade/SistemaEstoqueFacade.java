package facade;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import factory.GerenciadorCriacao;
import factory.GerenciadorEletronico;
import factory.GerenciadorAlimento;
import factory.GerenciadorGenerico;
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
        GerenciadorCriacao gerenciador;
        
        String nomeCategoria = categoria.getNome();
        if (nomeCategoria.equalsIgnoreCase("Eletronico")) {
            gerenciador = new GerenciadorEletronico(categoria, fornecedor);
        } else if (nomeCategoria.equalsIgnoreCase("Alimento")) {
            gerenciador = new GerenciadorAlimento(categoria, fornecedor);
        } else {
            gerenciador = new GerenciadorGenerico(categoria, fornecedor);
        }
        
        return gerenciador.criarProduto(nome, codigo, quantidade);
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
