import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(null, "Lucas", "lucas@gmail.com", "Professor");
		Pessoa p2 = new Pessoa(null, "João", "joao@gmail.com", "Aluno");
		Pessoa p3 = new Pessoa(null, "Pedro", "pedro@gmail.com", "Funcionário");
		Pessoa p;
		// System.out.println(p1);
		// System.out.println(p2);
		// System.out.println(p3);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaHibernate");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		p = em.find(Pessoa.class, 2);
		System.out.println(p);
		p.setNome("Robson");
		// em.refresh(p);
		// em.persist(p1);
		// em.persist(p2);
		// em.persist(p3);
		em.getTransaction().commit();


		

		p = em.find(Pessoa.class, 2);
		System.out.println(p);
	}

}
