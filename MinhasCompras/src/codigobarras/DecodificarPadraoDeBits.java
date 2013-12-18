package codigobarras;
//recebe 12 padroes bits (sem o 1 que nao e codificado em bits)
//calcula o padrao AB usado nos 6 primeiros padroes bits
//com padrao AB decodifica o 1 digito
//decodifica o 2a7 digito
//decodifica o 8a12 digito
//retorna padrao bits
public class DecodificarPadraoDeBits {
	private static final short QUANT_PADROES_BITS = 10;
	private static final short QUANT_PADROES_AB = 6;
	
	private static String[] codificacaoEsquerdaA = new String[QUANT_PADROES_BITS];
	private static String[] codificacaoEsquerdaB = new String[QUANT_PADROES_BITS];
	private static String[] codificacaoDireita = new String[QUANT_PADROES_BITS];
	private static String[] codificacaoPadraoAB = new String[QUANT_PADROES_BITS];
	
	private String[] padraoAB_Decodificado = new String[QUANT_PADROES_AB];
	private String padraoBitsDecodificado = new String();
	private String[] padroesBitsRecebidoSemDivisao = new String[95];//mudar para char //sem pardrao 101 de inicio e de fim e o do meio 01010
	private String padroesBitsRecebido;//com pardrao 101 de inicio e de fim e o do meio 01010
	
	public DecodificarPadraoDeBits(String padroesBitsRecebido){
		this.padroesBitsRecebido = padroesBitsRecebido;
		configuraCodificaEsquerdaA();
		configuraCodificaEsquerdaB();
		configuraCodificaDireita();
		configurametodoAouB();		
	}
	
	private void configuraCodificaEsquerdaA(){
	codificacaoEsquerdaA[0]="0001101";
	codificacaoEsquerdaA[1]="0011001";
	codificacaoEsquerdaA[2]="0010011";
	codificacaoEsquerdaA[3]="0111101";
	codificacaoEsquerdaA[4]="0100011";
	codificacaoEsquerdaA[5]="0110001";
	codificacaoEsquerdaA[6]="0101111";
	codificacaoEsquerdaA[7]="0111011";
	codificacaoEsquerdaA[8]="0110111";
	codificacaoEsquerdaA[9]="0001011";
	}	
	private void configuraCodificaEsquerdaB(){
	codificacaoEsquerdaB[0]="0100111";	
	codificacaoEsquerdaB[1]="0110011";
	codificacaoEsquerdaB[2]="0011011";
	codificacaoEsquerdaB[3]="0100001";
	codificacaoEsquerdaB[4]="0011101";
	codificacaoEsquerdaB[5]="0111001";
	codificacaoEsquerdaB[6]="0000101";
	codificacaoEsquerdaB[7]="0010001";
	codificacaoEsquerdaB[8]="0001001";
	codificacaoEsquerdaB[9]="0010111";
	}
	private void configuraCodificaDireita(){
	codificacaoDireita[0]="1110010";
	codificacaoDireita[1]="1100110";
	codificacaoDireita[2]="1101100";
	codificacaoDireita[3]="1000010";
	codificacaoDireita[4]="1011100";
	codificacaoDireita[5]="1001110";
	codificacaoDireita[6]="1010000";
	codificacaoDireita[7]="1000100";
	codificacaoDireita[8]="1001000";
	codificacaoDireita[9]="1110100";
	}
	private void configurametodoAouB(){
	codificacaoPadraoAB[0]="AAAAAA";
	codificacaoPadraoAB[1]="AABABB";
	codificacaoPadraoAB[2]="AABBAB";
	codificacaoPadraoAB[3]="AABBBA";
	codificacaoPadraoAB[4]="ABAABB";
	codificacaoPadraoAB[5]="ABBAAB";
	codificacaoPadraoAB[6]="ABBBAA";
	codificacaoPadraoAB[7]="ABABAB";
	codificacaoPadraoAB[8]="ABABBA";
	codificacaoPadraoAB[9]="ABBABA";
	}
	
	public String decodificaPadraoBits(){
		excluiPadroesBitsDeDivisao();
		decodificaAB();
		decodificaDigito1();
		decodificaDigitos2a7();
		decodificaDigitos8a13();
		
		return padraoBitsDecodificado;		
	}	
	
	public void excluiPadroesBitsDeDivisao(){
		
		char[]	auxiliarArrayChar = padroesBitsRecebido.toCharArray();
		String[] auxiliarArrayString = new String[95];
		
		for (int i = 0; i < auxiliarArrayChar.length; i++) {
			auxiliarArrayString[i] = String.valueOf(auxiliarArrayChar[i]);
		}	
		auxiliarArrayString[0] = "*";
		auxiliarArrayString[1] = "*";
		auxiliarArrayString[2] = "*";		
		auxiliarArrayString[45] = "*";
		auxiliarArrayString[46] = "*";
		auxiliarArrayString[47] = "*";
		auxiliarArrayString[48] = "*";
		auxiliarArrayString[48] = "*";
		auxiliarArrayString[49] = "*";
		auxiliarArrayString[92] = "*";
		auxiliarArrayString[93] = "*";
		auxiliarArrayString[94] = "*";
		
		for (int i = 0, indice = 0; i < auxiliarArrayString.length; i++) {
			
			if(auxiliarArrayString[i] != "*"){
			padroesBitsRecebidoSemDivisao[indice] = auxiliarArrayString[i];
			indice++;
			}
		}	
		
		String[] auxiliar = new String[12];
		
		for (int i = 0; i < auxiliar.length; i++) {
			auxiliar[i]= "";
		}
		for (int i = 0, indice = 0,contador = 1; i < 84; i++) {//agrupa de 7 em 7
//			System.out.println(" i" + i);
//			System.out.println(contador);
//			System.out.println("indice" + indice);
			
			if(contador <=7){
				auxiliar[indice] += padroesBitsRecebidoSemDivisao[i];
				}
			if(contador == 7){contador =0; indice++; }			
			contador++;			
		}		
		
		padroesBitsRecebidoSemDivisao = new String[12];
		for (int i = 0; i < 12; i++) {
			padroesBitsRecebidoSemDivisao[i]=auxiliar[i];
		}
		
	}
	
	private void decodificaAB(){
		
		for (int indicePadraoBits = 0; indicePadraoBits < (padroesBitsRecebidoSemDivisao.length)/2; indicePadraoBits++) {
			for (int indiceCodificacaoEsquerda = 0; indiceCodificacaoEsquerda < codificacaoEsquerdaA.length; indiceCodificacaoEsquerda++) {			
				
				if(padroesBitsRecebidoSemDivisao[indicePadraoBits].equals(codificacaoEsquerdaA[indiceCodificacaoEsquerda])){				
					padraoAB_Decodificado[indicePadraoBits] = "A";//A se sequencia bits em codificaEsquerdaA 			
				
				}else if(padroesBitsRecebidoSemDivisao[indicePadraoBits].equals(codificacaoEsquerdaB[indiceCodificacaoEsquerda])){
					padraoAB_Decodificado[indicePadraoBits] = "B";
				}
			}			
		}	
	}
	
	private void decodificaDigito1(){
		String padraoAB_DecodificadoString= new String();
		
		for (int i = 0; i < padraoAB_Decodificado.length; i++){//converte array em string  
			padraoAB_DecodificadoString += padraoAB_Decodificado[i];    
        } 		
		
		for (int i = 0; i < codificacaoPadraoAB.length; i++) {
			if(codificacaoPadraoAB[i].equals(padraoAB_DecodificadoString)){
				padraoBitsDecodificado = String.valueOf(i);
				break;
			}
		}
	}
	
	private void decodificaDigitos2a7(){
		
		for (int indicePadraoAB_Decodificado = 0; indicePadraoAB_Decodificado < padroesBitsRecebidoSemDivisao.length/2; indicePadraoAB_Decodificado++) {
			if(padraoAB_Decodificado[indicePadraoAB_Decodificado]=="A"){
				
				for (int indiceCodificacaoEsquerdaA = 0; indiceCodificacaoEsquerdaA < codificacaoEsquerdaA.length; indiceCodificacaoEsquerdaA++) {
					if(padroesBitsRecebidoSemDivisao[indicePadraoAB_Decodificado].equals(codificacaoEsquerdaA[indiceCodificacaoEsquerdaA])){
						padraoBitsDecodificado += indiceCodificacaoEsquerdaA;
					}
				}
			}else{
				for (int indiceCodificacaoEsquerdaB = 0; indiceCodificacaoEsquerdaB < codificacaoEsquerdaB.length; indiceCodificacaoEsquerdaB++) {
					if(padroesBitsRecebidoSemDivisao[indicePadraoAB_Decodificado].equals(codificacaoEsquerdaB[indiceCodificacaoEsquerdaB])){
						padraoBitsDecodificado += indiceCodificacaoEsquerdaB;
					}
				}
			}
		}		
	}
	
	private void decodificaDigitos8a13(){
		
		for (int indicePadroesBitsRecebido=0; indicePadroesBitsRecebido < padroesBitsRecebidoSemDivisao.length; indicePadroesBitsRecebido++) {
			for (int indiceCodificacaoDireita=0; indiceCodificacaoDireita < codificacaoDireita.length; indiceCodificacaoDireita++) {
				if(padroesBitsRecebidoSemDivisao[indicePadroesBitsRecebido].equals(codificacaoDireita[indiceCodificacaoDireita])){
					padraoBitsDecodificado = padraoBitsDecodificado + String.valueOf(indiceCodificacaoDireita);
					break;
			}	
			}
		}		
	}	
}
	
