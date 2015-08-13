package br.com.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.domain.JPAUtil;
import br.com.domain.Veiculo;

public class MainTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		Veiculo veiculo = new Veiculo();
		veiculo.setFabricante("Honda");
		veiculo.setModelo("Civic");
		veiculo.setAnoFabricacao(2012);
		veiculo.setAnoModelo(2013);
		veiculo.setValor(new BigDecimal(71300));

		manager.persist(veiculo);

		tx.commit();

		Veiculo veiculo2 = manager.getReference(Veiculo.class, 1L);
		if (veiculo2 != null) {
			System.out.println(veiculo2.getFabricante());
			System.out.println("Valor atual: " + veiculo2.getValor());
			veiculo2.setValor(veiculo2.getValor().add(new BigDecimal(500)));
			System.out.println("Novo valor: " + veiculo2.getValor());
		}

		Query query = manager.createQuery("from tab_veiculo");
		List<Veiculo> veiculos = query.getResultList();
		for (Veiculo veiculo3 : veiculos) {
			System.out.println(veiculo3.getCodigo() + " - "
					+ veiculo3.getFabricante() + " " + veiculo3.getModelo()
					+ ", ano " + veiculo3.getAnoFabricacao() + "/"
					+ veiculo3.getAnoModelo() + " por " + "R$"
					+ veiculo3.getValor());
		}
		
		manager.remove(veiculo);

		manager.close();
		JPAUtil.close();
	}
}
