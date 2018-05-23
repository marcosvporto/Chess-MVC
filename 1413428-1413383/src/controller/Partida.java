package controller;


import view.ChessPanel;
import model.*;

public class Partida {
	
	public static void comecarPartida(ChessPanel p,int top,int left,int dimCasa) {
		Casa[][] c;
		Tabuleiro t = new Tabuleiro(top,left,dimCasa);
		c = t.getMatrizCasas();
		for(int i = 0;i<8;i++) {
			switch(i) {
			case 0: c[i][0].setPeca(new Torre( 'p', 0, i));break;
			case 1: c[i][0].setPeca(new Bispo( 'p', 0, i));break;
			case 2: c[i][0].setPeca(new Cavalo('p', 0, i));break;
			case 3: c[i][0].setPeca(new Rei(   'p', 0, i));break;
			case 4: c[i][0].setPeca(new Dama(  'p', 0, i));break;
			case 5: c[i][0].setPeca(new Cavalo('p', 0, i));break;
			case 6: c[i][0].setPeca(new Bispo( 'p', 0, i));break;
			case 7: c[i][0].setPeca(new Torre( 'p', 0, i));break;
			
			}
		}
		for(int i = 0;i<8;i++) {
			switch(i) {
			case 0: c[i][7].setPeca(new Torre( 'b', 7 , i));break;
			case 1: c[i][7].setPeca(new Bispo( 'b', 7 , i));break;
			case 2: c[i][7].setPeca(new Cavalo('b', 7 , i));break;
			case 3: c[i][7].setPeca(new Dama(  'b', 7 , i));break;
			case 4: c[i][7].setPeca(new Rei(   'b', 7 , i));break;
			case 5: c[i][7].setPeca(new Cavalo('b', 7 , i));break;
			case 6: c[i][7].setPeca(new Bispo( 'b', 7 , i));break;
			case 7: c[i][7].setPeca(new Torre( 'b', 7 , i));break;
			}
		}
		for(int i=0;i<8;i++) {
			c[i][1].setPeca(new Peao('p',1,i));
			c[i][6].setPeca(new Peao('b',6,i));
		}
		p.setTabuleiro(t);
		
		
		
	}
	
	
			
			
			
		
	

}
