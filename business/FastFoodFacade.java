package business;

import java.util.List;
import business.subPedidos.IPedidos;
import business.subPedidos.Pedido;
import business.subPedidos.PedidoTarefa;
import business.subPedidos.PedidosFacade;
import business.subProdutos.IProdutos;
import business.subProdutos.Produto;
import business.subProdutos.ProdutosFacade;
import business.subFuncionarios.Funcionario;
import business.subFuncionarios.FuncionariosFacade;
import business.subFuncionarios.IFuncionarios;
import business.subClientes.Cliente;
import business.subClientes.ClientesFacade;
import business.subClientes.IClientes;
import business.subTarefas.ITarefas;
import business.subTarefas.Tarefa;
import business.subTarefas.TarefasFacade;
import business.subBancadas.Bancada;
import business.subBancadas.BancadasFacade;
import business.subBancadas.IBancadas;
import business.subRestaurantes.IRestaurantes;
import business.subRestaurantes.Restaurante;
import business.subRestaurantes.RestaurantesFacade;
import java.util.Date;
import java.util.Map;

public class FastFoodFacade implements IFastFood {
    private static FastFoodFacade instance;

    private final IPedidos pedidos;
    private final IProdutos produtos;
    private final IFuncionarios funcionarios;
    private final IClientes clientes;
    private final ITarefas tarefas;
    private final IBancadas bancadas;
    private final IRestaurantes restaurantes;

    private FastFoodFacade() {
        this.pedidos = PedidosFacade.getInstance();
        this.produtos = ProdutosFacade.getInstance();
        this.funcionarios = FuncionariosFacade.getInstance();
        this.clientes = ClientesFacade.getInstance();
        this.tarefas = TarefasFacade.getInstance();
        this.bancadas = BancadasFacade.getInstance();
        this.restaurantes = RestaurantesFacade.getInstance();
    }

    public static FastFoodFacade getInstance() {
        if (FastFoodFacade.instance == null) {
            FastFoodFacade.instance = new FastFoodFacade();
        }
        return FastFoodFacade.instance;
    }

    @Override
    public String criarPedido(String clienteId, List<Produto> produtos) {
        return this.pedidos.criarPedido(clienteId, produtos);
    }

    @Override
    public Pedido obterPedido(String pedidoId) {
        return this.pedidos.obterPedido(pedidoId);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return this.pedidos.listarPedidos();
    }

    @Override
    public List<PedidoTarefa> pagarPedido(String pedidoId) {
        List<PedidoTarefa> tarefas = this.pedidos.pagarPedido(pedidoId);
        for (PedidoTarefa tarefa : tarefas) {
            String bancadaId = tarefa.getTarefa().getBancadaId();
            this.bancadas.adicionarTarefa(bancadaId, tarefa);
        }
        return tarefas;
    }

    @Override
    public List<PedidoTarefa> obterTarefasPendentes(String pedidoId) {
        return this.pedidos.obterTarefasPendentes(pedidoId);
    }

    @Override
    public List<Pedido> listarPedidosProntos() {
        return this.pedidos.listarPedidosProntos();
    }

    @Override
    public boolean entregarPedido(String pedidoId) {
        return this.pedidos.entregarPedido(pedidoId);
    }

    @Override
    public String adicionarProduto(String nome, float preco, String tarefaId) {
        return this.produtos.adicionarProduto(nome, preco, tarefaId);
    }

    @Override
    public Produto obterProduto(String produtoId) {
        return this.produtos.obterProduto(produtoId);
    }

    @Override
    public List<Produto> listarProdutos() {
        return this.produtos.listarProdutos();
    }

    @Override
    public String registarFuncionario(String nome, String cargo) {
        return this.funcionarios.registarFuncionario(nome, cargo);
    }

    @Override
    public Funcionario obterFuncionario(String funcionarioId) {
        return this.funcionarios.obterFuncionario(funcionarioId);
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return this.funcionarios.listarFuncionarios();
    }

    @Override
    public void associarBancadaFuncionario(String funcionarioId, String bancadaId) {
        this.funcionarios.associarBancada(funcionarioId, bancadaId);
    }

    @Override
    public List<PedidoTarefa> listarTarefasPendentesFuncionario(String funcionarioId) {
        return this.funcionarios.listarTarefasPendentes(funcionarioId);
    }

    @Override
    public String entregarProximoPedidoPronto(String funcionarioId) {
        return this.funcionarios.entregarProximoPedidoPronto(funcionarioId);
    }

    @Override
    public String registarCliente(String nome, String contacto) {
        return this.clientes.registarCliente(nome, contacto);
    }

    @Override
    public Cliente obterCliente(String clienteId) {
        return this.clientes.obterCliente(clienteId);
    }

    @Override
    public List<Cliente> listarClientes() {
        return this.clientes.listarClientes();
    }

    @Override
    public String criarTarefa(String descricao, String bancadaId, double tempoEstimadoMin) {
        return this.tarefas.criarTarefa(descricao, bancadaId, tempoEstimadoMin);
    }

    @Override
    public Tarefa obterTarefa(String tarefaId) {
        return this.tarefas.obterTarefa(tarefaId);
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return this.tarefas.listarTarefas();
    }

    @Override
    public String registarBancada(String nome, String local) {
        return this.bancadas.registarBancada(nome, local);
    }

    @Override
    public Bancada obterBancada(String bancadaId) {
        return this.bancadas.obterBancada(bancadaId);
    }

    @Override
    public List<Bancada> listarBancadas() {
        return this.bancadas.listarBancadas();
    }

    @Override
    public void adicionarStockBancada(String bancadaId, Produto produto, int quantidade) {
        this.bancadas.adicionarStock(bancadaId, produto, quantidade);
    }

    @Override
    public boolean executarProximaTarefaBancada(String bancadaId) {
        return this.bancadas.executarProximaTarefa(bancadaId);
    }

    @Override
    public float getPreco(List<Produto> pedido) {
        return this.pedidos.getPreco(pedido);
    }

    @Override
    public boolean savePagamento(int codPedido, String tipoPagamento) {
        return this.pedidos.savePagamento(codPedido, tipoPagamento);
    }

    @Override
    public boolean savePedido(List<Produto> pedido, int codPedido) {
        return this.pedidos.savePedido(pedido, codPedido);
    }

    @Override
    public void fowardPedido(int codPedido) {
        this.pedidos.fowardPedido(codPedido);
    }

    @Override
    public void atualizarPedido(int codProduto, int novoItem) {
        this.pedidos.atualizarPedido(codProduto, novoItem);
    }

    @Override
    public void adicionarPedido(int codPedido, List<Integer> novosItens) {
        this.pedidos.adicionarPedido(codPedido, novosItens);
    }

    @Override
    public void adicionarNota(int codPedido, String nota) {
        this.pedidos.adicionarNota(codPedido, nota);
    }

    @Override
    public void alergias(int codPedido, int alergico) {
        this.pedidos.alergias(codPedido, alergico);
    }

    @Override
    public void takeAway(int codPedido) {
        this.pedidos.takeAway(codPedido);
    }

    @Override
    public void pago(int codPedido) {
        this.pedidos.pago(codPedido);
    }

    @Override
    public void regConclusaoPedido(int codPedido) {
        this.pedidos.regConclusaoPedido(codPedido);
    }

    @Override
    public void contaPedido() {
        this.pedidos.contaPedido();
    }

    @Override
    public void adiconaData(int codPedido) {
        this.pedidos.adiconaData(codPedido);
    }

    @Override
    public void regPedidoNEntregue(int codPedido) {
        this.pedidos.regPedidoNEntregue(codPedido);
    }

    @Override
    public void contaPedidoNEntregue() {
        this.pedidos.contaPedidoNEntregue();
    }

    @Override
    public List<Produto> getStockBaixo() {
        return this.produtos.getStockBaixo();
    }

    @Override
    public boolean checkProdutos(List<Integer> prod) {
        return this.produtos.checkProdutos(prod);
    }

    @Override
    public boolean checkQuantidades(List<Integer> quanti) {
        return this.produtos.checkQuantidades(quanti);
    }

    @Override
    public boolean fazEncomenda(List<Integer> prod, List<Integer> quanti) {
        return this.produtos.fazEncomenda(prod, quanti);
    }

    @Override
    public boolean checkProduto(int codProduto, int quantidade) {
        return this.produtos.checkProduto(codProduto, quantidade);
    }

    @Override
    public boolean checkProduto(int codProd, String nome) {
        return this.produtos.checkProduto(codProd, nome);
    }

    @Override
    public Produto addProduto(int codProd) {
        return this.produtos.addProduto(codProd);
    }

    @Override
    public boolean removeProduto(int codProd) {
        return this.produtos.removeProduto(codProd);
    }

    @Override
    public void updateMenu(Produto prod) {
        this.produtos.updateMenu(prod);
    }

    @Override
    public void getProdId() {
        this.produtos.getProdId();
    }

    @Override
    public void getMenuInput() {
        this.produtos.getMenuInput();
    }

    @Override
    public boolean validarMenu(int codMenu) {
        return this.produtos.validarMenu(codMenu);
    }

    @Override
    public boolean checkLogin(int codFunc, String pass) {
        return this.funcionarios.checkLogin(codFunc, pass);
    }

    @Override
    public void logIn(int codFunc) {
        this.funcionarios.logIn(codFunc);
    }

    @Override
    public void getRestId() {
        this.funcionarios.getRestId();
    }

    @Override
    public void getMensagem() {
        this.funcionarios.getMensagem();
    }

    @Override
    public boolean validaMensagem(String men) {
        return this.funcionarios.validaMensagem(men);
    }

    @Override
    public void validaMensagem(int codRest, String men) {
        this.funcionarios.validaMensagem(codRest, men);
    }

    @Override
    public boolean checkTarefa(int codTarefa) {
        return this.tarefas.checkTarefa(codTarefa);
    }

    @Override
    public boolean checkStockTarefa(int codTarefa) {
        return this.tarefas.checkStockTarefa(codTarefa);
    }

    @Override
    public void finalizaTarefa(int codTarefa) {
        this.tarefas.finalizaTarefa(codTarefa);
    }

    @Override
    public void registraMetrica(int codTarefa) {
        this.tarefas.registraMetrica(codTarefa);
    }

    @Override
    public void updateTarefa(Date data) {
        this.tarefas.updateTarefa(data);
    }

    @Override
    public void sortTarefas() {
        this.tarefas.sortTarefas();
    }

    @Override
    public void notificaCliente() {
        this.tarefas.notificaCliente();
    }

    @Override
    public Date getTimeEntrega(int codProduto) {
        return this.tarefas.getTimeEntrega(codProduto);
    }

    @Override
    public void registaErro(int codPedido) {
        this.tarefas.registaErro(codPedido);
    }

    @Override
    public void getIdRest() {
        this.restaurantes.getIdRest();
    }

    @Override
    public boolean checkIdRest(int codRest) {
        return this.restaurantes.checkIdRest(codRest);
    }

    @Override
    public boolean validaRest(int codRest) {
        return this.restaurantes.validaRest(codRest);
    }

    @Override
    public int getTempoMedio(int codRest) {
        return this.restaurantes.getTempoMedio(codRest);
    }

    @Override
    public Map<String, Integer> getTrabalhoPosto(int codRest) {
        return this.restaurantes.getTrabalhoPosto(codRest);
    }

    @Override
    public int getFaturacao(int codRest) {
        return this.restaurantes.getFaturacao(codRest);
    }

    @Override
    public void printDados(Map<String, Integer> usos, int faturado, int tempo) {
        this.restaurantes.printDados(usos, faturado, tempo);
    }

    @Override
    public String registarRestaurante(String nome) {
        return this.restaurantes.registarRestaurante(nome);
    }

    @Override
    public Restaurante obterRestaurante(String restauranteId) {
        return this.restaurantes.obterRestaurante(restauranteId);
    }

    @Override
    public void adicionarFuncionarioRestaurante(String restauranteId, String funcionarioId) {
        this.restaurantes.adicionarFuncionario(restauranteId, funcionarioId);
    }

    @Override
    public void adicionarBancadaRestaurante(String restauranteId, String bancadaId) {
        this.restaurantes.adicionarBancada(restauranteId, bancadaId);
    }

    @Override
    public List<String> listarFuncionariosRestaurante(String restauranteId) {
        return this.restaurantes.listarFuncionarios(restauranteId);
    }

    @Override
    public List<String> listarBancadasRestaurante(String restauranteId) {
        return this.restaurantes.listarBancadas(restauranteId);
    }
}
