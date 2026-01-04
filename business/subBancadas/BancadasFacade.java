package business.subBancadas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import business.subPedidos.Pedido;
import business.subPedidos.PedidoTarefa;
import business.subPedidos.PedidosFacade;
import business.subProdutos.Produto;

public class BancadasFacade implements IBancadas {
    private static BancadasFacade instance;

    private final List<Bancada> bancadas;
    private final Map<String, String> pedidosBloqueados;

    private BancadasFacade() {
        this.bancadas = new ArrayList<>();
        this.pedidosBloqueados = new HashMap<>();
    }

    public static BancadasFacade getInstance() {
        if (BancadasFacade.instance == null) {
            BancadasFacade.instance = new BancadasFacade();
        }
        return BancadasFacade.instance;
    }

    public void reset() {
        this.bancadas.clear();
        this.pedidosBloqueados.clear();
    }

    @Override
    public String registarBancada(String nome, String local) {
        String id = UUID.randomUUID().toString();
        Bancada bancada = new Bancada(id, nome, local);
        this.bancadas.add(bancada);
        return id;
    }

    @Override
    public Bancada obterBancada(String bancadaId) {
        for (Bancada bancada : this.bancadas) {
            if (bancada.getId().equals(bancadaId)) {
                return bancada;
            }
        }
        return null;
    }

    @Override
    public List<Bancada> listarBancadas() {
        return new ArrayList<>(this.bancadas);
    }

    public void adicionarStock(String bancadaId, Produto produto, int quantidade) {
        Bancada bancada = obterBancada(bancadaId);
        if (bancada != null) {
            bancada.adicionarStock(produto, quantidade);
        }
    }

    public void adicionarTarefa(String bancadaId, PedidoTarefa tarefa) {
        Bancada bancada = obterBancada(bancadaId);
        if (bancada != null) {
            bancada.adicionarTarefa(tarefa);
        }
    }

    public void adicionarTarefas(String bancadaId, List<PedidoTarefa> tarefas) {
        Bancada bancada = obterBancada(bancadaId);
        if (bancada != null) {
            bancada.adicionarTarefas(tarefas);
        }
    }

    public List<PedidoTarefa> listarTarefasPendentes(String bancadaId) {
        Bancada bancada = obterBancada(bancadaId);
        if (bancada == null) {
            return new ArrayList<>();
        }
        return bancada.listarTarefasPendentes();
    }

    public boolean executarProximaTarefa(String bancadaId) {
        Bancada bancada = obterBancada(bancadaId);
        if (bancada == null) {
            return false;
        }
        PedidoTarefa tarefa = bancada.obterProximaTarefa();
        if (tarefa == null) {
            return false;
        }
        String pedidoId = tarefa.getPedidoId();
        String bancadaBloqueio = this.pedidosBloqueados.get(pedidoId);
        if (bancadaBloqueio != null && !bancadaBloqueio.equals(bancadaId)) {
            moverTarefasPedidoParaMeio(pedidoId);
            return false;
        }
        Produto produto = tarefa.getProduto();
        if (!bancada.temStock(produto, 1)) {
            this.pedidosBloqueados.put(pedidoId, bancadaId);
            moverTarefasPedidoParaMeio(pedidoId);
            return false;
        }
        bancada.consumirStock(produto, 1);
        bancada.removerTarefa(tarefa);
        Pedido pedido = PedidosFacade.getInstance().obterPedido(tarefa.getPedidoId());
        if (pedido != null) {
            pedido.concluirTarefa(tarefa);
        }
        if (bancadaId.equals(this.pedidosBloqueados.get(pedidoId))) {
            this.pedidosBloqueados.remove(pedidoId);
        }
        return true;
    }

    private void moverTarefasPedidoParaMeio(String pedidoId) {
        for (Bancada bancada : this.bancadas) {
            bancada.moverTarefasPedidoParaMeio(pedidoId);
        }
    }
}
