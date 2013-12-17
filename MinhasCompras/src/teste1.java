
 
import java.awt.Color;  

import java.awt.image.BufferedImage;
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;
import javax.swing.JLabel;

public class teste1 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read( new File("300px-EAN-13-5901234123457.svg.png"));
		
		 System.out.println(image);
		//BufferedImage image = ImageIO.read(new File("barcode.jpg"));
	
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int[][] result = new int[h][w];
	    int pixel;
	
	    int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w); 
	    
	    System.out.println(w);
	    System.out.println(h);
	    
	    Color c = new Color(dataBuffInt[100]);
	 
	    
		for (int row = 0; row < h; row++) {
	         for (int col = 0; col < w; col++) {
	        	pixel =  image.getRGB(col, row);
	            result[row][col] = image.getRGB(col, row);
	        	
	         }
	    }

	    for (int row = 0; row < h; row++) {
	    	System.out.println();
	         for (int col = 0; col < w; col++) {
	        	 if(result[row][col] == -1){
	        		 System.out.print(" ");
	        	  //   printPixelARGB(result[row][col]);
	        		 //System.out.print();
	        	 }	 
	        	 if (result[row][col] != -1){
	        	 	System.out.print("#");
	        	 	//System.out.print("result[row][col]");
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