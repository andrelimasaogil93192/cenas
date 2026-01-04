package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subBancadas.Bancada;

public class BancadaDAO {
    private final Map<String, Bancada> bancadas;

    public BancadaDAO() {
        this.bancadas = new HashMap<>();
    }

    public void put(Bancada bancada) {
        this.bancadas.put(bancada.getId(), bancada);
    }

    public Bancada get(String id) {
        return this.bancadas.get(id);
    }

    public List<Bancada> list() {
        return new ArrayList<>(this.bancadas.values());
    }
}
