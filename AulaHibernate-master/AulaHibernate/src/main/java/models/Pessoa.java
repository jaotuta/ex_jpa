package models;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer aid;
	String nome;
	String email;
	String cargo;
	
	@ManyToMany(targetEntity = Aula.class)
	@JoinColumn(name = "aid", referencedColumnName = "codAula")
	private List<Aula> aulas;

	public Pessoa(Integer id, String nome, String email, String cargo) {
		super();
		this.aid = id;
		this.nome = nome;
		this.email = email;
		this.cargo = cargo;
	}
	
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Aula aula) {
		this.aulas.add(aula);
	}

	public Pessoa () {
		
	}

	@Override
	public String toString() {
		return "id: " + aid + " \n Nome: " + nome + ", Email: " + email + ", Cargo: " + cargo ;
	}

	public Integer getId() {
		return aid;
	}



	public void setId(Integer aid) {
		this.aid = aid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
