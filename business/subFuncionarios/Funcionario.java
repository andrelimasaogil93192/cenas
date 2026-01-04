package business.subFuncionarios;

public class Funcionario {
    private final String id;
    private final String nome;
    private final String cargo;
    private final String password;
    private boolean ativo;
    private String bancadaId;

    public Funcionario(String id, String nome, String cargo, String pass) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.password = pass;
        this.ativo = false;
        this.bancadaId = null;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCargo() {
        return this.cargo;
    }

    public String getBancadaId() {
        return this.bancadaId;
    }

    public void setBancadaId(String bancadaId) {
        this.bancadaId = bancadaId;
    }

    void logIn(){
        this.ativo = true;
    }

    void logOut(){
        this.ativo = false;
    }

    public String getPassword() {
        return this.password;
    }

    boolean checkLogin(String user, String pass){
        return this.id.equals(user) && this.password.equals(pass) && !this.ativo;
    }
}
