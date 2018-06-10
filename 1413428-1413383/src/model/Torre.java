package model;

public class Torre extends Peca{
	
	public Torre(char cor, int linha, int coluna){
		super(TipoPeca.torre,cor,linha,coluna);
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		int TAM = 20;
		int [][]mov = new int[2][TAM];
		int tot = 0;

		// casas acima da torre
		for(int i=linhaCasa-1 ; i>=0 ; i--) {
			mov[0][tot] = i;
			mov[1][tot] = colunaCasa;
			tot++;
		}

		// casas abaixo da torre
		for(int i=linhaCasa+1 ; i<=7 ; i++) {
			mov[0][tot] = i;
			mov[1][tot] = colunaCasa;
			tot++;
		}
		
		// casas a esquerda da torre
		for(int j=colunaCasa-1 ; j>=0 ; j--) {
			mov[0][tot] = linhaCasa;
			mov[1][tot] = j;
			tot++;
		}
		
		// casas a direita da torre 
		for(int j=colunaCasa+1 ; j<=7 ; j++) {
			mov[0][tot] = linhaCasa;
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
