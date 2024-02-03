package dev.jfredericoneto.loja.modelo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() {
    }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public Long getId() {
        return id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public String getNome() {
        return this.dadosPessoais.getNome();
    }

    public String getCpf() {
        return this.dadosPessoais.getCpf();
    }
}