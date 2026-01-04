package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subTarefas.Tarefa;

public class TarefaDAO {
    private final Map<String, Tarefa> tarefas;

    public TarefaDAO() {
        this.tarefas = new HashMap<>();
    }

    public void put(Tarefa tarefa) {
        this.tarefas.put(tarefa.getId(), tarefa);
    }

    public Tarefa get(String id) {
        return this.tarefas.get(id);
    }

    public List<Tarefa> list() {
        return new ArrayList<>(this.tarefas.values());
    }
}
