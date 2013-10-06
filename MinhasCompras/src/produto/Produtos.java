package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Produtos {
    
	int id;
	String nome;
	String marca;
	long codigo;
	double valor;
	String unidade_de_medida;
    
    public int getIdProduto() {
        return id;
    }

    public void setIdProduto(int id) {
        this.id = id;
    }
    
    public String getNomeProduto() {
        return nome;
    }

    public void setNomeProduto(String nome) {
        this.nome = nome;
    }

    public String getMarcaProduto() {
        return marca;
    }

    public void setMarcaProduto(String marca) {
        this.marca = marca;
    }

    public long getCodigoProduto() {
        return codigo;
    }

    public void setCodigoProduto(long codigo) {
        this.codigo = codigo;
    }
    
    public double getValorProduto() {
        return valor;
    }

    public void setValorProduto(double valor) {
        this.valor = valor;
    }

    public String getUnidadeMedidaProduto() {
        return unidade_de_medida;
    }

    public void setUnidadeMedidaProduto(String unidade_de_medida) {
        this.unidade_de_medida = unidade_de_medida;
    }
    
    //Insere um produto no banco de dados
    public void incluirProduto (Connection conn){
        
    	String sqlInsert = "INSERT INTO produtos (id, nome, marca, codigo, valor, unidade_de_medida) VALUES ( ?, ?, ?, ?, ?, ?)";
    	
    	PreparedStatement stmt = null;
    	
    	try{
    		
    		stmt = conn.prepareStatement(sqlInsert);
    		
    		stmt.setInt(1, getIdProduto());
    		stmt.setString(2, getNomeProduto());
    		stmt.setString(3, getMarcaProduto());
    		stmt.setLong(4, getCodigoProduto());
    		stmt.setDouble(5, getValorProduto());
    		stmt.setString(6, getUnidadeMedidaProduto());
    		
    		stmt.execute();
    		stmt.close();
    		
    	} catch(SQLException u){
    		
    		throw new RuntimeException(u);
    		
    	}
    	
    }
    
  //Seleciona um produto no banco de dados
    public void consultarProduto (Connection conn){
        
    	String sqlSelect = "SELECT id, nome, marca, codigo, valor, unidade_de_medida FROM produtos";
    	
    	PreparedStatement stmt = null;
    	
    	ResultSet rs = null;
    	
    	try{
    		
    		stmt = conn.prepareStatement(sqlSelect);
    		
    		rs = stmt.executeQuery();
    		
    		if(rs.next()){
    			
    			this.setIdProduto(rs.getInt(1));
    			this.setNomeProduto(rs.getString(2));
    			this.setMarcaProduto(rs.getString(3));
    			this.setCodigoProduto(rs.getLong(4));
    			this.setValorProduto(rs.getInt(5));
    			this.setUnidadeMedidaProduto(rs.getString(6));
    			
    		}
    		
    	} catch(SQLException u){
    		
    		throw new RuntimeException(u);
    		
    	}
    	
    }
    
}