package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Aula {
	
	private static final long serialVersionUID = 1L;
	@Id
	String codAula;
	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Professor professor;
	
	@ManyToMany(targetEntity = Pessoa.class)
	private List<Pessoa> pessoa;

	@Override
	public String toString() {
		return "\n     ► Código da aula: " + codAula + " | Nome: " + nome ;
	}
	public String toStringTwo() {
		return "*** Aula:  \n Código da aula: " + codAula + " \n Nome: " + nome + "  \n Professor: " + professor.getNome() + "  \n Alunos: " + pessoa ;
	}
	public Aula(String id, String nome, Professor professor) {
		super();
		this.codAula = id;
		this.nome = nome;
		this.professor = professor;

	}
	public Aula() {

	}
	


	public String getCodAula() {
		return codAula;
	}

	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa.add(pessoa);
	}
	
	
	
	
	
}
