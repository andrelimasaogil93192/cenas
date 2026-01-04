package business.subClientes;

import java.util.List;

public interface IClientes {
    String registarCliente(String nome, String contacto);
    Cliente obterCliente(String clienteId);
    List<Cliente> listarClientes();
}
