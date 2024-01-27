package dev.jfredericoneto.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import dev.jfredericoneto.loja.dao.CategoriaDao;
import dev.jfredericoneto.loja.dao.ProdutoDao;
import dev.jfredericoneto.loja.modelo.Categoria;
import dev.jfredericoneto.loja.modelo.Produto;
import dev.jfredericoneto.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto1 = produtoDao.buscarPorId(1l);
        System.out.println(produto1.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(produto -> System.out.println(produto.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Samsung Galaxy S21 FE");
        System.out.println("Preco do Produto: " + precoDoProduto);

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Samsung Galaxy S21 FE", "Celular intermediário", new BigDecimal(1500),
                celulares);

        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
