package controller;


import view.ChessPanel;
import model.*;

public class Partida {
	
	
	public static Tabuleiro t;
	
	public Partida(int top,int left,int dimCasa) {
		
		t = new Tabuleiro(top,left,dimCasa);
		
	}
	
	public static void comecarPartida(ChessPanel p) {
		p.setTabuleiro(t);
	}
	
	public static void restaurarPartida(ChessPanel p) {
		
	}
	
}
