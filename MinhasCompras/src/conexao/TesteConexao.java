package conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) throws SQLException {
        Connection conn = new Conexao().getConnection();
        System.out.println("Conexão aberta com sucesso!");
        conn.close();
    }
}