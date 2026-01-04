package business.subBancadas;

import java.util.List;
import business.subPedidos.PedidoTarefa;
import business.subProdutos.Produto;

public interface IBancadas {
    String registarBancada(String nome, String local);
    Bancada obterBancada(String bancadaId);
    List<Bancada> listarBancadas();

    void adicionarStock(String bancadaId, Produto produto, int quantidade);
    void adicionarTarefa(String bancadaId, PedidoTarefa tarefa);
    void adicionarTarefas(String bancadaId, List<PedidoTarefa> tarefas);
    List<PedidoTarefa> listarTarefasPendentes(String bancadaId);
    boolean executarProximaTarefa(String bancadaId);
}
