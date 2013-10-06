package conexao;

//faz as importações de classes necessárias para o funcionamento do programa
import java.sql.Connection; // conexão SQL para Java
import java.sql.DriverManager; // driver de conexão SQL para Java
import java.sql.SQLException; // classe para tratamento de exceções

//conexão ao banco de dados
public class Conexao {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/minhascompras","root","");
		}
		catch(SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}
}