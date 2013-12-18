package codigobarras;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class teste {
	
	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read( new File("3033710074365_cdcd0.jpg"));
		LerCodigo2 imagem = new LerCodigo2(image);
		imagem.converte();
	}

}
