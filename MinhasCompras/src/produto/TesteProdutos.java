package produto;
 
import java.sql.SQLException;
import java.sql.Connection;
import conexao.Conexao;
 
 public class TesteProdutos {
 
    public static void main(String[] args) {
 
      Connection conn = null;
 
      Produtos cl;
 
      try {
 
      // obtem conexao com o banco
         Conexao bd = new Conexao();
         conn = bd.getConnection();
 
         conn.setAutoCommit(false);
 
      // Inclusao do Primeiro Produtos
         cl = new Produtos();
         cl.setNomeProduto("Chocolate");
         cl.setMarcaProduto("kibom");
         cl.setCodigoProduto(2879);
         cl.setValorProduto(.85);
         cl.setUnidadeMedidaProduto("Kg");
         
         cl.incluirProduto(conn);
 

      // efetiva inclusoes
         conn.commit();
         System.out.println("Inclusão concluída");
         System.out.println("Buscando...");
 
      // Busca Clientes
         cl.consultarProduto (conn);
 
         String saida = "Nome: "+cl.getNomeProduto()+
                         "\nMarca: "+cl.getMarcaProduto()+
                         "\nid: "+cl.getIdProduto();
 
         System.out.println(saida);
      }
          catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
               try {
                  conn.rollback();
               }
                   catch (SQLException e1) {
                     System.out.print(e1.getStackTrace());
                  }
            }
         }
      finally {
         if (conn != null) {
            try {
               conn.close();
            }
                catch (SQLException e1) {
                  System.out.print(e1.getStackTrace());
               }
         }
      }
      System.exit(0);
    }
}
