import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Repository {
	EntityManagerFactory emf;
	EntityManager em;
	
	public Repository() {
		this.emf = Persistence.createEntityManagerFactory("ex_jpa01");
		this.em = emf.createEntityManager();	
	}

	public void save (Pessoa pessoa) {
		
		
		try {
			System.out.println(pessoa);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(pessoa); 
			tx.commit();
		} catch (Exception exp) {
		    
		    // close session
		}
		
		

	}
	public void update (Pessoa pessoaNova) {
		Pessoa pessoa = em.find(Pessoa.class, pessoaNova.getId());
		pessoa.setId(pessoaNova.getId());
		pessoa.setNome(pessoaNova.getNome());
		pessoa.setEmail(pessoaNova.getEmail());
		pessoa.setCargo(pessoaNova.getCargo());
	}
	public void delete (Pessoa pessoaDeletar) {
		Pessoa pessoa = em.find(Pessoa.class, pessoaDeletar.getId());
		em.remove(pessoa);
	}
	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll () {
		return em.createQuery("FROM " +	Pessoa.class.getName()).getResultList();
	}
	
}
