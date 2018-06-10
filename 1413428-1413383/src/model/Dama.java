package model;

public class Dama extends Peca {
	
	public Dama(char cor){
		super(TipoPeca.dama,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {
		int TAM = 35;
				int [][]mov = new int[2][TAM];
				int tot = 0;
				
				//mistura das possiveis movimentacoes da TORRE + BISPO
				
				//BISPO
						// casas a esquerda superior do bispo
						int i = this.linha - 1;
						for(int j=this.coluna-1 ; j>=0 ; j--) {
		
							mov[0][tot] = i;
							mov[1][tot] = j;
							
							i--;
							tot++;
						}
						
						// casas a esquerda inferior do bispo
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
						
				//TORRE
						// casas acima da torre
						for(int k=this.linha-1 ; k>=0 ; k--) {
							mov[0][tot] = k;
							mov[1][tot] = this.coluna;
							tot++;
						}
		
						// casas abaixo da torre
						for(int k=this.linha+1 ; k<=7 ; k++) {
							mov[0][tot] = k;
							mov[1][tot] = this.coluna;
							tot++;
						}
						
						// casas a esquerda da torre
						for(int k=this.coluna-1 ; k>=0 ; k--) {
							mov[0][tot] = this.linha;
							mov[1][tot] = k;
							tot++;
						}
						
						// casas a direita da torre 
						for(int k=this.coluna+1 ; k<=7 ; k++) {
							mov[0][tot] = this.linha;
							mov[1][tot] = k;
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
