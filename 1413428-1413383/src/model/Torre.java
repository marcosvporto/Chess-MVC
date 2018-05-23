package model;

public class Torre extends Peca{
	
	public Torre(char cor, int linha, int coluna){
		super(TipoPeca.torre,cor,linha,coluna);
		
		
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
