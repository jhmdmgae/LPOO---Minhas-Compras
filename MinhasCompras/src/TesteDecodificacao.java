

public class TesteDecodificacao {
	
	public static void main(String[] args) {
		
		//String[] codigoDeBarras = {"7","8","9","9","8","7","6","5","4","3","2","1","5"};
//		String[] novoCodigoDeBarras = { "0110111",
//										"0010111",
//										"0001011",
//										"0001001",
//										"0111011",
//										"0000101",
//										"1001110",
//										"1011100",
//										"1000010",
//										"1101100",
//										"1100110",
//										"1001110"};//EAN-13 fictício 7 899876 543215 

		String[] novoCodigoDeBarras = { "0110111",
				"0010111",
				"0011001",
				"0011011",
				"0111101",
				"0011101",				
				"1001110",
				"1010000",
				"1000100",
				"1001000",
				"1110100",
				"1001110"};//EAN-13 fictício 7891234567895 
		
		DecodificarCodigoBarras q = new DecodificarCodigoBarras(novoCodigoDeBarras);
		
		System.out.println(q.decodificaPadraoBits());
	}
}
