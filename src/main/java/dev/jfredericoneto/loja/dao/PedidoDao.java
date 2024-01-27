package dev.jfredericoneto.loja.dao;

import dev.jfredericoneto.loja.modelo.Pedido;
import jakarta.persistence.EntityManager;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

}
