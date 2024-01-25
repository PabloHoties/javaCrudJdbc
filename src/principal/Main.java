package principal;

import java.util.Scanner;

import controllers.PessoaController;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("\nSistema para controle de Pessoas:\n");

		try {

			System.out.println("(1) Cadastrar pessoa");
			System.out.println("(2) Atualizar pessoa");
			System.out.println("(3) Excluir pessoa");
			System.out.println("(4) Consultar pessoas");
			
			System.out.print("\nInforme a opção desejada: ");
			Integer opcao = Integer.parseInt(scanner.nextLine());
			
			PessoaController pessoaController = new PessoaController();
			
			switch(opcao) {
			case 1:
				pessoaController.cadastrarPessoa();
				break;
			case 2:
				pessoaController.atualizarPessoa();
				break;
			case 3:
				pessoaController.excluirPessoa();
				break;
			case 4:
				pessoaController.consultarPessoas();
				break;
			default:
				System.out.println("\nOpção inválida!");
				break;
			}
			
		} catch (Exception e) {
			System.out.println("\nOcorreu um erro: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
