package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.ChessFrame;
import view.ChessPanel;

public class Principal {
	
	public static Partida p;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChessFrame f=new ChessFrame("XADREZ",680,700);
		ChessPanel cp = new ChessPanel();
		p = new Partida(10,10,80);
		p.comecarPartida(cp);
		f.getContentPane().add(cp);
		f.setVisible(true);
		
		

		
		

	}

}
