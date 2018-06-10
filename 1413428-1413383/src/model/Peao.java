package model;

public class Peao extends Peca {
	
	public Peao(char cor){
		super(TipoPeca.peao,cor);
		
		
	}

	@Override
	public int[][] getMovimentos() {
		int [][]mov = new int[2][4];
		
		
		//peca preta
 		if(cor == 'p') { 
 			if(qtdMovimentos==0) {


 				mov[0][0] = this.linha + 2;
 				mov[1][0] = this.coluna;
 				
 				mov[0][1] = this.linha + 1;
 				mov[1][1] = this.coluna;
 				
 				mov[0][2] = this.linha + 1;
 				mov[1][2] = this.coluna + 1;
 				
 				mov[0][3] = this.linha + 1;
 				mov[1][3] = this.coluna - 1;
 				return mov;
 			} else {


 				mov[0][0] = this.linha + 1;
 				mov[1][0] = this.coluna;
				
				
				mov[0][1] = this.linha  + 1;
				mov[1][1] = this.coluna + 1;
				
				mov[0][2] = this.linha  + 1;
				mov[1][2] = this.coluna - 1;
				//repete movimento
				mov[0][3] = this.linha + 1;
 				mov[1][3] = this.coluna;
				
				
 				return mov;
 			}
 		
 		//peca branca
 		} else {
 			if(qtdMovimentos==0) {


 				mov[0][0] = this.linha - 2;
 				mov[1][0] = this.coluna;
 				
 				mov[0][1] = this.linha - 1;
 				mov[1][1] = this.coluna;
 				
 				mov[0][2] = this.linha - 1;
 				mov[1][2] = this.coluna - 1;
 				
 				mov[0][3] = this.linha - 1;
 				mov[1][3] = this.coluna + 1;
 				
 				return mov;
 			} else {


 				mov[0][0] = this.linha - 1;
 				mov[1][0] = this.coluna;
				
 				mov[0][1] = this.linha - 1;
 				mov[1][1] = this.coluna - 1;
				//repete movimento
				mov[0][2] = this.linha - 1;
				mov[1][2] = this.coluna + 1;
				
				mov[0][3] = this.linha - 1;
 				mov[1][3] = this.coluna;
 				
 				return mov;
 			}
 		}
		
		
	}
	

}
