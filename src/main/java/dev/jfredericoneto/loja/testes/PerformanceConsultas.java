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

public class PerformanceConsultas {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
        em.close();
        System.out.println(pedido.getCliente().getNome());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Samsung Galaxy S21 FE", "Celular intermediário", new BigDecimal(1500),
                celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal(8000), videogames);
        Produto macbook = new Produto("Macbook", "Macbook Pro Retina", new BigDecimal(14000), informatica);

        Cliente cliente = new Cliente("João", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();
        em.close();
    }

}
