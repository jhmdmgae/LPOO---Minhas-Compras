package conexao;

//faz as importa��es de classes necess�rias para o funcionamento do programa
import java.sql.Connection; // conex�o SQL para Java
import java.sql.DriverManager; // driver de conex�o SQL para Java
import java.sql.SQLException; // classe para tratamento de exce��es

//conex�o ao banco de dados
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