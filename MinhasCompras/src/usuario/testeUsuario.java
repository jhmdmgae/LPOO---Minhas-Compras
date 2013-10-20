package usuario;
 
public class testeUsuario {
 
    public static void main(String[] args) {
 
    	Usuario novoUsuario = new Usuario();
		novoUsuario.setNome("j");
		novoUsuario.setEmail("j");
		novoUsuario.setCpf("j");
		novoUsuario.setSenha("j");
		
		UsuarioBanco inserirUsuario = new UsuarioBanco();
		inserirUsuario.incluirUsuario(novoUsuario);
    }
}