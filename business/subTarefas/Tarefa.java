package business.subTarefas;

public class Tarefa {
    private final String id;
    private final String descricao;
    private final String bancadaId;
    private final double tempoEstimadoMin;

    public Tarefa(String id, String descricao, String bancadaId, double tempoEstimadoMin) {
        this.id = id;
        this.descricao = descricao;
        this.bancadaId = bancadaId;
        this.tempoEstimadoMin = tempoEstimadoMin;
    }

    public String getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getBancadaId() {
        return this.bancadaId;
    }

    public double getTempoEstimadoMin() {
        return this.tempoEstimadoMin;
    }
}
