package business.subClientes;

public class Cliente {
    private final String id;
    private final String nome;
    private final String contacto;

    public Cliente(String id, String nome, String contacto) {
        this.id = id;
        this.nome = nome;
        this.contacto = contacto;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getContacto() {
        return this.contacto;
    }
}
