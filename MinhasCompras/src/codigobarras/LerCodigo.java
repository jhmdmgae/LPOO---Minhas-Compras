package codigobarras;
 
import java.awt.Color;  
import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.IOException;  

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;
import javax.swing.JLabel;

//import barcodereader.image.ImageSupport;

public class LerCodigo {

	public static void main(String[] args) throws IOException {
		
		//BufferedImage image = ImageIO.read( new File("300px-EAN-13-5901234123457.svg.png"));
		BufferedImage image = ImageIO.read( new File("porta-toalha-codigo-de-barras.jpg"));
		
		 System.out.println(image);
	
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
	            //System.out.println(result[row][col]);
	        	soma = soma + result[row][col];
	        	//System.out.println("soma = " + soma);
	         }
	    }
		
		int mediaRGB = (int) (soma/(h*w));
		System.out.println("soma = " + soma);
		System.out.println("mediaRGB = " + mediaRGB);
		
		
		//descobre o inicio e o tamanho da coluna
		row = 140;
		for (col = 0; col < w; col++) {
			if(result[row][col] >= mediaRGB){
				if(referenciacolini == 0){
					referenciacolini = col;
				}
			}
		}
		
		System.out.println("referenciacolini = " + referenciacolini);
		
		for (col = w-1; col > 0; col--) {
			if(result[row][col] >= mediaRGB){
				if(referenciacolfim == 0){
					referenciacolfim = col;
				}
			}
		}
		
		System.out.println("referenciacolfim = " + referenciacolfim);
		
		//nova média
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
		
		
		for (col = referenciacolini; col < w; col++) {
			referenciatam = referenciatam + 1;
			if(result[row][col] <= mediaRGB){
				break;
			}
		}
		
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
		
		//printa a imagem
	    for (row = 140; row < 141; row++) {
	    	System.out.println();
	         for (col = referenciacolini + 1; col < referenciacolfim; col++) {
	        	 if(result[row][col] <= mediaRGB){
	        		 System.out.print(" ");
	        		 //System.out.print("|" + result[row][col]);
	        	 }	 
	        	 if (result[row][col] >= mediaRGB){
	        	 	System.out.print("#");
	        	 	//System.out.print("|" + result[row][col]);
	        	 	//System.out.println(c.getRed());   // = (dataBuffInt[100] >> 16) & 0xFF
	            	//System.out.println(c.getGreen()); // = (dataBuffInt[100] >> 8)  & 0xFF
	            	//System.out.println(c.getBlue());  // = (dataBuffInt[100] >> 0)  & 0xFF
	            	//System.out.println(c.getAlpha()); // = (dataBuffInt[100] >> 24) & 0xFF
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
