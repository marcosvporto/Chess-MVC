package view;

import model.Casa;
import model.Tabuleiro;
import model.TipoPeca;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.Movimento;


public class ChessPanel extends JPanel {
	
	
	int i, j;
	double leftX=100.0;
	double topY=100.0;
	double larg=80.0;
	double alt=80.0;
	public static boolean carregarPecas=true;
	public static boolean carregarTabuleiro=true;
	TipoPeca tipo = TipoPeca.peao;
	int colunaPromo = -1;
	int linhaPromo = -1;
	Tabuleiro t;
	Rectangle2D [][] matrizRect;
	
	
	public ChessPanel() {
		t = Tabuleiro.getTabuleiro();
		setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				for(int i = 0; i < 8 ; i++) {
					for(int j = 0; j<8;j++) {
						if (matrizRect[i][j].contains(x,y))
							t.selecionaCasa(i, j);
					}
						
				}
				
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
		this.matrizRect = new Rectangle2D[8][8];

		Casa [][]r = t.getMatrizCasas();
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				int left = r[i][j].getLeft();
				int top = r[i][j].getTop();
				int dim  =r[i][j].getDim();
				this.matrizRect[i][j] = new Rectangle2D.Float(left,top,dim,dim);
				g2d.draw(this.matrizRect[i][j]);
				if((i%2==0) && (j%2!=0))
					g2d.fill(this.matrizRect[i][j]);
				else if((i%2!=0) && (j%2==0))
					g2d.fill(this.matrizRect[i][j]);
				
				
				
			}
		}
	}
		
		
	public void carregarPecas(Graphics2D g2d) {

		int linhaPeao = 0;
		int colunaPeao = 0;
		Casa [][]c = t.getMatrizCasas();
		Image im;
		for(int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(c[i][j].isOcupada()) {
					try {
						im = ImageIO.read(new File(c[i][j].getPeca().getUrl()));
						int altPeca = im.getHeight(null);
						int largPeca = im.getWidth(null);
						int imgTop = (int) this.matrizRect[i][j].getCenterY()-(altPeca/2);
						int imgLeft = (int) (int) this.matrizRect[i][j].getCenterX()-(largPeca/2);
						g2d.drawImage(im,imgLeft,imgTop,null);
					}catch(IOException ex) {
						System.out.println(ex.getMessage());
						System.out.println("Erro ao tentar ler a imagem");
						System.exit(1);
					}
				if((j==0 || j==7)) {
					if(c[i][j].getPeca().getTipo().equals(TipoPeca.peao)) {
						colunaPromo = i;
						linhaPromo = j;
					}
				}
			}
		}
	}
		if (colunaPromo != -1 && linhaPromo != -1) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem dama = new JMenuItem("Dama");
			JMenuItem bispo = new JMenuItem("Bispo");
			JMenuItem torre = new JMenuItem("Torre");
			JMenuItem cavalo = new JMenuItem("Cavalo");
			dama.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tipo = TipoPeca.dama;
				}
			});
			bispo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tipo = TipoPeca.bispo;
				}
			});
			torre.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tipo = TipoPeca.torre;
				}
			});
			cavalo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tipo = TipoPeca.cavalo;
				}
			});
			menu.add(dama);
			menu.add(bispo);
			menu.add(torre);
			menu.add(cavalo);
			menu.show(this,(int) this.matrizRect[colunaPromo][linhaPromo].getCenterX(), 
					(int)this.matrizRect[colunaPromo][linhaPromo].getCenterY());
			
			if(!tipo.equals(TipoPeca.peao)) {
				t.promovePeao(tipo, c[colunaPromo][linhaPromo]);
				menu.setVisible(false);
				tipo = TipoPeca.peao;
				colunaPromo = -1;
				linhaPromo = -1;
				this.repaint();
			}
		}
	}
	public void alteraCorCasaSelecionada(Graphics2D g2d) {
		Casa [][]c = t.getMatrizCasas();

		for(int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(c[i][j].equals(t.getCasaSelecionada())) {
					g2d.setPaint(Color.GRAY);
					g2d.fill(this.matrizRect[i][j]);
					
				}
				else {
					if(c[i][j].getPermissao()) {
						g2d.setPaint(new Color(153,255,153));
						g2d.fill(this.matrizRect[i][j]);
						g2d.setPaint(Color.BLACK);
						g2d.draw(this.matrizRect[i][j]);
					}
				}
				
			}
		}
		
	
	
		
		
	
		
	}
	
	

	
	

}
