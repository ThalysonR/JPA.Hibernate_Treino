package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TesteTodasAsMovimentacoesDasContas {
    public static void main(String[] args) {
        JPAUtil jpau = new JPAUtil();
        EntityManager em = jpau.getEntityManager();
        em.getTransaction().begin();

        String jpql = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes";
        Query query = em.createQuery(jpql);

        List<Conta> todasAsContas = query.getResultList();

        todasAsContas.forEach(c -> {
            System.out.println("Titular: " + c.getTitular());
            System.out.println("Movimentacoes: ");
            System.out.println(c.getMovimentacoes());
        });

        em.close();
        jpau.closeEntityManager();
    }
}
