package business.subPedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import business.subProdutos.Produto;

public class PedidosFacade implements IPedidos {
    private static PedidosFacade instance;

    private final List<Pedido> pedidos;

    private PedidosFacade() {
        this.pedidos = new ArrayList<>();
    }

    public static PedidosFacade getInstance() {
        if (PedidosFacade.instance == null) {
            PedidosFacade.instance = new PedidosFacade();
        }
        return PedidosFacade.instance;
    }

    public void reset() {
        this.pedidos.clear();
    }

    @Override
    public String criarPedido(String clienteId, List<Produto> produtos) {
        String id = UUID.randomUUID().toString();
        Pedido pedido = new Pedido(id, clienteId, produtos);
        this.pedidos.add(pedido);
        return id;
    }

    @Override
    public Pedido obterPedido(String pedidoId) {
        for (Pedido pedido : this.pedidos) {
            if (pedido.getId().equals(pedidoId)) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(this.pedidos);
    }

    @Override
    public List<PedidoTarefa> pagarPedido(String pedidoId) {
        Pedido pedido = obterPedido(pedidoId);
        if (pedido == null) {
            return new ArrayList<>();
        }
        pedido.pago();
        return pedido.getTarefas();
    }

    @Override
    public List<PedidoTarefa> obterTarefasPendentes(String pedidoId) {
        Pedido pedido = obterPedido(pedidoId);
        if (pedido == null) {
            return new ArrayList<>();
        }
        return pedido.getTarefas();
    }

    @Override
    public List<Pedido> listarPedidosProntos() {
        List<Pedido> prontos = new ArrayList<>();
        for (Pedido pedido : this.pedidos) {
            if (pedido.isPronto() && pedido.getEntregue() == null) {
                prontos.add(pedido);
            }
        }
        return prontos;
    }

    @Override
    public boolean entregarPedido(String pedidoId) {
        Pedido pedido = obterPedido(pedidoId);
        if (pedido == null) {
            return false;
        }
        return pedido.entregarPedido();
    }

    @Override
    public float getPreco(List<Produto> pedido) {
        float total = 0.0f;
        for (Produto produto : pedido) {
            total += produto.getPreco();
        }
        return total;
    }

    @Override
    public boolean savePagamento(int codPedido, String tipoPagamento) {
        return true;
    }

    @Override
    public boolean savePedido(List<Produto> pedido, int codPedido) {
        return true;
    }

    @Override
    public void fowardPedido(int codPedido) {
    }

    @Override
    public void atualizarPedido(int codProduto, int novoItem) {
    }

    @Override
    public void adicionarPedido(int codPedido, List<Integer> novosItens) {
    }

    @Override
    public void adicionarNota(int codPedido, String nota) {
    }

    @Override
    public void alergias(int codPedido, int alergico) {
    }

    @Override
    public void takeAway(int codPedido) {
    }

    @Override
    public void pago(int codPedido) {
    }

    @Override
    public void regConclusaoPedido(int codPedido) {
    }

    @Override
    public void contaPedido() {
    }

    @Override
    public void adiconaData(int codPedido) {
    }

    @Override
    public void regPedidoNEntregue(int codPedido) {
    }

    @Override
    public void contaPedidoNEntregue() {
    }
}
