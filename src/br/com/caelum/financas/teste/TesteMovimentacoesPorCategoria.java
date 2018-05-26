package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TesteMovimentacoesPorCategoria {
    public static void main(String[] args) {
        JPAUtil jpau = new JPAUtil();
        EntityManager em = jpau.getEntityManager();
        em.getTransaction().begin();

        Categoria categoria = new Categoria();
        categoria.setId(3);

        String jpql = "SELECT m FROM Movimentacao m join m.categoria c where c = :pCategoria";
        Query query = em.createQuery(jpql);
        query.setParameter("pCategoria", categoria);

        List<Movimentacao> resultados = query.getResultList();

        resultados.forEach(m -> {
            System.out.println("Descricao: " + m.getDescricao());
            System.out.println("Conta.id: " + m.getConta().getId());
        });

        em.getTransaction().commit();
        em.close();
        jpau.closeEntityManager();
    }
}
