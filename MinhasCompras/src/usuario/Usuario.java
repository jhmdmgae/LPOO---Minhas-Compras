/*
 * Essa classe cria um objeto usuario com os atributos pertinentes ao objeto.
 * Seus métodos servem para inserir e pegar os valores dos seus atributos.
 */
package usuario;

public class Usuario {
    
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    
    public Usuario(){
    	
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
}
