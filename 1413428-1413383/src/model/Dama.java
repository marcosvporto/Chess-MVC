package model;

public class Dama extends Peca {
	
	public Dama(char cor, int linha, int coluna){
		super(TipoPeca.dama,cor,linha,coluna);
		
		
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		int TAM = 35;
		int [][]mov = new int[2][TAM];
		int tot = 0;
		
		//mistura das possiveis movimentacoes da TORRE + BISPO
		
		//BISPO
				// casas a esquerda superior do bispo
				int i = linhaCasa - 1;
				for(int j=colunaCasa-1 ; j>=0 ; j--) {

					mov[0][tot] = i;
					mov[1][tot] = j;
					
					i--;
					tot++;
				}
				
				// casas a esquerda inferior do bispo
				i = linhaCasa + 1;
				for(int j=colunaCasa - 1; j>=0 ; j--) {

					mov[0][tot] = i;
					mov[1][tot] = j;
					
					i++;
					tot++;
				}
				
				// casas a direita inferior do bispo
				i = linhaCasa + 1;
				for(int j=colunaCasa + 1; j<=7 ; j++) {

					mov[0][tot] = i;
					mov[1][tot] = j;
							
				    i++;
					tot++;
				}
				
				// casas a direita superior do bispo
				i = linhaCasa - 1;
				for(int j=colunaCasa + 1; j<=7 ; j++) {

					mov[0][tot] = i;
					mov[1][tot] = j;
									
					i--;
					tot++;
				}
				
		//TORRE
				// casas acima da torre
				for(int k=linhaCasa-1 ; k>=0 ; k--) {
					mov[0][tot] = k;
					mov[1][tot] = colunaCasa;
					tot++;
				}

				// casas abaixo da torre
				for(int k=linhaCasa+1 ; k<=7 ; k++) {
					mov[0][tot] = k;
					mov[1][tot] = colunaCasa;
					tot++;
				}
				
				// casas a esquerda da torre
				for(int k=colunaCasa-1 ; k>=0 ; k--) {
					mov[0][tot] = linhaCasa;
					mov[1][tot] = k;
					tot++;
				}
				
				// casas a direita da torre 
				for(int k=colunaCasa+1 ; k<=7 ; k++) {
					mov[0][tot] = linhaCasa;
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
