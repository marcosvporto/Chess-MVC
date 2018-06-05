package model;

public class Torre extends Peca{
	
	public Torre(char cor, int linha, int coluna){
		super(TipoPeca.torre,cor,linha,coluna);
		
		
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		int [][]mov = new int[2][2];
		mov[0][0] = colunaCasa;
		mov[0][1] = linhaCasa + 1;
		mov[1][0] = colunaCasa;
		mov[1][1] = linhaCasa + 2;
		return mov;
	}

}
