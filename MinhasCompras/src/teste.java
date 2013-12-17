import java.awt.image.*;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;  


public class teste {  	
	
	public static void main(String[] args) throws IOException {
	
	    BufferedImage imagemLida = ImageIO.read(new File("images1.jpg"));
	
	    int larguraImagem = imagemLida.getWidth();
	    int alturaImagem = imagemLida.getHeight();
	    
	    int[][] result = new int[alturaImagem][larguraImagem];
	    int pixel;
	    
	  //O método getRGB() retorna um array de int que representa os valores RGB de cada ponto da imagem.
	    int[] dataBuffInt = imagemLida.getRGB(0, 0, larguraImagem, alturaImagem, null, 0, larguraImagem); 
	    
	    System.out.println(larguraImagem);
	    System.out.println(alturaImagem);	    
	 
//	    for (int row = 0; row < alturaImagem*larguraImagem; row++) {	         
//	        	 System.out.println(dataBuffInt[row]);	        
//	    }
	    
		for (int row = 0; row < alturaImagem; row++) {
	         for (int col = 0; col < larguraImagem; col++) {
	        	//pixel =  imagemLida.getRGB(col, row);
	            result[row][col] = imagemLida.getRGB(col, row); 
	         }
	    }
		
		for (int row = 0; row < alturaImagem; row++) {
	         for (int col = 0; col < larguraImagem; col++) {
	        	 
	        	 if(result[row][col]== -1){
	        	 System.out.print(' '); 
	        	 }else{
	        		 System.out.print('*');
	        	 		}   
	         }
	         System.out.println("\n");
	    }

//	    for (int row = 20; row < 22; row++) {
//	    	System.out.println();
//	         for (int col = 0; col < larguraImagem; col++) {
//	        	 //if(result[row][col] == -1){
//	        		 //System.out.print("'");
//	        	    // printPixelARGB(pixel);
//	        		 //System.out.print();
//	        	 //}	 
//	        	 //if (result[row][col] != -1){
//	        	 	//System.out.print("#");
//	        	 	//System.out.print("result[row][col]");
//	        	 	//System.out.println(c.getRed());   // = (dataBuffInt[100] >> 16) & 0xFF
//	            	//System.out.println(c.getGreen()); // = (dataBuffInt[100] >> 8)  & 0xFF
//	            	//System.out.println(c.getBlue());  // = (dataBuffInt[100] >> 0)  & 0xFF
//	            	//System.out.println(c.getAlpha()); // = (dataBuffInt[100] >> 24) & 0xFF
//	             //}
//	         }
//	    }
    	
	}
	
//	public static void printPixelARGB(int pixel) {
//        int alpha = (pixel >> 24) & 0xff;
//        int red = (pixel >> 16) & 0xff;
//        int green = (pixel >> 8) & 0xff;
//        int blue = (pixel) & 0xff;
//        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
//      }

}