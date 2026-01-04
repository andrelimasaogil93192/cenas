package business.subClientes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientesFacade implements IClientes {
    private static ClientesFacade instance;

    private final List<Cliente> clientes;

    private ClientesFacade() {
        this.clientes = new ArrayList<>();
    }

    public static ClientesFacade getInstance() {
        if (ClientesFacade.instance == null) {
            ClientesFacade.instance = new ClientesFacade();
        }
        return ClientesFacade.instance;
    }

    public void reset() {
        this.clientes.clear();
    }

    @Override
    public String registarCliente(String nome, String contacto) {
        String id = UUID.randomUUID().toString();
        Cliente cliente = new Cliente(id, nome, contacto);
        this.clientes.add(cliente);
        return id;
    }

    @Override
    public Cliente obterCliente(String clienteId) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getId().equals(clienteId)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        return new ArrayList<>(this.clientes);
    }
}
