package strategy;

import model.Produto;

/**
 * Política conservadora de reposição:
 * - Só sugere reposição quando a quantidade estiver abaixo de um limite mínimo;
 * - Reposição em lotes pequenos.
 */
public class ReposicaoConservadoraStrategy implements EstrategiaReposicao {

    private final int limiteMinimo;
    private final int loteReposicao;

    public ReposicaoConservadoraStrategy() {
        this(5, 10); // valores padrão
    }

    public ReposicaoConservadoraStrategy(int limiteMinimo, int loteReposicao) {
        this.limiteMinimo = limiteMinimo;
        this.loteReposicao = loteReposicao;
    }

    @Override
    public int calcularQuantidadeReposicao(Produto produto) {
        if (produto.getQuantidade() < limiteMinimo) {
            return loteReposicao;
        }
        return 0;
    }
}
