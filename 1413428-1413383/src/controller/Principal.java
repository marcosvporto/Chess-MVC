package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.ChessFrame;
import view.ChessPanel;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChessFrame f=new ChessFrame("XADREZ",680,700);
		ChessPanel p = new ChessPanel();
		Partida.comecarPartida(p,10,10,80);
		f.getContentPane().add(p);
		f.setVisible(true);
		
		

		
		

	}

}
