package strategy;

import model.Produto;

/**
 * Strategy - define o contrato para diferentes políticas de reposição de estoque.
 */
public interface EstrategiaReposicao {

    /**
     * Calcula a quantidade sugerida para reposição do produto.
     *
     * @param produto Produto analisado
     * @return quantidade sugerida para repor (0 se nenhuma reposição for necessária)
     */
    int calcularQuantidadeReposicao(Produto produto);
}
