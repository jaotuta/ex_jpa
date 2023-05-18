import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class Principal {
	private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }
	public static void main(String[] args) {
		Repository repository = new Repository();
        int opcao = 0;
        while (opcao != 4) {
            String resposta = JOptionPane.showInputDialog(null,
                    "Qual operação deseja realizar ?\n [1] - Cadastrar novo Aluno | Professor \n [2] - Exibir todos \n [3] - Buscar Aluno | Professor \n [4] - Sair  ",
                    "Universidade", 1);
            if (isInteger(resposta)) {
                opcao = java.lang.Integer.parseInt(resposta);
                switch (opcao) {
                    case 1: {
                        String nome;
                        String funcao;
                        String email;
                        nome = JOptionPane.showInputDialog(null, "Nome", "Universidade", 1);
                        funcao = JOptionPane.showInputDialog(null, "Aluno ou Professor??", "Universidade", 1);
                        email = JOptionPane.showInputDialog(null, "Email", "Universidade", 1);
                        int confirmacao = JOptionPane.showConfirmDialog(null, "Dados: \n Nome: " + nome + "\n Função: "
                                + funcao + "\n Email: " + email + "\n Confirmar cadastro ? ", "Universidade", 1);
                        if (confirmacao == 0) {
                        	Pessoa pessoa = new Pessoa(null, nome, email, funcao);
                        	repository.save(pessoa);
                            JOptionPane.showMessageDialog(null, "Cadastro realizado !!!", "Universidade", 1);
                        }
                    }
                        break;
                    case 2: {
                        String renderData = "";
                        List<Pessoa> dados = repository.findAll();
                        for (Pessoa pessoa : dados) {
                            renderData += "\n" + pessoa.toString();
                        }

                        JOptionPane.showMessageDialog(null, renderData);
                    }
                        break;
                    case 3: {
                        
                            JOptionPane.showMessageDialog(null, "Opção não implementada", "Universidade", 1);
                        
                    }
                        break;
                    case 4: {
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
