package model;

public class Torre extends Peca{
	
	public Torre(char cor){
		super(TipoPeca.torre,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {

		int TAM = 20;
				int [][]mov = new int[2][TAM];
				int tot = 0;
		
				// casas acima da torre
				for(int i=this.linha-1 ; i>=0 ; i--) {
					mov[0][tot] = i;
					mov[1][tot] = this.coluna;
					tot++;
				}
		
				// casas abaixo da torre
				for(int i=this.linha+1 ; i<=7 ; i++) {
					mov[0][tot] = i;
					mov[1][tot] = this.coluna;
					tot++;
				}
				
				// casas a esquerda da torre
				for(int j=this.coluna-1 ; j>=0 ; j--) {
					mov[0][tot] = this.linha;
					mov[1][tot] = j;
					tot++;
				}
				
				// casas a direita da torre 
				for(int j=this.coluna+1 ; j<=7 ; j++) {
					mov[0][tot] = this.linha;
					mov[1][tot] = j;
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
