package business.subRestaurantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RestaurantesFacade implements IRestaurantes {
    private static RestaurantesFacade instance;
    private final List<Restaurante> restaurantes;

    private RestaurantesFacade() {
        this.restaurantes = new ArrayList<>();
    }

    public static RestaurantesFacade getInstance() {
        if (RestaurantesFacade.instance == null) {
            RestaurantesFacade.instance = new RestaurantesFacade();
        }
        return RestaurantesFacade.instance;
    }

    public void reset() {
        this.restaurantes.clear();
    }

    @Override
    public String registarRestaurante(String nome) {
        String id = UUID.randomUUID().toString();
        this.restaurantes.add(new Restaurante(id, nome));
        return id;
    }

    @Override
    public Restaurante obterRestaurante(String restauranteId) {
        for (Restaurante restaurante : this.restaurantes) {
            if (restaurante.getId().equals(restauranteId)) {
                return restaurante;
            }
        }
        return null;
    }

    @Override
    public void adicionarFuncionario(String restauranteId, String funcionarioId) {
        Restaurante restaurante = obterRestaurante(restauranteId);
        if (restaurante != null) {
            restaurante.adicionarFuncionario(funcionarioId);
        }
    }

    @Override
    public void adicionarBancada(String restauranteId, String bancadaId) {
        Restaurante restaurante = obterRestaurante(restauranteId);
        if (restaurante != null) {
            restaurante.adicionarBancada(bancadaId);
        }
    }

    @Override
    public List<String> listarFuncionarios(String restauranteId) {
        Restaurante restaurante = obterRestaurante(restauranteId);
        if (restaurante == null) {
            return new ArrayList<>();
        }
        return restaurante.getFuncionarios();
    }

    @Override
    public List<String> listarBancadas(String restauranteId) {
        Restaurante restaurante = obterRestaurante(restauranteId);
        if (restaurante == null) {
            return new ArrayList<>();
        }
        return restaurante.getBancadas();
    }

    @Override
    public void getIdRest() {
    }

    @Override
    public boolean checkIdRest(int codRest) {
        return true;
    }

    @Override
    public boolean validaRest(int codRest) {
        return true;
    }

    @Override
    public int getTempoMedio(int codRest) {
        return 0;
    }

    @Override
    public Map<String, Integer> getTrabalhoPosto(int codRest) {
        return new HashMap<>();
    }

    @Override
    public int getFaturacao(int codRest) {
        return 0;
    }

    @Override
    public void printDados(Map<String, Integer> usos, int faturado, int tempo) {
    }
}
