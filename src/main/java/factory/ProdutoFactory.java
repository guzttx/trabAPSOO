package factory;

import model.*;
import java.time.LocalDate;

/**
 * Factory Method Pattern - Padrão de Criação
 * 
 * Responsável por encapsular a lógica de criação de diferentes tipos de produtos.
 * Esta classe decide qual subclasse de Produto instanciar baseado na categoria informada.
 * 
 * Vantagens:
 * - Desacopla a criação de objetos do código cliente (Main)
 * - Centraliza a lógica de decisão de qual tipo criar
 * - Facilita adicionar novos tipos de produtos sem modificar o código cliente
 * - Aplica o princípio Open/Closed (aberto para extensão, fechado para modificação)
 */
public class ProdutoFactory {
    
    // Construtor privado para prevenir instanciação
    private ProdutoFactory() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    // Constantes para categorias conhecidas
    private static final String CATEGORIA_ELETRONICO = "Eletronico";
    private static final String CATEGORIA_ALIMENTO = "Alimento";
    
    // Valores padrão para atributos específicos
    private static final int GARANTIA_PADRAO_MESES = 12;
    private static final int VALIDADE_PADRAO_DIAS = 30;

    /**
     * Factory Method principal - cria o tipo apropriado de produto baseado na categoria.
     * 
     * @param nome Nome do produto
     * @param codigo Código único do produto
     * @param quantidade Quantidade inicial em estoque
     * @param categoria Categoria do produto (determina o tipo a ser criado)
     * @param fornecedor Fornecedor do produto
     * @return Instância da subclasse apropriada de Produto
     */
    public static Produto criarProduto(String nome, String codigo, int quantidade, 
                                       Categoria categoria, Fornecedor fornecedor) {
        
        String nomeCategoria = categoria.getNome();
        
        // Lógica de decisão: qual tipo de produto criar?
        if (nomeCategoria.equalsIgnoreCase(CATEGORIA_ELETRONICO)) {
            System.out.println("[Factory] Criando produto eletrônico com garantia de " + GARANTIA_PADRAO_MESES + " meses...");
            return new ProdutoEletronico(nome, codigo, quantidade, categoria, fornecedor, GARANTIA_PADRAO_MESES);
            
        } else if (nomeCategoria.equalsIgnoreCase(CATEGORIA_ALIMENTO)) {
            LocalDate dataValidade = LocalDate.now().plusDays(VALIDADE_PADRAO_DIAS);
            System.out.println("[Factory] Criando produto alimentício com validade até " + dataValidade + "...");
            return new ProdutoAlimento(nome, codigo, quantidade, categoria, fornecedor, dataValidade);
            
        } else {
            System.out.println("[Factory] Criando produto genérico...");
            return new ProdutoGenerico(nome, codigo, quantidade, categoria, fornecedor);
        }
    }
    
    /**
     * Sobrecarga do Factory Method para criação de Eletrônicos com garantia customizada.
     * 
     * @param nome Nome do produto
     * @param codigo Código único do produto
     * @param quantidade Quantidade inicial
     * @param categoria Categoria do produto
     * @param fornecedor Fornecedor do produto
     * @param garantiaMeses Período de garantia em meses
     * @return ProdutoEletronico com garantia customizada
     */
    public static Produto criarProdutoEletronico(String nome, String codigo, int quantidade,
                                                 Categoria categoria, Fornecedor fornecedor, 
                                                 int garantiaMeses) {
        System.out.println("[Factory] Criando produto eletrônico com garantia customizada de " + garantiaMeses + " meses...");
        return new ProdutoEletronico(nome, codigo, quantidade, categoria, fornecedor, garantiaMeses);
    }
    
    /**
     * Sobrecarga do Factory Method para criação de Alimentos com validade customizada.
     * 
     * @param nome Nome do produto
     * @param codigo Código único do produto
     * @param quantidade Quantidade inicial
     * @param categoria Categoria do produto
     * @param fornecedor Fornecedor do produto
     * @param dataValidade Data de validade do produto
     * @return ProdutoAlimento com validade customizada
     */
    public static Produto criarProdutoAlimento(String nome, String codigo, int quantidade,
                                               Categoria categoria, Fornecedor fornecedor, 
                                               LocalDate dataValidade) {
        System.out.println("[Factory] Criando produto alimentício com validade customizada até " + dataValidade + "...");
        return new ProdutoAlimento(nome, codigo, quantidade, categoria, fornecedor, dataValidade);
    }
}
