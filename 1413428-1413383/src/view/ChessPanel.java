package view;

import model.Peao;
import model.Bispo;
import model.Casa;
import model.Cavalo;
import model.Dama;
import model.Rei;
import model.Tabuleiro;
import model.TipoPeca;
import model.Torre;
import controller.Movimento;
import controller.Partida;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


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
				
				//marca casa selecionada
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
		int linha = -1;
		int coluna = -1;


		//identifica casa selecionada
		for(int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
				if( c[i][j].equals( t.getCasaSelecionada() ) ) {
					
					//pinta casa selecionada
					pintaCasaSelecionada(Color.GRAY, c[i][j], g2d);
					
					linha = j; 
					coluna = i;
					break;
				}
				/*if(c[i][j].getPermissao()) {
					g2d.setPaint(Color.GREEN);
					g2d.fill(r[i][j]);
				}*/
			}
		}
		
		//pinta possiveis movimentos
		if(linha != -1 && coluna != -1) {
			
			char cor =  t.getCasaSelecionada().getPeca().getCor();

			int movimentos [][] = t.getCasaSelecionada().getPeca().getMovimentos(linha, coluna, cor);
			//	       	MOVIMENTO 1  	MOVIMENTO 2		MOVIMENTO 3		...
			//         |linha i     | linha i    	| linha i  		|     
			//         |coluna j    | coluna j    	| coluna j   	|  
			
			System.out.println(t.getCasaSelecionada().getPeca().getTipo());
			
			int cols = movimentos[0].length; 
			
			//pinta possiveis movimentos da peca
			for(int j=0;j<cols;j++) {

				int linhaTabuleiro = movimentos[1][j];
				int colunaTabuleiro = movimentos[0][j];
				//System.out.println(linhaTabuleiro);
				//System.out.println(colunaTabuleiro);
					if(linhaTabuleiro>=0 && linhaTabuleiro <=7 && colunaTabuleiro >=0 && colunaTabuleiro <=7) {
						pintaCasaSelecionada(Color.GRAY, c[linhaTabuleiro][colunaTabuleiro], g2d);
					}
					
					
			}//fim do for j
		}//fim do if
	}
	

	public void pintaCasaSelecionada(Color cor, Casa casa, Graphics2D g2d) {
		g2d.setPaint(cor);
		g2d.fill(casa.getRetangulo());
	}
	
	
	public void setTabuleiro(Tabuleiro t) {
		this.t = t;
	}

}
