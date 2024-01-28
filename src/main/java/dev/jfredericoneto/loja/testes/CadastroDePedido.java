package dev.jfredericoneto.loja.testes;

import java.math.BigDecimal;

import dev.jfredericoneto.loja.dao.CategoriaDao;
import dev.jfredericoneto.loja.dao.ClienteDao;
import dev.jfredericoneto.loja.dao.PedidoDao;
import dev.jfredericoneto.loja.dao.ProdutoDao;
import dev.jfredericoneto.loja.modelo.Categoria;
import dev.jfredericoneto.loja.modelo.Cliente;
import dev.jfredericoneto.loja.modelo.ItemPedido;
import dev.jfredericoneto.loja.modelo.Pedido;
import dev.jfredericoneto.loja.modelo.Produto;
import dev.jfredericoneto.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produtoDao.buscarPorId(1l);
        Cliente cliente = clienteDao.buscarPorId(1l);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Samsung Galaxy S21 FE", "Celular intermediário", new BigDecimal(1500),
                celulares);

        Cliente cliente = new Cliente("João", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

}