package service;

import model.Produto;
import observer.AlertaEstoque;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private static Estoque instance;
    private List<Produto> produtos;
    private List<AlertaEstoque> observadores;

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
        System.out.println("Produto não encontrado!");
    }

    public void registrarSaida(String codigo, int qtd) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                p.setQuantidade(p.getQuantidade() - qtd);
                System.out.println("Saída registrada. Quantidade atual: " + p.getQuantidade());
                notificarObservadores(p);
                return;
            }
        }
        System.out.println("Produto não encontrado!");
    }

    public void listarProdutos() {
        System.out.println("=== Produtos no Estoque ===");
        for (Produto p : produtos) {
            System.out.println(p);
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
}
