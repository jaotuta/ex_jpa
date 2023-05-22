import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import models.Aula;
import models.Pessoa;
import models.Professor;

public class Principal {
	private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }
	public static void main(String[] args) {
		Repository repository = new Repository();
        int opcao = 0;
        while (opcao != 5) {
            String resposta = JOptionPane.showInputDialog(null,
                    "Qual operação deseja realizar ?\n [1] - Cadastrar Novo Recurso \n [2] - Exibir todos \n [3] - Matricular aluno \n [4] - Editar recuros \n [5] - Sair  ",
                    "Universidade", 1);
            if (isInteger(resposta)) {
                opcao = java.lang.Integer.parseInt(resposta);
                switch (opcao) {
                    case 1: {
                    	String resposta2 = JOptionPane.showInputDialog(null,
                                "Escolha o recurso:\n [1] - Novo Aluno \n [2] - Novo Professor \n [3] - Nova Aula");
                    	if (Integer.parseInt(resposta2) == 1) {
                    		String nome;
                            String funcao = "Aluno";
                            String email;
                            nome = JOptionPane.showInputDialog(null, "Nome", "Universidade", 1);
                            email = JOptionPane.showInputDialog(null, "Email", "Universidade", 1);
                            int confirmacao = JOptionPane.showConfirmDialog(null, "Dados: \n Nome: " + nome + "\n Função: "
                                    + funcao + "\n Email: " + email + "\n Confirmar cadastro ? ", "Universidade", 1);
                            if (confirmacao == 0) {
                            	Pessoa pessoa = new Pessoa(null, nome, email, funcao);
                            	repository.save(pessoa);
                                JOptionPane.showMessageDialog(null, "Cadastro realizado !!!", "Universidade", 1);
                            }
                    	}
                    	else if (Integer.parseInt(resposta2) == 2) {
                    		String nome;
                            String funcao = "Professor";
                            String email;
                            nome = JOptionPane.showInputDialog(null, "Nome", "Universidade", 1);
                            email = JOptionPane.showInputDialog(null, "Email", "Universidade", 1);
                            int confirmacao = JOptionPane.showConfirmDialog(null, "Dados: \n Nome: " + nome + "\n Função: "
                                    + funcao + "\n Email: " + email + "\n Confirmar cadastro ? ", "Universidade", 1);
                            if (confirmacao == 0) {
                            	Professor professor = new Professor(null, nome, email, funcao);
                            	repository.save(professor);
                                JOptionPane.showMessageDialog(null, "Cadastro realizado !!!", "Universidade", 1);
                            }
                    	}
                    	else if (Integer.parseInt(resposta2) == 3) {
                    		String nome;
                    		String codAula;
                    		List<Professor> professores = repository.findAllProfs();
                    		Professor[] professoress = professores.toArray(new Professor[professores.size()]);
                    		codAula = JOptionPane.showInputDialog(null, "Código da Aula", "Universidade", 1);
                    		nome = JOptionPane.showInputDialog(null, "Nome", "Universidade", 1);
                    	    String initialSelection = "Selecione o Professor";
                    	    Professor professor = (Professor) JOptionPane.showInputDialog(null, "Selecione o professor",
                    	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, professoress, initialSelection);
                    	    Aula aula = new Aula(codAula, nome, professor);
                    	    repository.saveAula(aula);
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, "Entrada Inválida !!!", "Universidade", 1);
                    	}
                    }
                        break;
                    case 2: {
                    	String resposta3 = JOptionPane.showInputDialog(null,
                                "Buscar:\n [1] - Aluno \n [2] - Professor \n [3] - Aula");
                    	if (Integer.parseInt(resposta3) == 1) {
                    		String renderData = "";
                            List<Pessoa> dados = repository.findAllPessoas();
                            for (Pessoa pessoa : dados) {
                                renderData += "\n" + pessoa.toString();
                            }

                            JOptionPane.showMessageDialog(null, renderData);	
                    	}
                    	else if (Integer.parseInt(resposta3) == 2) {
                    		String renderData = "";
                            List<Professor> dados = repository.findAllProfs();
                            for (Professor prof : dados) {
                                renderData += "\n" + prof.toString3();
                            }

                            JOptionPane.showMessageDialog(null, renderData);	
                    	}
                    	else if (Integer.parseInt(resposta3) == 3) {
                    		String renderData = "";
                            List<Aula> dados = repository.findAllAulas();
                            for (Aula aula : dados) {
                                renderData += "\n" + aula.toStringTwo();
                            }

                            JOptionPane.showMessageDialog(null, renderData);	
                    	}else {
                    		JOptionPane.showMessageDialog(null, "Entrada Inválida !!!", "Universidade", 1);
                    	}
                    }
                        break;
                    case 3: {
                    	List<Aula> aulas = repository.findAllAulas();
                		Aula[] aulass = aulas.toArray(new Aula[aulas.size()]);
                	    String initialSelection = "Selecione a Aula";
                	    Aula aula = (Aula) JOptionPane.showInputDialog(null, "Selecione o professor",
                	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, aulass, initialSelection);
                	    
                	    List<Pessoa> pessoas = repository.findAllPessoas();
                	    Pessoa[] pessoass = pessoas.toArray(new Pessoa[pessoas.size()]);
                	    String initialAluno = "Selecione o Aluno";
                	    Pessoa aluno = (Pessoa) JOptionPane.showInputDialog(null, "Selecione o professor",
                	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, pessoass, initialAluno);
                	    aula.setPessoa(aluno);
                	    repository.save(aula);

                        
                    }
                        break;
                    case 4: {
                    	String resposta2 = JOptionPane.showInputDialog(null,
                                "Escolha o recurso para editar:\n [1] - Aluno \n [2] - Professor \n [3] - Aula");
                    	if (Integer.parseInt(resposta2) == 1) {
                    		List<Pessoa> pessoas = repository.findAllPessoas();
                    	    Pessoa[] pessoass = pessoas.toArray(new Pessoa[pessoas.size()]);
                    	    String initialAluno = "Selecione o Aluno";
                    	    Pessoa aluno = (Pessoa) JOptionPane.showInputDialog(null, "Selecione o professor",
                    	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, pessoass, initialAluno);
                    	    
                    	    aluno.setNome(JOptionPane.showInputDialog(null, "Nome:", aluno.getNome()));
                            aluno.setEmail(JOptionPane.showInputDialog(null, "Email:", aluno.getEmail())); 
                            aluno.setCargo(JOptionPane.showInputDialog(null, "Função:", aluno.getCargo())); 
                            repository.updateAluno(aluno);
                            JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!", "Universidade", 1);
                    	    
                    	    
                    	}
                    	else if (Integer.parseInt(resposta2) == 2) {
                    		List<Professor> professores = repository.findAllProfs();
                    		Professor[] professoress = professores.toArray(new Professor[professores.size()]);
                    	    String initialSelection = "Selecione o Professor";
                    	    Professor professor = (Professor) JOptionPane.showInputDialog(null, "Selecione o professor",
                    	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, professoress, initialSelection);
                    	    
                    	    professor.setNome(JOptionPane.showInputDialog(null, "Nome:", professor.getNome()));
                    	    professor.setEmail(JOptionPane.showInputDialog(null, "Email:", professor.getEmail())); 
                    	    professor.setCargo(JOptionPane.showInputDialog(null, "Função:", professor.getCargo())); 
                            repository.updateProfessor(professor);
                            JOptionPane.showMessageDialog(null, "Professor alterado com sucesso!", "Universidade", 1);
                    	    
                    	}
                    	else if (Integer.parseInt(resposta2) == 3) {
                    		List<Aula> aulas = repository.findAllAulas();
                    		Aula[] aulass = aulas.toArray(new Aula[aulas.size()]);
                    	    String initialSelection = "Selecione a Aula";
                    	    Aula aula = (Aula) JOptionPane.showInputDialog(null, "Selecione a Aula",
                    	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, aulass, initialSelection);
                    	    Professor oldProf = aula.getProfessor();
                    	    List<Professor> professores = repository.findAllProfs();
                    		Professor[] professoress = professores.toArray(new Professor[professores.size()]);
                    	    String initialProf = "Selecione o Professor";
                    	    Professor professor = (Professor) JOptionPane.showInputDialog(null, "Selecione o professor",
                    	        "Universidade", JOptionPane.QUESTION_MESSAGE, null, professoress, initialProf);
                    	    if (oldProf != professor) {
                    	    	System.out.println("entrou aqui");
                    	    	repository.removeAula(oldProf, aula);

                    	    }
                	    	repository.updateAula(aula, professor);
                	    	JOptionPane.showMessageDialog(null, "Aula Alterada com sucesso!", "Universidade", 1);
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, "Entrada Inválida !!!", "Universidade", 1);
                    	}
                    }
                        break;
                    case 5: {
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema. !!!", "Universidade",
                                1);
                    }
                        break;
                    default: {
                        JOptionPane.showMessageDialog(null, "Entrada inválida.", "Universidade", 1);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Entrada inválida.", "Universidade UFN", 1);
            }

        }


	}

}
