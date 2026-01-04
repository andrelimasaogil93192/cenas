package business.subRestaurantes;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private final String id;
    private final String nome;
    private final List<String> funcionarios;
    private final List<String> bancadas;

    public Restaurante(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
        this.bancadas = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public List<String> getFuncionarios() {
        return new ArrayList<>(this.funcionarios);
    }

    public List<String> getBancadas() {
        return new ArrayList<>(this.bancadas);
    }

    public void adicionarFuncionario(String funcionarioId) {
        this.funcionarios.add(funcionarioId);
    }

    public void adicionarBancada(String bancadaId) {
        this.bancadas.add(bancadaId);
    }
}
