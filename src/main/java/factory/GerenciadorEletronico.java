package factory;

import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.ProdutoEletronico;

public class GerenciadorEletronico extends GerenciadorCriacao {
    
    private static final int GARANTIA_PADRAO_MESES = 12;
    private Categoria categoria;
    private Fornecedor fornecedor;
    
    public GerenciadorEletronico(Categoria categoria, Fornecedor fornecedor) {
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
    
    @Override
    public Produto criarProduto(String nome, String codigo, int quantidade) {
        return new ProdutoEletronico(nome, codigo, quantidade, categoria, fornecedor, GARANTIA_PADRAO_MESES);
    }
}
