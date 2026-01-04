package business.subTarefas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TarefasFacade implements ITarefas {
    private static TarefasFacade instance;

    private final List<Tarefa> tarefas;

    private TarefasFacade() {
        this.tarefas = new ArrayList<>();
    }

    public static TarefasFacade getInstance() {
        if (TarefasFacade.instance == null) {
            TarefasFacade.instance = new TarefasFacade();
        }
        return TarefasFacade.instance;
    }

    public void reset() {
        this.tarefas.clear();
    }

    @Override
    public String criarTarefa(String descricao, String bancadaId, double tempoEstimadoMin) {
        String id = UUID.randomUUID().toString();
        Tarefa tarefa = new Tarefa(id, descricao, bancadaId, tempoEstimadoMin);
        this.tarefas.add(tarefa);
        return id;
    }

    @Override
    public Tarefa obterTarefa(String tarefaId) {
        for (Tarefa tarefa : this.tarefas) {
            if (tarefa.getId().equals(tarefaId)) {
                return tarefa;
            }
        }
        return null;
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(this.tarefas);
    }

    @Override
    public boolean checkTarefa(int codTarefa) {
        return true;
    }

    @Override
    public boolean checkStockTarefa(int codTarefa) {
        return true;
    }

    @Override
    public void finalizaTarefa(int codTarefa) {
    }

    @Override
    public void registraMetrica(int codTarefa) {
    }

    @Override
    public void updateTarefa(Date data) {
    }

    @Override
    public void sortTarefas() {
    }

    @Override
    public void notificaCliente() {
    }

    @Override
    public Date getTimeEntrega(int codProduto) {
        return new Date();
    }

    @Override
    public void registaErro(int codPedido) {
    }
}
