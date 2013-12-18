package codigobarras;
 
import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.IOException;  
import java.util.ArrayList;

import javax.imageio.ImageIO;

//import barcodereader.image.ImageSupport;

public class LerCodigo {

	//private static int SENSIBILIDADE = 50;

	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read( new File("3033710074365_cdcd0.jpg"));
	
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int[][] result = new int[h][w];
	    int row;
	    int col;
	    float soma = 0;
	    int referenciacolini = 0;
	    int referenciacolfim = 0;
	    int referenciatam = 0;
	    int branco = 0;
	    int preto = 0;
	    int desviopadrao = 0;
	    boolean teste = false;
	    
	    ArrayList<String> codigobinario = new ArrayList<String> (); 
	    
	    System.out.println(w);
	    System.out.println(h);
	    
	    //captura os pixels
		for (row = (h/2)-50; row < (h/2)+50; row++) {
	         for (col = 0; col < w; col++) {
	            result[row][col] = image.getRGB(col, row);
	            if (result[row][col] < 0){
	            	result[row][col] = -result[row][col];
	            }	
	        	soma = soma + result[row][col];
	         }
	    }
		
		int mediaRGB = (int) (soma/(h*w));
		System.out.println("soma = " + soma);
		System.out.println("mediaRGB = " + mediaRGB);
		
		
		//descobre a margem esquerda
		row = h/2;
		for (col = 0; col < w; col++) {
			if(result[row][col] >= mediaRGB){
				if(referenciacolini == 0){
					referenciacolini = col+1;
				}
			}
		}
		
		//descobre o tamanho inicial da barra para padrão
		for (col = referenciacolini; col < w; col++) {
			//System.out.println("result["+row+"]["+col+"] = " + result[row][col]);
			if(result[row][col] <= mediaRGB){
				break;
			}
			referenciatam++;
		}
		
		System.out.println("referenciacolini = " + referenciacolini);
		
		//descobre a margem direita
		for (col = w-1; col > 0; col--) {
			if(result[row][col] >= mediaRGB){
				if(referenciacolfim == 0){
					referenciacolfim = col;
				}
			}
		}
		
		System.out.println("referenciacolfim = " + referenciacolfim);
		
		//nova média com corte
		for (row = (h/2)-50; row < (h/2)+50; row++) {
	         for (col = referenciacolini; col < referenciacolfim; col++) {
	            result[row][col] = image.getRGB(col, row);
	            if (result[row][col] < 0){
	            	result[row][col] = -result[row][col];
	            }	
	            //System.out.println(result[row][col]);
	        	soma = soma + result[row][col];
	        	//System.out.println("soma = " + soma);
	         }
	    }
		
		mediaRGB = (int) (soma/(h*w));
		System.out.println("soma = " + soma);
		System.out.println("mediaRGB = " + mediaRGB);
		System.out.println("referenciatam = " + referenciatam);
		
		//media da coluna
		for (col = 0; col < w; col++) {
	         for (row = (h/2)-20; row < (h/2)+20; row++) {
	        	 if(result[row][col] <= mediaRGB){
	 				branco++;
	        	 }
	        	 if(result[row][col] >= mediaRGB){
	 				preto++;
	        	 }
	         }
	         for (row = (h/2)-20; row < (h/2)+20; row++) {
	        	 //System.out.println(branco + " " + preto);
	        	 if(branco > preto){
		        	 for (row = (h/2)-20; row < (h/2)+20; row++){
		        		 //result[row][col] = 1;
		        		
		        	 }
		         }
		         if(branco < preto){
		        	 for (row = (h/2)-20; row < (h/2)+20; row++){
		        		 //result[row][col] = 13100587;
		        	 }
		         }
	         }
	         branco = 0;
	         preto = 0;
	    }
		
		
		//desviopadrão
		soma = 0;
		for (col = 0; col < w; col++) {
	         for (row = (h/2)-20; row < (h/2)+20; row++) {
                soma += Math.pow(mediaRGB - result[row][col], 2);
            }
        }
		desviopadrao = (int) Math.sqrt(soma / (h*w));
		System.out.println("desviopadrao = " + desviopadrao);
		System.out.println("mediaRGB = " + mediaRGB);
		
		
		//transformar em bits
		for (col = referenciacolini; col <= referenciacolfim; col++) {
			
			if(result[row][col] <= mediaRGB && result[row][col+1] > mediaRGB){
				teste = true;
			}
			if(result[row][col] >= mediaRGB && result[row][col+1] < mediaRGB){
				teste = true;
			}
			if(result[row][col] <= mediaRGB){
 				branco++;
 				System.out.println("result["+row+"]["+col+"] = " + result[row][col]);
 				if(teste == true){
 					System.out.println("branco = " + branco);
 					if(branco <= (referenciatam+(referenciatam/4)) && branco >= (referenciatam-(referenciatam/4))){
 						codigobinario.add("0");
 						branco = 0;
 	 	 				teste = false;
 					}else if(branco <= (2*referenciatam)+(referenciatam/4) && branco >= ((2*referenciatam)-(referenciatam/4))){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (3*referenciatam)+(referenciatam/4) && branco >= ((3*referenciatam)-(referenciatam/4))){
 						codigobinario.add("0");
 						codigobinario.add("0");
 						codigobinario.add("0");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (4*referenciatam)+(referenciatam/4) && branco >= ((4*referenciatam)-(referenciatam/4))){
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
 				System.out.println("result["+row+"]["+col+"] = " + result[row][col]);
 				if(teste == true){
 					System.out.println("preto = " + preto);
	 				if(preto <= referenciatam+(referenciatam/4) && preto >= referenciatam-(referenciatam/4)){
						codigobinario.add("1");
						preto = 0;
	 	 				teste = false;
					}else if(preto <= (2*referenciatam)+(referenciatam/4) && preto >= (2*referenciatam)-(referenciatam/4)){
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (3*referenciatam)+(referenciatam/4) && preto >= (3*referenciatam)-(referenciatam/4)){
						codigobinario.add("1");
						codigobinario.add("1");
						codigobinario.add("1");
						preto = 0;
						teste = false;
					}else if(preto <= (4*referenciatam)+(referenciatam/4) && preto >= (4*referenciatam)-(referenciatam/4)){
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
		
		String[] converte = new String[codigobinario.size()];
	    for (int i = 0; i < codigobinario.size(); i++) {
	    	converte[i] = codigobinario.get(i).toString();
	    	System.out.print(converte[i]);
	    }
			    
		//imprime a sequencia de binarios
		System.out.println("tamanho do array codigo binário = " + codigobinario.size());
		for(String s : codigobinario){
			   System.out.print(s);
		}
		System.out.println();
		//System.out.println("codigobinario = " + codigobinario);
		
		
		//printa a imagem
	    for (row = h/2; row < h/2 + 20; row++) {
	    	System.out.println();
	         for (col = referenciacolini; col < referenciacolfim; col++) {
	        	 if(result[row][col] <= mediaRGB){
	        		 System.out.print(" ");
	        		 //System.out.print("|" + result[row][col]);
	        	 } else {
	        	 	System.out.print("#");
	        	 	//System.out.print("|" + result[row][col]);
	             }
	         }
	    }	    
	}
}
