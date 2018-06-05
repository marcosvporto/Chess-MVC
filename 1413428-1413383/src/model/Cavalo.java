package model;

public class Cavalo extends Peca {

	public Cavalo(char cor, int linha, int coluna){
		super(TipoPeca.cavalo,cor,linha,coluna);
		
		
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		int [][]mov = new int[8][8];
		mov[0][0] = colunaCasa;
		mov[0][1] = linhaCasa + 1;
		mov[1][0] = colunaCasa;
		mov[1][1] = linhaCasa + 2;
		return mov;
	}
}
