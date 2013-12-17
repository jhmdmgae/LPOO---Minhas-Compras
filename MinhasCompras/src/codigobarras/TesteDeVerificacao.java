package codigobarras;

public class TesteDeVerificacao {

	public static void main(String[] args) {
		//int[] codigo13 = { 7,8,9,1,2,3,4,5,6,7,8,9,5 };	//5	
		int[] codigo13 = { 0,2,2,3,3,3,4,5,7,7,8,9,8}; //9
		
		ValidaEAN_13 codigoBarras = new ValidaEAN_13(codigo13);
		if(codigoBarras.validar()){
			System.out.println("Codigo valido");
			}else{System.out.println("Codigo invalido");}
			
		
//		System.out.println("verificador Correto: " + verificadorCorreto);
//		System.out.println("digitoVerificador Calculado: " + digitoVerificador);

	}

}
