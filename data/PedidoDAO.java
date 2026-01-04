package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subPedidos.Pedido;

public class PedidoDAO {
    private final Map<String, Pedido> pedidos;

    public PedidoDAO() {
        this.pedidos = new HashMap<>();
    }

    public void put(Pedido pedido) {
        this.pedidos.put(pedido.getId(), pedido);
    }

    public Pedido get(String id) {
        return this.pedidos.get(id);
    }

    public List<Pedido> list() {
        return new ArrayList<>(this.pedidos.values());
    }
}
