package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclasse de Produto específica para alimentos.
 * Adiciona atributo de data de validade.
 */
public class ProdutoAlimento extends Produto {
    private LocalDate dataValidade;

    public ProdutoAlimento(String nome, String codigo, int quantidade, 
                           Categoria categoria, Fornecedor fornecedor, LocalDate dataValidade) {
        super(nome, codigo, quantidade, categoria, fornecedor);
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(dataValidade);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String status = estaVencido() ? " [VENCIDO]" : "";
        return "ProdutoAlimento{" +
                "nome='" + getNome() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", quantidade=" + getQuantidade() +
                ", categoria=" + getCategoria().getNome() +
                ", fornecedor=" + getFornecedor().getNome() +
                ", validade=" + dataValidade.format(formatter) + status +
                '}';
    }
}
