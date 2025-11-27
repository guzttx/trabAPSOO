package model;

/**
 * Subclasse de Produto para produtos genéricos.
 * Não possui atributos adicionais, mas permite extensibilidade futura.
 */
public class ProdutoGenerico extends Produto {

    public ProdutoGenerico(String nome, String codigo, int quantidade, 
                          Categoria categoria, Fornecedor fornecedor) {
        super(nome, codigo, quantidade, categoria, fornecedor);
    }

    @Override
    public String toString() {
        return "ProdutoGenerico{" +
                "nome='" + getNome() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", categoria=" + getCategoria().getNome() +
                ", fornecedor=" + getFornecedor().getNome() +
                '}';
    }
}
