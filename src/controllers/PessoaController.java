package controllers;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import entities.Pessoa;
import repositories.PessoaRepository;

public class PessoaController {

	public void cadastrarPessoa() throws Exception {

		System.out.println("\nCadastro de Pessoa:\n");
		Scanner scanner = new Scanner(System.in);

		Pessoa pessoa = new Pessoa();
		pessoa.setId(UUID.randomUUID());

		System.out.print("Informe o nome: ");
		pessoa.setNome(scanner.nextLine());

		System.out.print("Informe o email: ");
		pessoa.setEmail(scanner.nextLine());

		System.out.print("Informe o CPF: ");
		pessoa.setCpf(scanner.nextLine());

		PessoaRepository pessoaRepository = new PessoaRepository();
		pessoaRepository.create(pessoa);

		System.out.println("\nPessoa cadastrada com sucesso!");

		scanner.close();
	}

	public void atualizarPessoa() throws Exception {

		System.out.println("\nEdição de Pessoa:\n");
		Scanner scanner = new Scanner(System.in);

		System.out.print("Informe o ID: ");
		UUID id = UUID.fromString(scanner.nextLine());

		PessoaRepository pessoaRepository = new PessoaRepository();
		Pessoa pessoa = pessoaRepository.findById(id);

		// Verificando se a pessoa foi encontrada
		if (pessoa != null) {

			System.out.print("\nInforme o Nome: ");
			pessoa.setNome(scanner.nextLine());
			System.out.print("\nInforme o Email: ");
			pessoa.setEmail(scanner.nextLine());
			System.out.print("\nInforme o CPF: ");
			pessoa.setCpf(scanner.nextLine());

			// Atualizando o registro de pessoa no banco de dados
			pessoaRepository.update(pessoa);

			System.out.println("\nPessoa atualizada com sucesso!");

		} else {
			System.out.println("\nPessoa não encontrada. Verifique o ID.");
		}

		scanner.close();
	}

	public void excluirPessoa() throws Exception {
		System.out.println("\nExclusão de Pessoa:\n");
		Scanner scanner = new Scanner(System.in);

		System.out.print("Informe o ID: ");
		UUID id = UUID.fromString(scanner.nextLine());

		PessoaRepository pessoaRepository = new PessoaRepository();
		Pessoa pessoa = pessoaRepository.findById(id);

		// Verificando se pessoa foi encontrada
		if (pessoa != null) {

			// Excluindo o registro no banco de dedaos
			pessoaRepository.delete(pessoa);

			System.out.println("\nPessoa excluída com sucesso!");
		} else {
			System.out.println("\nPessoa não encontrada. Verifique o ID.");
		}

		scanner.close();
	}

	public void consultarPessoas() throws Exception {
		
		System.out.println("\nConsulta de Pessoa:\n");
		
		// Consultando todas as pessoas cadastradas
		PessoaRepository pessoaRepository = new PessoaRepository();
		List<Pessoa> lista = pessoaRepository.findAll();
		
		for(Pessoa pessoa : lista) {
			
			System.out.println("ID da pessoa: " + pessoa.getId());
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("Email: " + pessoa.getEmail());
			System.out.println("CPF: " + pessoa.getCpf());
			System.out.println("...");
		}
	}
}
