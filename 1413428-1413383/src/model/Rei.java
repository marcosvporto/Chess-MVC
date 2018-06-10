package model;

public class Rei extends Peca {
	
	public Rei(char cor, int linha, int coluna){
		super(TipoPeca.rei,cor,linha,coluna);
	}

	@Override
	public int[][] getMovimentos(int linhaCasa, int colunaCasa, char cor) {
		int [][]mov = new int[2][8];
		
		mov[0][0] = linhaCasa - 1;
		mov[1][0] = colunaCasa -1;
		
		mov[0][1] = linhaCasa - 1;
		mov[1][1] = colunaCasa;
		
		mov[0][2] = linhaCasa - 1;
		mov[1][2] = colunaCasa + 1;
		
		mov[0][3] = linhaCasa;
		mov[1][3] = colunaCasa - 1;
		
		mov[0][4] = linhaCasa;
		mov[1][4] = colunaCasa + 1;
		
		mov[0][5] = linhaCasa + 1;
		mov[1][5] = colunaCasa - 1;
		
		mov[0][6] = linhaCasa + 1;
		mov[1][6] = colunaCasa;
		
		mov[0][7] = linhaCasa + 1;
		mov[1][7] = colunaCasa + 1;
		
		return mov;
	}

}
