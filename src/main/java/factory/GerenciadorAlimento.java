package factory;

import java.time.LocalDate;
import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.ProdutoAlimento;

public class GerenciadorAlimento extends GerenciadorCriacao {
    
    private static final int VALIDADE_PADRAO_DIAS = 30;
    private Categoria categoria;
    private Fornecedor fornecedor;
    
    public GerenciadorAlimento(Categoria categoria, Fornecedor fornecedor) {
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
    
    @Override
    public Produto criarProduto(String nome, String codigo, int quantidade) {
        LocalDate dataValidade = LocalDate.now().plusDays(VALIDADE_PADRAO_DIAS);
        return new ProdutoAlimento(nome, codigo, quantidade, categoria, fornecedor, dataValidade);
    }
}
