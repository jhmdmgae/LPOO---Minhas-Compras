package codigobarras;
 
import java.awt.Color;  
import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.IOException;  
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;
import javax.swing.JLabel;

//import barcodereader.image.ImageSupport;

public class LerCodigo {

	private static int SENSIBILIDADE = 50;

	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read( new File("sample-1d-barcode1.jpg"));
	
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int[][] result = new int[h][w];
	    int pixel;
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
	
	    int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w); 
	    
	    System.out.println(w);
	    System.out.println(h);
	    
	    Color c = new Color(dataBuffInt[100]);
	 
	    //captura os pixels
		for (row = (h/2)-50; row < (h/2)+50; row++) {
	         for (col = 0; col < w; col++) {
	        	pixel =  image.getRGB(col, row);
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
		
		
		//descobre o inicio e o fim da coluna
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
		
		//descobre o tamanho fnal da barra para recorte
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
	        	pixel =  image.getRGB(col, row);
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
		
		
		//transformar em bits
		for (col = referenciacolini; col < referenciacolfim; col++) {
			
			if(result[row][col] <= mediaRGB + desviopadrao / SENSIBILIDADE && result[row][col+1] >= mediaRGB + desviopadrao / SENSIBILIDADE){
				teste = true;
			}
			if(result[row][col] >= mediaRGB + desviopadrao / SENSIBILIDADE && result[row][col+1] <= mediaRGB + desviopadrao / SENSIBILIDADE){
				teste = true;
			}
			
			if(result[row][col] <= mediaRGB + desviopadrao / SENSIBILIDADE){
 				branco++;
 				if(teste == true){
 					if(branco <= referenciatam+3 && branco >= referenciatam-3){
 						codigobinario.add("0");
 						branco = 0;
 	 	 				teste = false;
 					}else if(branco <= (2*referenciatam)+3 && branco >= (2*referenciatam)-3){
 						codigobinario.add("00");
 						branco = 0;
 						teste = false;
 					}else if(branco <= (3*referenciatam)+3 && branco >= (3*referenciatam)-3){
 						codigobinario.add("000");
 						branco = 0;
 						teste = false;
 					}
 	        	}
        	 } else {
 				preto++;
 				if(preto <= referenciatam+3 && preto >= referenciatam-3){
					codigobinario.add("1");
					preto = 0;
 	 				teste = false;
				}else if(preto <= (2*referenciatam)+3 && preto >= (2*referenciatam)-3){
					codigobinario.add("11");
					preto = 0;
					teste = false;
				}else if(preto <= (3*referenciatam)+3 && preto >= (3*referenciatam)-3){
					codigobinario.add("111");
					preto = 0;
					teste = false;
				}
        	 }
		}
		//imprime a sequencia de binarios
		for(String s : codigobinario){
			   System.out.print(s);
		}
		System.out.println();
		//System.out.println("codigobinario = " + codigobinario);
		
		
		//printa a imagem
	    for (row = h/2; row < h/2 + 20; row++) {
	    	System.out.println();
	         for (col = referenciacolini; col < referenciacolfim; col++) {
	        	 if(result[row][col] <= mediaRGB  + desviopadrao / SENSIBILIDADE){
	        		 System.out.print(" ");
	        		 //System.out.print("|" + result[row][col]);
	        	 } else {
	        	 	System.out.print("#");
	        	 	//System.out.print("|" + result[row][col]);
	             }
	         }
	    }	    
	}
	
	public static void printPixelARGB(int pixel) {
		
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);

	}
}
