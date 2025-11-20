package observer;

import model.Produto;

public class AlertaEmail implements AlertaEstoque {
    @Override
    public void atualizar(Produto p) {
        if (p.getQuantidade() < 10) {
            System.out.println("ALERTA: Produto " + p.getNome() + " com estoque baixo! Quantidade atual: " + p.getQuantidade());
        }
    }
}
