package codigobarras;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class LerCodigo2 {
	
	private BufferedImage imagem;
	private int[][] pixel;
	private double soma;
	private int mediaRGB;
	private int inicioCodigodeBarras = 0;
	private int finalCodigodeBarras = 0;
	private int TamanhoPadrao;
	
	ArrayList<String> codigobinario = new ArrayList<String> ();

	public LerCodigo2(BufferedImage imagem) {
        this.imagem = imagem;
    }
	
	public String[] converte(){
		
		//System.out.println("image = " + imagem);
		
		int w = imagem.getWidth();
        int h = imagem.getHeight();
        pixel = new int[h][w];
		
		
		mediapixel();
		MargemEsquerda();
		MargemDireita();
		TamanhoPadraoColuna();
		mediapixel();
		criarbits();
		
		
		String[] converte = new String[codigobinario.size()];
		
	    for (int i = 0; i < codigobinario.size(); i++) {
	    	converte[i] = codigobinario.get(i).toString();
	    	System.out.print(converte[i]);
	    }
	    
	    return converte;
	    
	}
	
	private void capturapixel() {
		
		int w;
	    int h;
		
		if(inicioCodigodeBarras == 0){
			w = imagem.getWidth();
			h = imagem.getHeight();
		} else {
			w = finalCodigodeBarras;
			h = imagem.getHeight();
		}
		
		System.out.println("finalCodigodeBarras = " + finalCodigodeBarras);
		System.out.println("inicioCodigodeBarras = " + inicioCodigodeBarras);
		System.out.println("w = " + w);
		System.out.println("h = " + h);
		System.out.println("mediaRGB = " + mediaRGB);
		
		
		
		for (int row = ((h/2)-50); row < ((h/2)+50); row++) {
			
	         for (int col = inicioCodigodeBarras; col < w; col++) {
	        	//System.out.println("pixel["+i+"]["+y+"] = " + pixel[i][y]);
	        	pixel[row][col] = imagem.getRGB(col, row);
	            if (pixel[row][col] < 0){
	            	pixel[row][col] = -pixel[row][col];
	            }
	         }
	    }
		
	}
	
	private void somapixel(){
		
		for (int i = (imagem.getHeight()/2)-50; i < (imagem.getHeight()/2)+50; i++) {
	         for (int y = 0; y < imagem.getWidth(); y++) {
	        	 soma = soma + pixel[i][y];
	         }
		}
	}
	
	private void mediapixel(){
		
		capturapixel();
		somapixel();		
		mediaRGB = (int) (soma/(imagem.getHeight()*imagem.getWidth()));
		System.out.println("mediaRGB = " + mediaRGB);
		
	}
	
	
	private void TamanhoPadraoColuna() {
		
		int i = imagem.getHeight()/2;
		for (int y = inicioCodigodeBarras; y < imagem.getWidth(); y++) {
			//System.out.println("pixel["+row+"]["+col+"] = " + pixel[row][col]);
			if(pixel[i][y] <= mediaRGB){
				break;
			}
			TamanhoPadrao++;
		}
		System.out.println("TamanhoPadrao = " + TamanhoPadrao);
		
	}

	private void MargemDireita() {
		
		int i = imagem.getHeight()/2;
		for (int y = imagem.getWidth()-1; y > 0; y--) {
			if(pixel[i][y] >= mediaRGB){
				if(finalCodigodeBarras  == 0){
					finalCodigodeBarras = y;
				}
			}
		}
		
	}

	private void MargemEsquerda() {
		
		int i = imagem.getHeight()/2;
		for (int y = 0; y < imagem.getWidth(); y++) {
			if(pixel[i][y] >= mediaRGB){
				if(inicioCodigodeBarras == 0){
					inicioCodigodeBarras = y+1;
				}
			}
		}
	}
	
	private void criarbits(){
		
		
		boolean teste = false;
		int branco = 0;
		int preto = 0;
		int i = imagem.getHeight()/2;
		
		for (int y = inicioCodigodeBarras; y <= finalCodigodeBarras; y++) {
			
			
			if(pixel[i][y] <= mediaRGB && pixel[i][y+1] > mediaRGB){
				teste = true;
			}
			if(pixel[i][y] >= mediaRGB && pixel[i][y+1] < mediaRGB){
				teste = true;
			}
			if(pixel[i][y] <= mediaRGB){
				branco++;
 				System.out.println("pixel["+i+"]["+y+"] = " + pixel[i][y]);
 				if(teste == true){
 					System.out.println("branco = " + branco);
 					if(branco <= (TamanhoPadrao+(TamanhoPadrao/4)) && branco >= (TamanhoPadrao-(TamanhoPadrao/4))){
 						codigobinario.add("0");
 						branco = 0;
 	 	 				teste = false;
 					}else if(branco <= (2*TamanhoPadrao)+(TamanhoPadrao/4) && branco >= ((2*TamanhoPadrao)-(TamanhoPadrao/4))){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (3*TamanhoPadrao)+(TamanhoPadrao/4) && branco >= ((3*TamanhoPadrao)-(TamanhoPadrao/4))){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (4*TamanhoPadrao)+(TamanhoPadrao/4) && branco >= ((4*TamanhoPadrao)-(TamanhoPadrao/4))){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}
 	        	}
        	 } else {
 				preto++;
 				System.out.println("pixel["+i+"]["+y+"] = " + pixel[i][y]);
 				if(teste == true){
 					System.out.println("preto = " + preto);
	 				if(preto <= TamanhoPadrao+(TamanhoPadrao/4) && preto >= TamanhoPadrao-(TamanhoPadrao/4)){
						codigobinario.add("1");
						preto = 0;
	 	 				teste = false;
					}else if(preto <= (2*TamanhoPadrao)+(TamanhoPadrao/4) && preto >= (2*TamanhoPadrao)-(TamanhoPadrao/4)){
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (3*TamanhoPadrao)+(TamanhoPadrao/4) && preto >= (3*TamanhoPadrao)-(TamanhoPadrao/4)){
						codigobinario.add("1");
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (4*TamanhoPadrao)+(TamanhoPadrao/4) && preto >= (4*TamanhoPadrao)-(TamanhoPadrao/4)){
						codigobinario.add("1");
						codigobinario.add("1");
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}
 				}
        	 }
		}
	}
}
