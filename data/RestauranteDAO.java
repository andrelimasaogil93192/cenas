package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subRestaurantes.Restaurante;

public class RestauranteDAO {
    private final Map<String, Restaurante> restaurantes;

    public RestauranteDAO() {
        this.restaurantes = new HashMap<>();
    }

    public void put(Restaurante restaurante) {
        this.restaurantes.put(restaurante.getId(), restaurante);
    }

    public Restaurante get(String id) {
        return this.restaurantes.get(id);
    }

    public List<Restaurante> list() {
        return new ArrayList<>(this.restaurantes.values());
    }
}
