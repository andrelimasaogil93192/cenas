package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subPedidos.PedidoTarefa;

public class PedidoTarefaDAO {
    private final Map<String, PedidoTarefa> tarefas;

    public PedidoTarefaDAO() {
        this.tarefas = new HashMap<>();
    }

    public void put(PedidoTarefa tarefa) {
        String id = tarefa.getPedidoId() + "|" + tarefa.getProduto().getId() + "|" + tarefa.getTarefa().getId();
        this.tarefas.put(id, tarefa);
    }

    public PedidoTarefa get(String id) {
        return this.tarefas.get(id);
    }

    public List<PedidoTarefa> list() {
        return new ArrayList<>(this.tarefas.values());
    }
}
