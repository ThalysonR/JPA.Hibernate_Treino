package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.util.JPAUtil;
import br.com.caelum.financas.modelo.Conta;

public class TesteConta {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setAgencia("123");
		conta.setTitular("Leonardo");
		conta.setBanco("Caixa");
		conta.setNumero("456");
		

		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);

		conta.setBanco("Bradesco");
		em.getTransaction().commit();
		
		em.close();

	}

}
