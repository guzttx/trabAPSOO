package service;

import java.util.ArrayList;
import java.util.List;
import model.Produto;
import observer.AlertaEstoque;
import strategy.EstrategiaReposicao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Estoque {
    private static final Logger logger = LoggerFactory.getLogger(Estoque.class);
    private static Estoque instance;
    private List<Produto> produtos;
    private List<AlertaEstoque> observadores;
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
        logger.info("Produto adicionado: {}", p.getNome());
    }

    public void registrarEntrada(String codigo, int qtd) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                p.setQuantidade(p.getQuantidade() + qtd);
                logger.info("Entrada registrada. Quantidade atual: {}", p.getQuantidade());
                return;
            }
        }
        logger.warn("Produto não encontrado: {}", codigo);
    }

    public void registrarSaida(String codigo, int qtd) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                p.setQuantidade(p.getQuantidade() - qtd);
                logger.info("Saída registrada. Quantidade atual: {}", p.getQuantidade());
                notificarObservadores(p);
                sugerirReposicaoSeNecessario(p); //aqui entra o Strategy
                return;
            }
        }
        logger.warn("Produto não encontrado: {}", codigo);
    }

    public void listarProdutos() {
        logger.info("=== Produtos no Estoque ===");
        for (Produto p : produtos) {
            logger.info("{}", p);
        }
    }

    public void addObservador(AlertaEstoque obs) {
        observadores.add(obs);
    }

    private void notificarObservadores(Produto p) {
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
            logger.info("[Strategy] Sugestão de reposição para o produto {}: repor {} unidades.",
                    p.getNome(), quantidadeSugerida);
        }
    }

}
