package controller;


import view.ChessPanel;
import model.*;

public class Partida {
	
	
	static Tabuleiro t;
	
	public Partida() {
		
		t = Tabuleiro.getTabuleiro();
		
	}
	
	public static void comecarPartida(ChessPanel p) {
		Casa[][] c;

		c = t.getMatrizCasas();
		for(int i = 0;i<8;i++) {
			switch(i) {
			case 0: c[i][0].setPeca(new Torre(  'p'));break;
			case 1: c[i][0].setPeca(new Cavalo( 'p'));break;
			case 2: c[i][0].setPeca(new Bispo(  'p'));break;
			case 3: c[i][0].setPeca(new Dama(   'p'));break;
			case 4: c[i][0].setPeca(new Rei(    'p'));break;
			case 5: c[i][0].setPeca(new Bispo(  'p'));break;
			case 6: c[i][0].setPeca(new Cavalo( 'p'));break;
			case 7: c[i][0].setPeca(new Torre(  'p'));break;
			
			}
		}
		for(int i = 0;i<8;i++) {
			switch(i) {
			case 0: c[i][7].setPeca(new Torre(  'b'));break;
			case 1: c[i][7].setPeca(new Cavalo( 'b'));break;
			case 2: c[i][7].setPeca(new Bispo(  'b'));break;
			case 3: c[i][7].setPeca(new Dama(    'b'));break;
			case 4: c[i][7].setPeca(new Rei(   'b'));break;
			case 5: c[i][7].setPeca(new Bispo(  'b'));break;
			case 6: c[i][7].setPeca(new Cavalo( 'b'));break;
			case 7: c[i][7].setPeca(new Torre(  'b'));break;
			}
		}
		for(int i=0;i<8;i++) {
			c[i][1].setPeca(new Peao('p'));
			c[i][6].setPeca(new Peao('b'));
		}

		
		
		
	}
	
}
