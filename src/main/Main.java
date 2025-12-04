package main;

import java.util.Scanner;
import model.Categoria;
import model.Fornecedor;
import factory.GerenciadorEstoque;
import factory.GerenciadorEletronico;
import factory.GerenciadorAlimento;
import factory.GerenciadorGenerico;

/**
 * CÓDIGO CLIENTE - Demonstra o padrão Factory Method
 * 
 * PRINCÍPIO CHAVE DO FACTORY METHOD:
 * O código cliente trabalha com o Creator abstrato (GerenciadorEstoque) sem conhecer
 * as classes concretas de produtos (ProdutoEletronico, ProdutoAlimento, etc.)
 * 
 * A DECISÃO de qual Creator usar é baseada em configuração/contexto (neste caso, categoria).
 * Uma vez escolhido o Creator, TODO o código trabalha através da interface abstrata.
 * 
 * Analogia Refactoring Guru: Como "Demo.java" que escolhe WindowsDialog ou HtmlDialog
 * baseado no OS, mas depois trabalha apenas com Dialog abstrato.
 */
public class Main {
    
    private static GerenciadorEstoque gerenciador;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║   SISTEMA DE GERENCIAMENTO DE     ║");
            System.out.println("║   ESTOQUE - FACTORY METHOD        ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Registrar entrada");
            System.out.println("3 - Registrar saída");
            System.out.println("4 - Listar produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir enter

            switch (opcao) {
                case 1:
                    adicionarProduto(sc);
                    break;
                case 2:
                    registrarEntrada(sc);
                    break;
                case 3:
                    registrarSaida(sc);
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 0:
                    System.out.println("\n✓ Encerrando sistema... Até logo!");
                    break;
                default:
                    System.out.println("✗ Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
    
    /**
     * MÉTODO QUE DEMONSTRA O FACTORY METHOD PATTERN
     * 
     * PASSO 1: Configurar - Escolher qual Creator instanciar baseado na categoria
     * PASSO 2: Trabalhar - Usar apenas a interface abstrata (GerenciadorEstoque)
     * 
     * Note que após a configuração, NÃO sabemos nem nos importamos com o tipo concreto!
     */
    private static void adicionarProduto(Scanner sc) {
        System.out.println("\n=== ADICIONAR PRODUTO ===");
        
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        
        System.out.print("Código do produto: ");
        String codigo = sc.nextLine();
        
        System.out.print("Quantidade inicial: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        
        System.out.println("\nCategorias disponíveis:");
        System.out.println("  1 - Eletronico (com garantia)");
        System.out.println("  2 - Alimento (com validade)");
        System.out.println("  3 - Generico (outros produtos)");
        System.out.print("Escolha a categoria: ");
        int tipoCategoria = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Nome do fornecedor: ");
        String forn = sc.nextLine();
        
        // ===================================================================
        // FACTORY METHOD PATTERN EM AÇÃO!
        // ===================================================================
        // PASSO 1: CONFIGURAR - Escolher o Creator concreto baseado no contexto
        // Esta é a ÚNICA parte que conhece as classes concretas de Creators
        // ===================================================================
        String nomeCategoria = configurarGerenciador(tipoCategoria);
        
        if (gerenciador == null) {
            System.out.println("✗ Categoria inválida!");
            return;
        }
        
        // ===================================================================
        // PASSO 2: TRABALHAR - Usar apenas a abstração
        // A partir daqui, o código NÃO sabe qual tipo concreto de gerenciador está usando!
        // Ele apenas chama adicionarProduto() e o polimorfismo faz a mágica acontecer:
        //   - Se for GerenciadorEletronico → criarProduto() retorna ProdutoEletronico
        //   - Se for GerenciadorAlimento → criarProduto() retorna ProdutoAlimento
        //   - Se for GerenciadorGenerico → criarProduto() retorna ProdutoGenerico
        // ===================================================================
        gerenciador.adicionarProduto(nome, codigo, qtd, 
                                    new Categoria(nomeCategoria), 
                                    new Fornecedor(forn));
        
        System.out.println("\n✓ Produto adicionado ao estoque com sucesso!");
    }
    
    /**
     * MÉTODO DE CONFIGURAÇÃO - escolhe qual Creator instanciar
     * 
     * Este método encapsula a lógica de decisão.
     * Em uma aplicação real, isso poderia vir de:
     * - Arquivo de configuração
     * - Variável de ambiente
     * - Banco de dados
     * - Preferências do usuário
     * 
     * Analogia Refactoring Guru: Como o método configure() no Demo.java
     * que escolhe WindowsDialog ou HtmlDialog baseado no OS
     */
    private static String configurarGerenciador(int tipo) {
        switch (tipo) {
            case 1:
                gerenciador = new GerenciadorEletronico();
                return "Eletronico";
            case 2:
                gerenciador = new GerenciadorAlimento();
                return "Alimento";
            case 3:
                gerenciador = new GerenciadorGenerico();
                return "Generico";
            default:
                gerenciador = null;
                return null;
        }
    }
    
    private static void registrarEntrada(Scanner sc) {
        if (gerenciador == null || gerenciador.getProdutos().isEmpty()) {
            System.out.println("✗ Nenhum produto cadastrado!");
            return;
        }
        
        System.out.println("\n=== REGISTRAR ENTRADA ===");
        System.out.print("Código do produto: ");
        String codEntrada = sc.nextLine();
        System.out.print("Quantidade a adicionar: ");
        int qEntrada = sc.nextInt();
        sc.nextLine();
        
        gerenciador.registrarEntrada(codEntrada, qEntrada);
    }
    
    private static void registrarSaida(Scanner sc) {
        if (gerenciador == null || gerenciador.getProdutos().isEmpty()) {
            System.out.println("✗ Nenhum produto cadastrado!");
            return;
        }
        
        System.out.println("\n=== REGISTRAR SAÍDA ===");
        System.out.print("Código do produto: ");
        String codSaida = sc.nextLine();
        System.out.print("Quantidade a retirar: ");
        int qSaida = sc.nextInt();
        sc.nextLine();
        
        gerenciador.registrarSaida(codSaida, qSaida);
    }
    
    private static void listarProdutos() {
        if (gerenciador == null || gerenciador.getProdutos().isEmpty()) {
            System.out.println("\n✗ Nenhum produto cadastrado!");
            return;
        }
        
        gerenciador.listarProdutos();
    }
}
