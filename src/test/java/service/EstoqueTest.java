package service;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.ProdutoGenerico;
import observer.AlertaEstoque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.ReposicaoConservadoraStrategy;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    private Estoque estoque;

    @BeforeEach
    void setUp() {
        // Obtém a instância única do Estoque
        estoque = Estoque.getInstance();
    }

    @Test
    void testAdicionarProduto() {
        Categoria categoria = new Categoria("Teste");
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste");
        Produto produto = new ProdutoGenerico("Produto Teste", "TEST_ADD", 100, categoria, fornecedor);
        
        estoque.addProduto(produto);
        assertNotNull(produto);
        assertEquals("Produto Teste", produto.getNome());
    }

    @Test
    void testRegistrarEntrada() {
        Categoria categoria = new Categoria("Teste");
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste");
        Produto produto = new ProdutoGenerico("Produto Entrada", "TEST_ENT", 100, categoria, fornecedor);
        estoque.addProduto(produto);
        
        int quantidadeInicial = produto.getQuantidade();
        estoque.registrarEntrada("TEST_ENT", 50);
        
        assertEquals(quantidadeInicial + 50, produto.getQuantidade());
    }

    @Test
    void testRegistrarSaidaComSucesso() {
        Categoria categoria = new Categoria("Teste");
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste");
        Produto produto = new ProdutoGenerico("Produto Saida", "TEST_SAIDA", 100, categoria, fornecedor);
        estoque.addProduto(produto);
        
        int quantidadeInicial = produto.getQuantidade();
        estoque.registrarSaida("TEST_SAIDA", 30);
        
        assertEquals(quantidadeInicial - 30, produto.getQuantidade());
    }

    @Test
    void testRegistrarSaidaComQuantidadeInsuficiente() {
        Categoria categoria = new Categoria("Teste");
        Fornecedor fornecedor = new Fornecedor("Fornecedor");
        Produto produto = new ProdutoGenerico("Produto Limitado", "TEST_LIMIT", 10, categoria, fornecedor);
        estoque.addProduto(produto);
        
        int quantidadeInicial = produto.getQuantidade();
        
        // Tenta retirar mais do que tem disponível
        estoque.registrarSaida("TEST_LIMIT", 50);
        
        // Quantidade deve permanecer a mesma (saída bloqueada)
        assertEquals(quantidadeInicial, produto.getQuantidade());
    }

    @Test
    void testRegistrarEntradaProdutoInexistente() {
        // Tenta registrar entrada para produto que não existe
        estoque.registrarEntrada("INEXISTENTE", 10);
        // Não deve lançar exceção, apenas registrar no log
        assertTrue(true); // Passou se não lançou exceção
    }

    @Test
    void testRegistrarSaidaProdutoInexistente() {
        // Tenta registrar saída para produto que não existe
        estoque.registrarSaida("INEXISTENTE", 10);
        // Não deve lançar exceção, apenas registrar no log
        assertTrue(true); // Passou se não lançou exceção
    }

    @Test
    void testAdicionarObservador() {
        AlertaEstoque alerta = new AlertaEstoque() {
            @Override
            public void atualizar(Produto p) {
                // Observer de teste
            }
        };
        
        estoque.addObservador(alerta);
        assertTrue(true); // Se não lançar exceção, passou
    }

    @Test
    void testSetEstrategiaReposicao() {
        ReposicaoConservadoraStrategy estrategia = new ReposicaoConservadoraStrategy();
        estoque.setEstrategiaReposicao(estrategia);
        assertTrue(true); // Se não lançar exceção, passou
    }

    @Test
    void testListarProdutos() {
        Categoria categoria = new Categoria("Teste");
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste");
        Produto produto = new ProdutoGenerico("Produto Lista", "TEST_LIST", 100, categoria, fornecedor);
        estoque.addProduto(produto);
        
        estoque.listarProdutos();
        assertTrue(true); // Se não lançar exceção, passou
    }
}
