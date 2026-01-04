package business.subPedidos;

import java.util.Objects;
import business.subProdutos.Produto;
import business.subTarefas.Tarefa;

public class PedidoTarefa {
    private final String pedidoId;
    private final Produto produto;
    private final Tarefa tarefa;

    public PedidoTarefa(String pedidoId, Produto produto, Tarefa tarefa) {
        this.pedidoId = pedidoId;
        this.produto = produto;
        this.tarefa = tarefa;
    }

    public String getPedidoId() {
        return this.pedidoId;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Tarefa getTarefa() {
        return this.tarefa;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PedidoTarefa)) {
            return false;
        }
        PedidoTarefa that = (PedidoTarefa) other;
        return Objects.equals(this.pedidoId, that.pedidoId)
            && Objects.equals(this.produto.getId(), that.produto.getId())
            && Objects.equals(this.tarefa.getId(), that.tarefa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pedidoId, this.produto.getId(), this.tarefa.getId());
    }
}
