package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Pessoa;
import factories.ConnectionFactory;

public class PessoaRepository {

	public void create(Pessoa pessoa) throws Exception {

		// Obtendo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// escrever o comando SQL para realizar o cadastro de uma pessoa
		PreparedStatement statement = connection
				.prepareStatement("insert into pessoa(id, nome, email, cpf) values(?, ?, ?, ?)");

		statement.setObject(1, pessoa.getId());
		statement.setString(2, pessoa.getNome());
		statement.setString(3, pessoa.getEmail());
		statement.setString(4, pessoa.getCpf());
		statement.execute();

		// fechando a conexão do banco de dados
		connection.close();
	}

	public void update(Pessoa pessoa) throws Exception {

		// Obtendo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo o comando SQl para realizar a atualização da pessoa
		PreparedStatement statement = connection
				.prepareStatement("update pessoa set nome=?, email=?, cpf=? where id=?");

		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getEmail());
		statement.setString(3, pessoa.getCpf());
		statement.setObject(4, pessoa.getId());

		// Executando e fechando
		statement.execute();
		statement.close();
	}

	public void delete(Pessoa pessoa) throws Exception {

		// Obtendo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo o comando SQL para realizar a exclusão da pessoa
		PreparedStatement statement = connection.prepareStatement("delete from pessoa where id=?");

		statement.setObject(1, pessoa.getId());

		statement.execute();
		statement.close();
	}

	public List<Pessoa> findAll() throws Exception {

		// Obtendo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Executando uma instrução SQL para consultar todas as pessoas
		PreparedStatement statement = connection.prepareStatement("select * from pessoa order by nome");

		// Executando e lendo os dados obtidos na minha consulta
		ResultSet resultSet = statement.executeQuery();

		// Declarando uma lista da classe pessoa
		List<Pessoa> lista = new ArrayList<Pessoa>();

		// Percorrendo cada registro obtido no ResultSet
		while (resultSet.next()) {

			// Lendo cada campo do registro do banco de dados
			Pessoa pessoa = new Pessoa();

			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setCpf(resultSet.getString("cpf"));
			pessoa.setEmail(resultSet.getString("email"));

			lista.add(pessoa);
		}

		connection.close(); // Fechando a conexão do banco de dados
		return lista; // Retornar o conteúdo da lista
	}

	public Pessoa findById(UUID id) throws Exception {

		// Obtendo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma instrução SQL para consultar uma pessoa através do id
		PreparedStatement statement = connection.prepareStatement("select * from pessoa where id=?");

		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Pessoa pessoa = null;

		// Se algum registro foi encontrado
		if (resultSet.next()) {

			pessoa = new Pessoa();

			pessoa.setId(UUID.fromString(resultSet.getString("id")));
			pessoa.setNome(resultSet.getString("nome"));
			pessoa.setCpf(resultSet.getString("cpf"));
			pessoa.setEmail(resultSet.getString("email"));
		}

		connection.close();
		return pessoa;
	}
}
