/*
 * A classe possui 8 métodos sendo o método converte() o principal. 
 * Este recebe um imagem que é carregada pelo construtor ao inicializar a classe. 
 * Quando converte() é chamada ela inicialmente calcula a média dos valores de cada pixel 
 * usando o método capturapixel() e somapixel() para realizar o cálculo.
 * De posse do valor da média das cores, segue para os métodos MargemEsquerda() e 
 * MargemDireita() que descobre onde inicia o código de barras ao considerar 
 * o primeiro pixel mais escuro da esquerda e da direita. 
 * Em seguida chama o método TamanhoPadraoColuna() que descobre o tamanho em pixel da primeira barra a esquerda. 
 * Com essas informações é chamado novamente o método mediapixel() para que melhore a média das cores capturas 
 * uma vez que removemos os espaços em brancos das laterais.
 * Por último chamamos o método criarbits() que converte o padrão de barras em um padrão de bits depositando 
 * cada coluna capturada no ArrayList "codigobinario" que será usado ao final de tudo para gerar 
 * uma string de retorno, "converte".
 */

package codigobarras;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LerCodigo2 {
	
	private BufferedImage imagem;
	private int[][] pixel;
	private double soma;
	private int mediaRGB;
	private int inicioCodigodeBarras = 0;
	private int finalCodigodeBarras = 0;
	private int TamanhoPadrao;
	private int altura;
	private int var = 0;
	
	ArrayList<String> codigobinario = new ArrayList<String> ();
	private String converte = null;

	public LerCodigo2(BufferedImage imagem) {
        this.imagem = imagem;
    }
	
	public String converte(){
		
		int w = imagem.getWidth();
        int h = imagem.getHeight();
        pixel = new int[h][w];
        altura = (imagem.getHeight()/3)+var;        
        
        do {
		
		mediapixel();
		MargemEsquerda();
		MargemDireita();
		TamanhoPadraoColuna();
		mediapixel();
		criarbits();
	    
	    StringBuilder sb = new StringBuilder();
	    for (String sb1 : codigobinario) {  
    	   sb.append(sb1);
    	}  
	    	  
    	String converte = sb.toString();
    	System.out.println(converte);
    	System.out.println(converte.length());
	    
    	var+= 10;
    	
        } while (converte.length() == 95);
    	
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
		
		int i = altura;
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
		
		int i = altura;
		for (int y = imagem.getWidth()-1; y > 0; y--) {
			if(pixel[i][y] >= mediaRGB){
				if(finalCodigodeBarras  == 0){
					finalCodigodeBarras = y;
				}
			}
		}
		
	}

	private void MargemEsquerda() {
		
		int i = altura;
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
		int i = altura;
		
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
 					if(branco <= (TamanhoPadrao+(TamanhoPadrao/3))){
 						codigobinario.add("0");
 						branco = 0;
 	 	 				teste = false;
 					}else if(branco <= (2*TamanhoPadrao)+(TamanhoPadrao/3)){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (3*TamanhoPadrao)+(TamanhoPadrao/2)){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (4*TamanhoPadrao)+(TamanhoPadrao/2)){
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
	 				if(preto <= TamanhoPadrao+(TamanhoPadrao/3)){
						codigobinario.add("1");
						preto = 0;
	 	 				teste = false;
					}else if(preto <= (2*TamanhoPadrao)+(TamanhoPadrao/3)){
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (3*TamanhoPadrao)+(TamanhoPadrao/2)){
						codigobinario.add("1");
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (4*TamanhoPadrao)+(TamanhoPadrao/2)){
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
