package main;

import java.util.List;
import business.FastFoodFacade;
import business.IFastFood;

public class Main {
    public static void main(String[] args) {
        IFastFood app = FastFoodFacade.getInstance();

        String clienteId = app.registarCliente("Ana", "ana@exemplo.pt");
        String bancadaId = app.registarBancada("Bancada 1", "Linha A");
        String tarefaId = app.criarTarefa("Preparar hamburguer", bancadaId, 6.0);
        String produtoId = app.adicionarProduto("Hamburguer Classico", 4.5F, tarefaId);
        String pedidoId = app.criarPedido(clienteId, List.of(app.obterProduto(produtoId)));

        System.out.println("Pedido criado: " + pedidoId);
        System.out.println("Total de pedidos: " + app.listarPedidos().size());
    }
}
