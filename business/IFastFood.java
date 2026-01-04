package business;

import java.util.List;
import business.subPedidos.Pedido;
import business.subProdutos.Produto;
import business.subFuncionarios.Funcionario;
import business.subClientes.Cliente;
import business.subTarefas.Tarefa;
import business.subBancadas.Bancada;
import business.subPedidos.PedidoTarefa;
import business.subRestaurantes.Restaurante;
import java.util.Date;
import java.util.Map;

public interface IFastFood {
    String criarPedido(String clienteId, List<Produto> produtos);
    Pedido obterPedido(String pedidoId);
    List<Pedido> listarPedidos();
    List<PedidoTarefa> pagarPedido(String pedidoId);
    List<PedidoTarefa> obterTarefasPendentes(String pedidoId);
    List<Pedido> listarPedidosProntos();
    boolean entregarPedido(String pedidoId);

    String adicionarProduto(String nome, float preco, String tarefaId);
    Produto obterProduto(String produtoId);
    List<Produto> listarProdutos();

    String registarFuncionario(String nome, String cargo);
    Funcionario obterFuncionario(String funcionarioId);
    List<Funcionario> listarFuncionarios();
    void associarBancadaFuncionario(String funcionarioId, String bancadaId);
    List<PedidoTarefa> listarTarefasPendentesFuncionario(String funcionarioId);
    String entregarProximoPedidoPronto(String funcionarioId);

    String registarCliente(String nome, String contacto);
    Cliente obterCliente(String clienteId);
    List<Cliente> listarClientes();

    String criarTarefa(String descricao, String bancadaId, double tempoEstimadoMin);
    Tarefa obterTarefa(String tarefaId);
    List<Tarefa> listarTarefas();

    String registarBancada(String nome, String local);
    Bancada obterBancada(String bancadaId);
    List<Bancada> listarBancadas();
    void adicionarStockBancada(String bancadaId, Produto produto, int quantidade);
    boolean executarProximaTarefaBancada(String bancadaId);

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

    List<Produto> getStockBaixo();
    boolean checkProdutos(List<Integer> prod);
    boolean checkQuantidades(List<Integer> quanti);
    boolean fazEncomenda(List<Integer> prod, List<Integer> quanti);
    boolean checkProduto(int codProduto, int quantidade);
    boolean checkProduto(int codProd, String nome);
    Produto addProduto(int codProd);
    boolean removeProduto(int codProd);
    void updateMenu(Produto prod);
    void getProdId();
    void getMenuInput();
    boolean validarMenu(int codMenu);

    boolean checkLogin(int codFunc, String pass);
    void logIn(int codFunc);
    void getRestId();
    void getMensagem();
    boolean validaMensagem(String men);
    void validaMensagem(int codRest, String men);

    boolean checkTarefa(int codTarefa);
    boolean checkStockTarefa(int codTarefa);
    void finalizaTarefa(int codTarefa);
    void registraMetrica(int codTarefa);
    void updateTarefa(Date data);
    void sortTarefas();
    void notificaCliente();
    Date getTimeEntrega(int codProduto);
    void registaErro(int codPedido);

    void getIdRest();
    boolean checkIdRest(int codRest);
    boolean validaRest(int codRest);
    int getTempoMedio(int codRest);
    Map<String, Integer> getTrabalhoPosto(int codRest);
    int getFaturacao(int codRest);
    void printDados(Map<String, Integer> usos, int faturado, int tempo);

    String registarRestaurante(String nome);
    Restaurante obterRestaurante(String restauranteId);
    void adicionarFuncionarioRestaurante(String restauranteId, String funcionarioId);
    void adicionarBancadaRestaurante(String restauranteId, String bancadaId);
    List<String> listarFuncionariosRestaurante(String restauranteId);
    List<String> listarBancadasRestaurante(String restauranteId);
}
