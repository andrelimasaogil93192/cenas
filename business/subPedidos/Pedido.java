package business.subPedidos;

import business.subProdutos.Produto;
import business.subTarefas.Tarefa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Pedido {
    private final String id;
    private final String clienteId;
    private final List<Produto> produtos;
    private final Map<Produto, String> extras;
    private String nota;
    private boolean pago;
    private boolean finalizado;
    private boolean pronto;
    private Date inicio;
    private Date cozinhado;
    private Date entregue;
    private List<PedidoTarefa> tarefas;

    public Pedido(String id, String clienteId, List<Produto> produtos) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtos = new ArrayList<>(produtos);
        this.extras = new HashMap<>();
        this.pago = false;
        this.finalizado = false;
        this.pronto = false;
        this.inicio = new Date();
    }

    public String getId() {
        return this.id;
    }

    public String getClienteId() {
        return this.clienteId;
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(this.produtos);
    }

    public String getNotas() {
        return this.nota;
    }

    public void adicionarNota(String nota) {
        this.nota= nota;
    }

    public String getExtra(Produto produto) {
        String extra = this.extras.get(produto);
        if (extra == null) {
            return "Sem extras";
        }
        return extra;
    }

    void atualizarPedido(Produto produto, String novoItem) {
        this.extras.put(produto, novoItem);
    }

    void adicionarPedido(Produto produto) {
        this.produtos.add(produto);
    }

    float getPreço(){
        float preco = 0;
        for (Produto produto : this.produtos) {
            preco += produto.getPreco();
        }
        return preco;
    }

    void finalizarPedido(){
        this.cozinhado = new Date();
        this.finalizado = true;

    }

    public boolean entregarPedido() {
        if (!this.pronto || this.entregue != null) {
            return false;
        }
        this.entregue = new Date();
        this.finalizado = true;
        return true;
    }

    void pago(){
        this.pago = true;
        if (this.tarefas == null) {
            this.tarefas = gerarTarefas();
        }
    }

    List<Long> getDatas(){
        if (this.pronto) {
            List<Long> res = new ArrayList<>();
            long diffMs = cozinhado.getTime() - inicio.getTime();
            long diffMin = diffMs / (1000 * 60);
            res.add(diffMin);
            diffMs = entregue.getTime() - cozinhado.getTime();
            diffMin = diffMs / (1000 * 60);
            res.add(diffMin);
            return res;
        }
        return null;
    }

    public List<PedidoTarefa> getTarefas() {
        if (this.tarefas == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(this.tarefas);
    }

    public void concluirTarefa(PedidoTarefa tarefa) {
        if (this.tarefas != null) {
            this.tarefas.remove(tarefa);
            if (this.tarefas.isEmpty()) {
                this.pronto = true;
            }
        }
    }

    public boolean isPronto() {
        return this.pronto;
    }

    public Date getEntregue() {
        return this.entregue;
    }


    private List<PedidoTarefa> gerarTarefas() {
        List<TaskNode> nodes = new ArrayList<>();
        int sequence = 0;

        for (Produto produto : this.produtos) {
            List<TaskNode> componentes = new ArrayList<>();
            if (produto.getCompostoPor() != null && !produto.getCompostoPor().isEmpty()) {
                for (Map.Entry<Produto, Integer> entry : produto.getCompostoPor().entrySet()) {
                    Produto componente = entry.getKey();
                    int quantidade = entry.getValue();
                    for (int i = 0; i < quantidade; i++) {
                        TaskNode node = criarNode(componente, sequence++);
                        if (node != null) {
                            componentes.add(node);
                            nodes.add(node);
                        }
                    }
                }
            }
            TaskNode principal = criarNode(produto, sequence++);
            if (principal != null) {
                for (TaskNode componente : componentes) {
                    componente.dependentes.add(principal);
                    principal.indegree++;
                }
                nodes.add(principal);
            }
        }

        PriorityQueue<TaskNode> disponiveis = new PriorityQueue<>(
            Comparator.comparingDouble(TaskNode::getTempo).reversed()
                .thenComparingInt(node -> node.sequence)
        );

        for (TaskNode node : nodes) {
            if (node.indegree == 0) {
                disponiveis.add(node);
            }
        }

        List<PedidoTarefa> resultado = new ArrayList<>();
        while (!disponiveis.isEmpty()) {
            TaskNode atual = disponiveis.poll();
            resultado.add(atual.tarefa);
            for (TaskNode dependente : atual.dependentes) {
                dependente.indegree--;
                if (dependente.indegree == 0) {
                    disponiveis.add(dependente);
                }
            }
        }

        return resultado;
    }

    private TaskNode criarNode(Produto produto, int sequence) {
        Tarefa tarefa = produto.getTarefa();
        if (tarefa == null) {
            return null;
        }
        return new TaskNode(new PedidoTarefa(this.id, produto, tarefa), sequence);
    }

    private static class TaskNode {
        private final PedidoTarefa tarefa;
        private final List<TaskNode> dependentes;
        private int indegree;
        private final int sequence;

        private TaskNode(PedidoTarefa tarefa, int sequence) {
            this.tarefa = tarefa;
            this.sequence = sequence;
            this.dependentes = new ArrayList<>();
            this.indegree = 0;
        }

        private double getTempo() {
            return this.tarefa.getTarefa().getTempoEstimadoMin();
        }
    }
}
