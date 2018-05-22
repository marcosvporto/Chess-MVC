package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.ChessFrame;
import view.ChessPanel;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChessFrame f=new ChessFrame("XADREZ");
		ChessPanel p = new ChessPanel();
		
		f.getContentPane().add(p);
		f.setVisible(true);
		

		
		

	}

}
