package business.subBancadas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subPedidos.PedidoTarefa;
import business.subProdutos.Produto;

public class Bancada {
    private final String id;
    private final String nome;
    private final String local;
    private final Map<Produto, Integer> stock;
    private final List<PedidoTarefa> tarefasPendentes;

    public Bancada(String id, String nome, String local) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.stock = new HashMap<>();
        this.tarefasPendentes = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getLocal() {
        return this.local;
    }

    public int getStock(Produto produto) {
        return this.stock.getOrDefault(produto, 0);
    }

    public void adicionarStock(Produto produto, int quantidade) {
        int atual = this.stock.getOrDefault(produto, 0);
        this.stock.put(produto, atual + quantidade);
    }

    public boolean temStock(Produto produto, int quantidade) {
        return this.stock.getOrDefault(produto, 0) >= quantidade;
    }

    public void consumirStock(Produto produto, int quantidade) {
        int atual = this.stock.getOrDefault(produto, 0);
        this.stock.put(produto, Math.max(0, atual - quantidade));
    }

    public void adicionarTarefa(PedidoTarefa tarefa) {
        this.tarefasPendentes.add(tarefa);
    }

    public void adicionarTarefas(List<PedidoTarefa> tarefas) {
        this.tarefasPendentes.addAll(tarefas);
    }

    public List<PedidoTarefa> listarTarefasPendentes() {
        return new ArrayList<>(this.tarefasPendentes);
    }

    public PedidoTarefa obterProximaTarefa() {
        if (this.tarefasPendentes.isEmpty()) {
            return null;
        }
        return this.tarefasPendentes.get(0);
    }

    public void removerTarefa(PedidoTarefa tarefa) {
        this.tarefasPendentes.remove(tarefa);
    }

    public void moverTarefaParaMeio(PedidoTarefa tarefa) {
        if (!this.tarefasPendentes.remove(tarefa)) {
            return;
        }
        int meio = this.tarefasPendentes.size() / 2;
        this.tarefasPendentes.add(meio, tarefa);
    }

    public void moverTarefasPedidoParaMeio(String pedidoId) {
        List<PedidoTarefa> doPedido = new ArrayList<>();
        for (PedidoTarefa tarefa : this.tarefasPendentes) {
            if (tarefa.getPedidoId().equals(pedidoId)) {
                doPedido.add(tarefa);
            }
        }
        if (doPedido.isEmpty()) {
            return;
        }
        this.tarefasPendentes.removeAll(doPedido);
        int meio = this.tarefasPendentes.size() / 2;
        this.tarefasPendentes.addAll(meio, doPedido);
    }
}
