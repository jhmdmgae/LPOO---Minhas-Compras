package codigobarras;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Principal {
       
       public static void main(String[] args) throws IOException {
               
    	   //lê imagem e atribui ao objeto
   	   		//BufferedImage image = ImageIO.read( new File("7899876543215_3e0bb.jpg"));
   	   		BufferedImage image = ImageIO.read( new File("3033710074365_cdcd0.jpg"));
   	   		//BufferedImage image = ImageIO.read( new File("sample-1d-barcode1.jpg"));
   	   
  			LerImagemCodigoBarras imagem = new LerImagemCodigoBarras(image);   			
  			//coverte padrao bits, informado imagem.converte()       		
      		DecodificarPadraoDeBits decodificar = new DecodificarPadraoDeBits(imagem.converte());        			
      		String novoCodigoBarrasDecodificado = decodificar.decodificaPadraoBits();
      		
      		System.out.println("Codigo de barras:" + novoCodigoBarrasDecodificado);
      		
      		//ValidaEAN_13 codigoValidoOuInvalido = new ValidaEAN_13(novoCodigoBarrasDecodificado);
      		//System.out.println("Codigo valido?" + codigoValidoOuInvalido.validar());
      		
       		
       }
}
