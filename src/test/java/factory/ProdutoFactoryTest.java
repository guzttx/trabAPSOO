package factory;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoFactoryTest {

    @Test
    void testCriarProduto() {
        Categoria categoria = new Categoria("Eletrônicos");
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste");
        
        Produto produto = ProdutoFactory.criarProduto(
            "Notebook", 
            "NB001", 
            10, 
            categoria, 
            fornecedor
        );
        
        assertNotNull(produto);
        assertEquals("Notebook", produto.getNome());
        assertEquals("NB001", produto.getCodigo());
        assertEquals(10, produto.getQuantidade());
        assertEquals(categoria, produto.getCategoria());
        assertEquals(fornecedor, produto.getFornecedor());
    }

    @Test
    void testCriarProdutoComDiferentesParametros() {
        Categoria categoria = new Categoria("Alimentos");
        Fornecedor fornecedor = new Fornecedor("Fornecedor XYZ");
        
        Produto produto = ProdutoFactory.criarProduto(
            "Arroz", 
            "ARR001", 
            50, 
            categoria, 
            fornecedor
        );
        
        assertNotNull(produto);
        assertEquals("Arroz", produto.getNome());
        assertEquals(50, produto.getQuantidade());
    }

    @Test
    void testConstrutorPrivado() throws Exception {
        // Testa que não é possível instanciar a factory
        // (não há construtor público acessível)
        var constructor = ProdutoFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        Exception exception = assertThrows(Exception.class, () -> {
            constructor.newInstance();
        });
        
        // Verifica que a exceção é do tipo esperado (UnsupportedOperationException)
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
