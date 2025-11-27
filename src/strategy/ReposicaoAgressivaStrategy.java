package strategy;

import model.Produto;

/**
 * Política agressiva de reposição:
 * - Quando o estoque está abaixo de um limite, sugere uma reposição maior.
 */
public class ReposicaoAgressivaStrategy implements EstrategiaReposicao {

    private final int limiteMinimo;

    public ReposicaoAgressivaStrategy() {
        this(10); // limite padrão
    }

    public ReposicaoAgressivaStrategy(int limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }

    @Override
    public int calcularQuantidadeReposicao(Produto produto) {
        if (produto.getQuantidade() < limiteMinimo) {
            // exemplo simples: repor o dobro do limite mínimo
            return limiteMinimo * 2;
        }
        return 0;
    }
}
