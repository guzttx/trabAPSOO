package observer;

import model.Produto;

public interface AlertaEstoque { // interface do observer (rg pattern)
    void atualizar(Produto p);
}
