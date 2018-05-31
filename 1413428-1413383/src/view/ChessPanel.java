package view;

import model.Casa;
import model.Tabuleiro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import controller.Movimento;







public class ChessPanel extends JPanel {
	
	
	int i, j;
	double leftX=100.0;
	double topY=100.0;
	double larg=80.0;
	double alt=80.0;
	public static boolean carregarPecas=true;
	public static boolean carregarTabuleiro=true;
	Tabuleiro t;
	
	
	public ChessPanel() {
		
		setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				//System.out.println(x);
				//System.out.println(y);
				t.selecionaCasa(x, y);
				//Movimento.trataMovimento(t);
				repaint();
				
			}
			
		});
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(carregarTabuleiro)
			carregarTabuleiro(g2d);
		alteraCorCasaSelecionada(g2d);
		if(carregarPecas)
			carregarPecas(g2d);
		
			
		
		
		
		
	}
	
	public void carregarTabuleiro(Graphics2D g2d) {
		Casa [][]r = t.getMatrizCasas();
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				//System.out.println(r[i][j].getCenterX());
				g2d.draw(r[i][j].getRetangulo());
				if((i%2==0) && (j%2!=0))
					g2d.fill(r[i][j].getRetangulo());
				else if((i%2!=0) && (j%2==0))
					g2d.fill(r[i][j].getRetangulo());
				
				
				
			}
		}
	}
		
		
	public void carregarPecas(Graphics2D g2d) {
		Casa [][]c = t.getMatrizCasas();
		for(int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(c[i][j].isOcupada()) {
					int altPeca = c[i][j].getPeca().getImage().getHeight(null);
					int largPeca = c[i][j].getPeca().getImage().getWidth(null);
					int imgTop = (int) c[i][j].getRetangulo().getCenterY()-(altPeca/2);
					int imgLeft = (int) c[i][j].getRetangulo().getCenterX()-(largPeca/2);
					g2d.drawImage(c[i][j].getPeca().getImage(),imgLeft,imgTop,null);
			}
		}
	}
	}
	public void alteraCorCasaSelecionada(Graphics2D g2d) {
		Casa [][]c = t.getMatrizCasas();
		//Rectangle2D [][]r = t.getMatrizRetangulos();
		for(int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(c[i][j].equals(t.getCasaSelecionada())) {
					g2d.setPaint(Color.GRAY);
					g2d.fill(c[i][j].getRetangulo());
				}
				/*if(c[i][j].getPermissao()) {
					g2d.setPaint(Color.GREEN);
					g2d.fill(r[i][j]);
				}*/
				
			}
		}
	
	
		
		
	
		
	}
	
	
	public void setTabuleiro(Tabuleiro t) {
		this.t = t;
	}
	
	

}
