package service;

import java.util.ArrayList;
import java.util.List;
import model.Produto;
import observer.AlertaEstoque;
import strategy.EstrategiaReposicao;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class Estoque {
    // private static final Logger logger = LoggerFactory.getLogger(Estoque.class);
    private static Estoque instance;
    private List<Produto> produtos;
    private List<AlertaEstoque> observadores; // listagem de observer (rg pattern)
    private EstrategiaReposicao estrategiaReposicao;

    private Estoque() {
        produtos = new ArrayList<>();
        observadores = new ArrayList<>();
    }
    
    public static Estoque getInstance() {
        if (instance == null) {
            instance = new Estoque();
        }
        return instance;
    }
    
    public void setEstrategiaReposicao(EstrategiaReposicao estrategiaReposicao) {
        this.estrategiaReposicao = estrategiaReposicao;
    }

    public void addProduto(Produto p) {
        produtos.add(p);
        System.out.println("Produto adicionado: " + p.getNome());
    }

    public void registrarEntrada(String codigo, int qtd) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                p.setQuantidade(p.getQuantidade() + qtd);
                System.out.println("Entrada registrada. Quantidade atual: " + p.getQuantidade());
                return;
            }
        }
        System.out.println("Produto não encontrado: " + codigo);
    }

    public void registrarSaida(String codigo, int qtd) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                if (p.getQuantidade() < qtd) {
                    System.out.println("Quantidade insuficiente para saída. Disponível: " + p.getQuantidade() + ", Solicitado: " + qtd);
                    return;
                }
                p.setQuantidade(p.getQuantidade() - qtd);
                System.out.println("Saída registrada. Quantidade atual: " + p.getQuantidade());
                notificarObservadores(p);
                sugerirReposicaoSeNecessario(p);
                return;
            }
        }
        System.out.println("Produto não encontrado: " + codigo);
    }

    public void listarProdutos() {
        System.out.println("=== Produtos no Estoque ===");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    public void addObservador(AlertaEstoque obs) { // attach do observer (rg pattern)
        observadores.add(obs);
    }

    private void notificarObservadores(Produto p) { //notify do observer (rg pattern de novo)
        for (AlertaEstoque obs : observadores) {
            obs.atualizar(p);
        }
    }
    
    private void sugerirReposicaoSeNecessario(Produto p) {
        if (estrategiaReposicao == null) {
            return;
        }

        int quantidadeSugerida = estrategiaReposicao.calcularQuantidadeReposicao(p);
        if (quantidadeSugerida > 0) {
            System.out.println("[Strategy] Sugestão de reposição para o produto " + p.getNome() + ": repor " + quantidadeSugerida + " unidades.");
        }
    }

}
