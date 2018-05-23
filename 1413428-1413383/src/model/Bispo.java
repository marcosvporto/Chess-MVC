package model;



public class Bispo extends Peca {

	public Bispo(char cor, int linha, int coluna){
		super(TipoPeca.bispo,cor,linha,coluna);
		
		
	}

	@Override
	public int[][] getMovimentos() {
		int [][]mov = new int[2][2];
		mov[0][0] = coluna;
		mov[0][1] = linha + 1;
		mov[1][0] = coluna;
		mov[1][1] = linha + 2;
		return mov;
	}
}
