package lista;

import java.util.ArrayList;

public class Lista {
	
	private int id;
	private String titulo;
	private String[] produtos;
	
	public Lista(){
    	
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getProdutos() {
        return produtos;
    }

    public void setProdutos(String[] produtos) {
        this.produtos = produtos;
    }
	
	ArrayList lista = new ArrayList();

}
