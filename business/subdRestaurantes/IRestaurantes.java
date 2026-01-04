package business.subRestaurantes;

import java.util.Map;
import java.util.List;

public interface IRestaurantes {
    String registarRestaurante(String nome);
    Restaurante obterRestaurante(String restauranteId);
    void adicionarFuncionario(String restauranteId, String funcionarioId);
    void adicionarBancada(String restauranteId, String bancadaId);
    List<String> listarFuncionarios(String restauranteId);
    List<String> listarBancadas(String restauranteId);

    void getIdRest();
    boolean checkIdRest(int codRest);
    boolean validaRest(int codRest);
    int getTempoMedio(int codRest);
    Map<String, Integer> getTrabalhoPosto(int codRest);
    int getFaturacao(int codRest);
    void printDados(Map<String, Integer> usos, int faturado, int tempo);
}
