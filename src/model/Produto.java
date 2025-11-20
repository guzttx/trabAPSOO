package model;

public class Produto {
    private String nome;
    private String codigo;
    private int quantidade;
    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto(String nome, String codigo, int quantidade, Categoria categoria, Fornecedor fornecedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Categoria getCategoria() { return categoria; }
    public Fornecedor getFornecedor() { return fornecedor; }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", quantidade=" + quantidade +
                ", categoria=" + categoria.getNome() +
                ", fornecedor=" + fornecedor.getNome() +
                '}';
    }
}
