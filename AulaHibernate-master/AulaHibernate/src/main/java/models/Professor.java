package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

@Entity
public class Professor {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nome;
	String email;
	String cargo;
	
	@OneToMany
	private List<Aula> aula;

	public Professor(Integer id, String nome, String email, String cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cargo = cargo;
	}
	public Professor() {

	}
	@Override
	public String toString() {
		return nome + " - Email: " + email + " - Cargo: " + cargo ;
	}
	public String toString3() {
		return "◙ " + nome + " - Email: " + email + " - Cargo: " + cargo + " \n Aulas: " + aula ;
	}
	public String toStringTwo() {
		return nome ;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<Aula> getAula() {
		return aula;
	}
	public void setAula(Aula aula) {
		this.aula.add(aula);
	}
	public void setAula2(List<Aula> aulas) {
		this.aula = aulas;
	}
}
