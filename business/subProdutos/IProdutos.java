package business.subProdutos;

import java.util.List;

public interface IProdutos {
    String adicionarProduto(String nome, float preco, String tarefaId);
    Produto obterProduto(String produtoId);
    List<Produto> listarProdutos();

    List<Produto> getStockBaixo();
    boolean checkProdutos(List<Integer> prod);
    boolean checkQuantidades(List<Integer> quanti);
    boolean fazEncomenda(List<Integer> prod, List<Integer> quanti);

    boolean checkProduto(int codProduto, int quantidade);
    boolean checkProduto(int codProd, String nome);
    Produto addProduto(int codProd);
    boolean removeProduto(int codProd);
    void updateMenu(Produto prod);
    void getProdId();
    void getMenuInput();
    boolean validarMenu(int codMenu);
}
