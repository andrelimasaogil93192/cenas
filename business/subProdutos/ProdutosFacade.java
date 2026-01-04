package business.subProdutos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import business.subTarefas.Tarefa;
import business.subTarefas.TarefasFacade;

public class ProdutosFacade implements IProdutos {
    private static ProdutosFacade instance;

    private final List<Produto> produtos;

    private ProdutosFacade() {
        this.produtos = new ArrayList<>();
    }

    public static ProdutosFacade getInstance() {
        if (ProdutosFacade.instance == null) {
            ProdutosFacade.instance = new ProdutosFacade();
        }
        return ProdutosFacade.instance;
    }

    public void reset() {
        this.produtos.clear();
    }

    @Override
    public String adicionarProduto(String nome, float preco, String tarefaId) {
        String id = UUID.randomUUID().toString();
        Tarefa tarefa = TarefasFacade.getInstance().obterTarefa(tarefaId);
        if (tarefa == null) {
            return null;
        }
        Produto produto = new Produto(id, nome, preco, tarefa);
        this.produtos.add(produto);
        return id;
    }

    @Override
    public Produto obterProduto(String produtoId) {
        for (Produto produto : this.produtos) {
            if (produto.getId().equals(produtoId)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listarProdutos() {
        return new ArrayList<>(this.produtos);
    }

    @Override
    public List<Produto> getStockBaixo() {
        return new ArrayList<>();
    }

    @Override
    public boolean checkProdutos(List<Integer> prod) {
        return true;
    }

    @Override
    public boolean checkQuantidades(List<Integer> quanti) {
        return true;
    }

    @Override
    public boolean fazEncomenda(List<Integer> prod, List<Integer> quanti) {
        return true;
    }

    @Override
    public boolean checkProduto(int codProduto, int quantidade) {
        return true;
    }

    @Override
    public boolean checkProduto(int codProd, String nome) {
        return true;
    }

    @Override
    public Produto addProduto(int codProd) {
        return null;
    }

    @Override
    public boolean removeProduto(int codProd) {
        return true;
    }

    @Override
    public void updateMenu(Produto prod) {
    }

    @Override
    public void getProdId() {
    }

    @Override
    public void getMenuInput() {
    }

    @Override
    public boolean validarMenu(int codMenu) {
        return true;
    }
}
