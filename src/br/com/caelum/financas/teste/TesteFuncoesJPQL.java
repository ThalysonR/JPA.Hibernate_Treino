package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class TesteFuncoesJPQL {
    public static void main(String[] args) {
        JPAUtil jpau = new JPAUtil();
        EntityManager em = jpau.getEntityManager();
        em.getTransaction().begin();

        Conta conta = new Conta();
        conta.setId(2);

        String jpql = "SELECT DISTINCT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta AND m.tipo = :pTipo GROUP BY m.data";
        TypedQuery query = em.createQuery(jpql, Double.class);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

//        Double media = (Double) query.getSingleResult();
//        System.out.println("A média é: " + media);
        List<Double> medias = query.getResultList();
        medias.forEach(m -> {
            System.out.println("A média é: " + m);
        });

        em.getTransaction().commit();
        em.close();
        jpau.closeEntityManager();
    }
}
