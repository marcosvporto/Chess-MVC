package model;

public class Cavalo extends Peca {

	public Cavalo(char cor){
		super(TipoPeca.cavalo,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {

		int [][]mov = new int[2][8];
				
				mov[0][0] = this.linha - 2;
				mov[1][0] = this.coluna - 1;
				
				mov[0][1] = this.linha - 2;
				mov[1][1] = this.coluna + 1;
				
				mov[0][2] = this.linha - 1;
				mov[1][2] = this.coluna - 2;
				
				mov[0][3] = this.linha - 1;
				mov[1][3] = this.coluna + 2;
				
				mov[0][4] = this.linha + 1;
				mov[1][4] = this.coluna - 2;
				
				mov[0][5] = this.linha + 1;
				mov[1][5] = this.coluna + 2;
				
				mov[0][6] = this.linha + 2;
				mov[1][6] = this.coluna - 1;
				
				mov[0][7] = this.linha + 2;
				mov[1][7] = this.coluna + 1;
		return mov;
	}
}
