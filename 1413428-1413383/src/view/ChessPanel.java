package view;

import model.Casa;
import model.Tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;







public class ChessPanel extends JPanel {
	
	
	int i, j;
	double leftX=100.0;
	double topY=100.0;
	double larg=80.0;
	double alt=80.0;
	public static boolean carregarPecas=false;
	public static boolean carregarTabuleiro=true;
	Tabuleiro t;
	
	
	public ChessPanel() {
		
		setBackground(Color.WHITE);
		t = new Tabuleiro(100, 100, 80);
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(carregarTabuleiro)
			CarregarTabuleiro(g2d);
		
		
		
		
	}
	
	public void CarregarTabuleiro(Graphics2D g2d) {
		Rectangle2D [][]r = t.getMatrizRetangulos();
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				System.out.println(r[i][j].getCenterX());
				g2d.draw(r[i][j]);
				
				
				
			}
		}
		
		
		
		
	}
	
	
	public void setTabuleiro(Tabuleiro t) {
		this.t = t;
	}
	
	

}
