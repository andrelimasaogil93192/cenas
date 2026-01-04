package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subClientes.Cliente;

public class ClienteDAO {
    private final Map<String, Cliente> clientes;

    public ClienteDAO() {
        this.clientes = new HashMap<>();
    }

    public void put(Cliente cliente) {
        this.clientes.put(cliente.getId(), cliente);
    }

    public Cliente get(String id) {
        return this.clientes.get(id);
    }

    public List<Cliente> list() {
        return new ArrayList<>(this.clientes.values());
    }
}
