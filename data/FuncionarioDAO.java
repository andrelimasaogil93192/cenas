package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subFuncionarios.Funcionario;

public class FuncionarioDAO {
    private final Map<String, Funcionario> funcionarios;

    public FuncionarioDAO() {
        this.funcionarios = new HashMap<>();
    }

    public void put(Funcionario funcionario) {
        this.funcionarios.put(funcionario.getId(), funcionario);
    }

    public Funcionario get(String id) {
        return this.funcionarios.get(id);
    }

    public List<Funcionario> list() {
        return new ArrayList<>(this.funcionarios.values());
    }
}
