package model;

import java.awt.geom.Rectangle2D;

public class Casa {
	
	int coluna;
	int linha;
	int top;
	int left;
	Peca p;
	Rectangle2D rect;
	boolean ocupada;
	
	
	Casa(int coluna,int linha, int dim,int left,int top){
		this.coluna = coluna;
		this.linha = linha;
		this.top = top;
		this.left = left;
		this.rect = new Rectangle2D.Float(left,top,dim,dim);
		
		
		
	}
	
	public Rectangle2D getRetangulo() {
		return rect;
	}
	
	public boolean isOcupada() {
		return ocupada;
	}
	public Peca getPeca() {
		return p;
	}
	public void setPeca( Peca p ) {
		this.p = p;
	}
	
	
	
	
}
