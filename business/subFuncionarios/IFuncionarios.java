package business.subFuncionarios;

import java.util.List;
import business.subPedidos.PedidoTarefa;

public interface IFuncionarios {
    String registarFuncionario(String nome, String cargo);
    Funcionario obterFuncionario(String funcionarioId);
    List<Funcionario> listarFuncionarios();

    void associarBancada(String funcionarioId, String bancadaId);
    String obterBancadaFuncionario(String funcionarioId);
    List<PedidoTarefa> listarTarefasPendentes(String funcionarioId);
    String entregarProximoPedidoPronto(String funcionarioId);

    boolean checkLogin(int codFunc, String pass);
    void logIn(int codFunc);
    void getRestId();
    void getMensagem();
    boolean validaMensagem(String men);
    void validaMensagem(int codRest, String men);
}
