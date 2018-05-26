package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class TesteJPQL {
    public static void main(String[] args) {
        JPAUtil jpau = new JPAUtil();
        EntityManager em = jpau.getEntityManager();
        em.getTransaction().begin();

        Conta conta = new Conta();
        conta.setId(2);

        String jpql = "SELECT m FROM Movimentacao m WHERE m.conta = :pConta AND m.tipo = :pTipo order by m.valor desc";
        Query query = em.createQuery(jpql);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        List<Movimentacao> resultados = query.getResultList();

        resultados.forEach(m -> {
            System.out.println("Descricao: " + m.getDescricao());
            System.out.println("Valor: " + m.getValor());
        });

//        BigDecimal soma = (BigDecimal)query.getSingleResult();
//        System.out.println("A soma é: " + soma);

        em.getTransaction().commit();
        em.close();
        jpau.closeEntityManager();
    }
}
