package model;



public class Bispo extends Peca {

	
	public Bispo(char cor){
		super(TipoPeca.bispo,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {
		int TAM = 20;
		int [][]mov = new int[2][TAM];
		int tot = 0;
		
		// casas a esquerda superior do bispo
		
		int i = this.linha - 1;
		for(int j = this.coluna - 1; j >= 0; j-- ) {
			mov[0][tot] = i;
			mov[1][tot] = j;
			
			i--;
			tot++;
		}
		
		//casas a esquerda inferior do bispo
		
		i = this.linha + 1;
		for(int j=this.coluna - 1; j>=0 ; j--) {
			
						mov[0][tot] = i;
						mov[1][tot] = j;
						
						i++;
						tot++;
					}
					
					// casas a direita inferior do bispo
					i = this.linha + 1;
					for(int j=this.coluna + 1; j<=7 ; j++) {
			
						mov[0][tot] = i;
						mov[1][tot] = j;
								
					    i++;
						tot++;
					}
					
					// casas a direita superior do bispo
					i = this.linha - 1;
					for(int j=this.coluna + 1; j<=7 ; j++) {
			
						mov[0][tot] = i;
						mov[1][tot] = j;
										
						i--;
						tot++;
					}
					
					// ANULA AS CASAS RESTANTES DA MATRIZ
					for(int j=tot ; j<TAM ; j++) {
						mov[0][tot] = -1;
						mov[1][tot] = -1;
						tot++;
					}
		return mov;
	}
}
