package business.subProdutos;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import business.subTarefas.Tarefa;

public class Produto {
    private final String id;
    private final String nome;
    private final float preco;
    private final Tarefa tarefa;
    private int stock;
    private final Map<Produto, Integer> compostoPor = new LinkedHashMap<>();

    public Produto(String id, String nome, float preco, Tarefa tarefa) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tarefa = tarefa;
        this.stock = 10;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }

    public Tarefa getTarefa() {
        return this.tarefa;
    }

    public int getStock() {
        return this.stock;
    }

    public void addStock(int stock) {
        this.stock = stock;
    }

    public Map<Produto, Integer> getCompostoPor() {
        return this.compostoPor;
    }

    public Boolean addComposto(List<Produto> produtos, List<Integer> quantidades) {
        if (produtos == null || quantidades == null || produtos.size() != quantidades.size()) {
            return false;
        }

        int tamanho = produtos.size();
        for (int i = 0; i < tamanho; i++) {
            Produto produto = produtos.get(i);
            Integer quantidadeProduto = quantidades.get(i);
            this.compostoPor.put(produto, quantidadeProduto);
        }
        return this.compostoPor.size() == tamanho;
    }


}
