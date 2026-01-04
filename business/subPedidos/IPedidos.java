package business.subPedidos;

import java.util.List;
import business.subProdutos.Produto;
import business.subPedidos.PedidoTarefa;

public interface IPedidos {
    String criarPedido(String clienteId, List<Produto> produtos);
    Pedido obterPedido(String pedidoId);
    List<Pedido> listarPedidos();
    List<PedidoTarefa> pagarPedido(String pedidoId);
    List<PedidoTarefa> obterTarefasPendentes(String pedidoId);
    List<Pedido> listarPedidosProntos();
    boolean entregarPedido(String pedidoId);

    float getPreco(List<Produto> pedido);
    boolean savePagamento(int codPedido, String tipoPagamento);
    boolean savePedido(List<Produto> pedido, int codPedido);
    void fowardPedido(int codPedido);
    void atualizarPedido(int codProduto, int novoItem);
    void adicionarPedido(int codPedido, List<Integer> novosItens);
    void adicionarNota(int codPedido, String nota);
    void alergias(int codPedido, int alergico);
    void takeAway(int codPedido);
    void pago(int codPedido);

    void regConclusaoPedido(int codPedido);
    void contaPedido();
    void adiconaData(int codPedido);
    void regPedidoNEntregue(int codPedido);
    void contaPedidoNEntregue();
}
