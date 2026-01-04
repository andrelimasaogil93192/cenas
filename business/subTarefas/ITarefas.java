package business.subTarefas;

import java.util.Date;
import java.util.List;

public interface ITarefas {
    String criarTarefa(String descricao, String bancadaId, double tempoEstimadoMin);
    Tarefa obterTarefa(String tarefaId);
    List<Tarefa> listarTarefas();

    boolean checkTarefa(int codTarefa);
    boolean checkStockTarefa(int codTarefa);
    void finalizaTarefa(int codTarefa);
    void registraMetrica(int codTarefa);
    void updateTarefa(Date data);
    void sortTarefas();
    void notificaCliente();
    Date getTimeEntrega(int codProduto);
    void registaErro(int codPedido);
}
