package main;

import java.util.Scanner;
import model.Produto;
import model.Categoria;
import model.Fornecedor;
import service.Estoque;
import observer.AlertaEmail;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = Estoque.getInstance();
        estoque.addObservador(new AlertaEmail());
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1 - Adicionar produto");
            System.out.println("2 - Registrar entrada");
            System.out.println("3 - Registrar saída");
            System.out.println("4 - Listar produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir enter

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Código do produto: ");
                    String codigo = sc.nextLine();
                    System.out.print("Quantidade inicial: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome da categoria: ");
                    String cat = sc.nextLine();
                    System.out.print("Nome do fornecedor: ");
                    String forn = sc.nextLine();
                    Produto p = new Produto(nome, codigo, qtd, new Categoria(cat), new Fornecedor(forn));
                    estoque.addProduto(p);
                    break;
                case 2:
                    System.out.print("Código do produto: ");
                    String codEntrada = sc.nextLine();
                    System.out.print("Quantidade a adicionar: ");
                    int qEntrada = sc.nextInt();
                    sc.nextLine();
                    estoque.registrarEntrada(codEntrada, qEntrada);
                    break;
                case 3:
                    System.out.print("Código do produto: ");
                    String codSaida = sc.nextLine();
                    System.out.print("Quantidade a retirar: ");
                    int qSaida = sc.nextInt();
                    sc.nextLine();
                    estoque.registrarSaida(codSaida, qSaida);
                    break;
                case 4:
                    estoque.listarProdutos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
