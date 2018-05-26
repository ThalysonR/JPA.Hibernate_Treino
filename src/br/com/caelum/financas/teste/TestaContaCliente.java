package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Cliente;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.EntityManager;

public class TestaContaCliente {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");

        Conta conta = new Conta();
        conta.setId(2);

        cliente.setConta(conta);

        JPAUtil jpau = new JPAUtil();
        EntityManager em = jpau.getEntityManager();
        em.getTransaction().begin();

        em.persist(cliente);

        em.getTransaction().commit();
        em.close();
        jpau.closeEntityManager();
    }
}
