package model;

public class Peao extends Peca {
	
	public Peao(char cor, int linha, int coluna){
		super(TipoPeca.peao,cor,linha,coluna);
		
		
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		
		//peca preta
		if(cor == 'p') { 
			if(qtdMovimentos==0) {
				int [][]mov = new int[2][2];
				mov[0][0] = linhaCasa + 2;
				mov[1][0] = colunaCasa;
				mov[0][1] = linhaCasa + 1;
				mov[1][1] = colunaCasa;
				return mov;
			} else {
				int [][]mov = new int[1][1];
				mov[0][0] = linhaCasa + 1;
				mov[1][0] = colunaCasa;
				return mov;
			}
		
		//peca branca
		} else {
			if(qtdMovimentos==0) {
				int [][]mov = new int[2][2];
				mov[0][0] = linhaCasa - 2;
				mov[1][0] = colunaCasa;
				mov[0][1] = linhaCasa - 1;
				mov[1][1] = colunaCasa;
				return mov;
			} else {
				int [][]mov = new int[1][1];
				mov[0][0] = linhaCasa - 1;
				mov[1][0] = colunaCasa;
				return mov;
			}
		}
		
		
	}
	

}
