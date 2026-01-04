package business.subFuncionarios;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import business.subBancadas.BancadasFacade;
import business.subPedidos.PedidosFacade;
import business.subPedidos.PedidoTarefa;

public class FuncionariosFacade implements IFuncionarios {
    private static FuncionariosFacade instance;

    private final List<Funcionario> funcionarios;

    private FuncionariosFacade() {
        this.funcionarios = new ArrayList<>();
    }

    public static FuncionariosFacade getInstance() {
        if (FuncionariosFacade.instance == null) {
            FuncionariosFacade.instance = new FuncionariosFacade();
        }
        return FuncionariosFacade.instance;
    }

    public void reset() {
        this.funcionarios.clear();
    }

    @Override
    public String registarFuncionario(String nome, String cargo) {
        String id = UUID.randomUUID().toString();
        Funcionario funcionario = new Funcionario(id, nome, cargo,"123");
        this.funcionarios.add(funcionario);
        return id;
    }

    @Override
    public Funcionario obterFuncionario(String funcionarioId) {
        for (Funcionario funcionario : this.funcionarios) {
            if (funcionario.getId().equals(funcionarioId)) {
                return funcionario;
            }
        }
        return null;
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(this.funcionarios);
    }

    @Override
    public void associarBancada(String funcionarioId, String bancadaId) {
        Funcionario funcionario = obterFuncionario(funcionarioId);
        if (funcionario != null) {
            funcionario.setBancadaId(bancadaId);
        }
    }

    @Override
    public String obterBancadaFuncionario(String funcionarioId) {
        Funcionario funcionario = obterFuncionario(funcionarioId);
        if (funcionario == null) {
            return null;
        }
        return funcionario.getBancadaId();
    }

    @Override
    public List<PedidoTarefa> listarTarefasPendentes(String funcionarioId) {
        String bancadaId = obterBancadaFuncionario(funcionarioId);
        if (bancadaId == null) {
            return new ArrayList<>();
        }
        return BancadasFacade.getInstance().listarTarefasPendentes(bancadaId);
    }

    @Override
    public String entregarProximoPedidoPronto(String funcionarioId) {
        Funcionario funcionario = obterFuncionario(funcionarioId);
        if (funcionario == null) {
            return null;
        }
        List<business.subPedidos.Pedido> prontos = PedidosFacade.getInstance().listarPedidosProntos();
        if (prontos.isEmpty()) {
            return null;
        }
        business.subPedidos.Pedido pedido = prontos.get(0);
        aguardarEntregaAleatoria();
        boolean entregue = PedidosFacade.getInstance().entregarPedido(pedido.getId());
        if (!entregue) {
            return null;
        }
        return pedido.getId();
    }

    private void aguardarEntregaAleatoria() {
        int segundos = ThreadLocalRandom.current().nextInt(1, 31);
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean checkLogin(int codFunc, String pass) {
        return true;
    }

    @Override
    public void logIn(int codFunc) {
    }

    @Override
    public void getRestId() {
    }

    @Override
    public void getMensagem() {
    }

    @Override
    public boolean validaMensagem(String men) {
        return true;
    }

    @Override
    public void validaMensagem(int codRest, String men) {
    }
}
