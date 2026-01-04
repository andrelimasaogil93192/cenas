package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import business.subProdutos.Produto;

public class ProdutoDAO {
    private final Map<String, Produto> produtos;

    public ProdutoDAO() {
        this.produtos = new HashMap<>();
    }

    public void put(Produto produto) {
        this.produtos.put(produto.getId(), produto);
    }

    public Produto get(String id) {
        return this.produtos.get(id);
    }

    public List<Produto> list() {
        return new ArrayList<>(this.produtos.values());
    }
}
