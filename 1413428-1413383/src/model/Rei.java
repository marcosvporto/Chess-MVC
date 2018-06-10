package model;

public class Rei extends Peca {
	
	public Rei(char cor){
		super(TipoPeca.rei,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {
		int [][]mov = new int[2][10];
				
				mov[0][0] = this.linha - 1;
				mov[1][0] = this.coluna -1;
				
				mov[0][1] = this.linha - 1;
				mov[1][1] = this.coluna;
				
				mov[0][2] = this.linha - 1;
				mov[1][2] = this.coluna + 1;
				
				mov[0][3] = this.linha;
				mov[1][3] = this.coluna - 1;
				
				
				mov[0][4] = this.linha;
				mov[1][4] = this.coluna + 1;
				
				mov[0][5] = this.linha + 1;
				mov[1][5] = this.coluna - 1;
				
				mov[0][6] = this.linha + 1;
				mov[1][6] = this.coluna;
				
				mov[0][7] = this.linha + 1;
				mov[1][7] = this.coluna + 1;
				
				mov[0][8] = this.linha;
				mov[1][8] = this.coluna - 2;
				
				mov[0][9] = this.linha;
				mov[1][9] = this.coluna + 2;
		return mov;
	}

}
