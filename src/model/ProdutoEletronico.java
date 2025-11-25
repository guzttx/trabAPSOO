package model;

/**
 * Subclasse de Produto específica para produtos eletrônicos.
 * Adiciona atributo de garantia em meses.
 */
public class ProdutoEletronico extends Produto {
    private int garantiaMeses;

    public ProdutoEletronico(String nome, String codigo, int quantidade, 
                             Categoria categoria, Fornecedor fornecedor, int garantiaMeses) {
        super(nome, codigo, quantidade, categoria, fornecedor);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String toString() {
        return "ProdutoEletronico{" +
                "nome='" + getNome() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", categoria=" + getCategoria().getNome() +
                ", fornecedor=" + getFornecedor().getNome() +
                ", garantia=" + garantiaMeses + " meses" +
                '}';
    }
}
