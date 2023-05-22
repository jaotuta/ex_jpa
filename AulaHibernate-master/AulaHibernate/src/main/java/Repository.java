import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import models.Aula;
import models.Pessoa;
import models.Professor;

public class Repository {
	EntityManagerFactory emf;
	EntityManager em;
	
	
	public Repository() {
		this.emf = Persistence.createEntityManagerFactory("ex_jpa01");
		this.em = emf.createEntityManager();	
	}

	
	public void save (Object obj) {
		
		try {
			System.out.println(obj);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(obj); 
			tx.commit();
		} catch (Exception exp) {		    
		    // close session
		}
			
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findAllPessoas () {
		return em.createQuery("FROM " +	Pessoa.class.getName()).getResultList();
	}
	
	
	public void updateAluno (Pessoa pessoaNova) {
		Pessoa pessoa = em.find(Pessoa.class, pessoaNova.getId());
		pessoa.setId (pessoaNova.getId());
		pessoa.setNome(pessoaNova.getNome());
		pessoa.setEmail(pessoaNova.getEmail());
		pessoa.setCargo(pessoaNova.getCargo());
	}
	public void updateProfessor (Professor profNovo) {
		Professor prof = em.find(Professor.class, profNovo.getId());
		prof.setId (profNovo.getId());
		prof.setNome(profNovo.getNome());
		prof.setEmail(profNovo.getEmail());
		prof.setCargo(profNovo.getCargo());
	}
	public void updateAula (Aula novaAula, Professor prof) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Aula aula = em.find(Aula.class, novaAula.getCodAula());
		Professor professor = em.find(Professor.class, prof.getId());
		aula.setCodAula(novaAula.getCodAula());
		aula.setProfessor(prof);
		professor.setAula(aula);
		tx.commit();
	}
	public void saveAula (Aula aula) {
		
		try {
			System.out.println(aula);
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			Professor professor = em.find(Professor.class, aula.getProfessor().getId());
			professor.setAula(aula);
			
			em.persist(aula); 
			tx.commit();
		} catch (Exception exp) {
		    
		    // close session
		}
			
	}
	
	public void removeAula(Professor professor, Aula aula) {
		List<Aula> novasAulas = new ArrayList<>();
		for (Aula aula_ : professor.getAula()) {
			if (!(aula_.getCodAula().equals(aula.getCodAula()))) {
				novasAulas.add(aula_);
			}
		}
		professor.setAula2(novasAulas);
	}
	
	public void delete (Pessoa pessoaDeletar) {
		Pessoa pessoa = em.find(Pessoa.class, pessoaDeletar.getId());
		em.remove(pessoa);
	}
	

	@SuppressWarnings("unchecked")
	public List<String> findAllPessoasTwo () {
		
		List<String> nomes = new ArrayList<>();
		List<Pessoa> alunos = em.createQuery("FROM " +Pessoa.class.getName()).getResultList();
		
		alunos.forEach(aluno -> {
			nomes.add(aluno.getNome());
		});
		return nomes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> findAllProfs () {		
		return em.createQuery("FROM " +	Professor.class.getName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aula> findAllAulas () {
	
		return em.createQuery("FROM " +	Aula.class.getName()).getResultList();
	}
}
