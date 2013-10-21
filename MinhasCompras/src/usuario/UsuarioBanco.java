/*
 * Classe que faz inserção, consulta, atualização das informações sobre usuários no banco de dados
 */

package usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import conexao.Conexao;
import usuario.Usuario;

public class UsuarioBanco {
	
	private Connection conn;
	
	int id;
    String nome;
    String cpf;
    String email;
    String senha;
	
	public UsuarioBanco() {
		
		this.conn = new Conexao().getConnection();
		
	}
	
	public void incluirUsuario (Usuario usuario){
	    
    	String sqlInsert = "INSERT INTO usuarios (id,nome,cpf,email, senha) VALUES ( ?, ?, ?, ?, ?)";
    	
    	try{
    		
    		PreparedStatement stmt = conn.prepareStatement(sqlInsert);
    		
    		stmt.setLong(1, usuario.getId());
    		stmt.setString(2, usuario.getNome());
    		stmt.setString(3, usuario.getCpf());
    		stmt.setString(4, usuario.getEmail());
    		stmt.setString(5, usuario.getSenha());
    		
    		stmt.execute();
    		stmt.close();
    		
    	} catch(SQLException u){
    		
    		throw new RuntimeException(u);
    		
    	}
    	
    }
    
    public void consultarUsuario (Usuario usuario){
        
    	String sqlSelect = "SELECT nome, cpf, email FROM usuarios";
    	
    	ResultSet rs = null;
    	
    	try{
    		
    		PreparedStatement stmt = conn.prepareStatement(sqlSelect);
    		
    		rs = stmt.executeQuery();
    		
    		if(rs.next()){
    			
    			usuario.setId(rs.getInt(1));
    			usuario.setNome(rs.getString(2));
    			usuario.setCpf(rs.getString(3));
    			usuario.setEmail(rs.getString(4));
    			
    		}
    		
    	} catch(SQLException u){
    		
    		throw new RuntimeException(u);
    		
    	}
    }
}
