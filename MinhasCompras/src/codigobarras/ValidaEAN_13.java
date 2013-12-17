package codigobarras;
/*1-recebe numero codigo barras(13digitos)
 *2-calcula digito verificador a partir 12 primeiros digitos
 *3-compara com verificador informado
 */
public class ValidaEAN_13 {
	
	private int[] codigoComVerificador;
	private int soma = 0;//soma os impares*FATOR aos pares
	private static final int FATOR = 3;
	private int verificadorInformado;	
	private int verificadorCalculado;	
		
	public ValidaEAN_13(int[] codigoComVerificador){
		this.codigoComVerificador = codigoComVerificador;
		this.verificadorInformado = codigoComVerificador[12];
		}

	public boolean validar(){
		
		for (int digito = 0; digito < (codigoComVerificador.length-1); digito++) {
			if((digito%2)!=0){
				soma += codigoComVerificador[digito]*FATOR;
				}else{
					soma += codigoComVerificador[digito];
					}		
		}
		
		verificadorCalculado = ((1000 - soma) % 10);
		
		if(verificadorCalculado == verificadorInformado){
			return true;
		}else{
			return false;
			}
	}
		
}
