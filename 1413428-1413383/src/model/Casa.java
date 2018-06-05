package model;

import java.awt.geom.Rectangle2D;

public class Casa {
	
	int coluna;
	int linha;
	int top;
	int left;
	public Peca p;
	Peca pretendente;
	Rectangle2D rect;
	boolean ocupada=false;
	boolean selecionada=false;
	boolean movimentoPossivel=false;
	
	
	Casa(int coluna,int linha, int dim,int left,int top){
		this.coluna = coluna;
		this.linha = linha;
		this.top = top;
		this.left = left;
		this.rect = new Rectangle2D.Float(left,top,dim,dim);
		this.selecionada = false;
		this.ocupada = false;
		
		if( linha == 0 ) {
			switch(coluna) {
			case 0: setPeca(new Torre(  'p', linha, coluna));break;
			case 1: setPeca(new Cavalo( 'p', linha, coluna));break;
			case 2: setPeca(new Bispo(  'p', linha, coluna));break;
			case 3: setPeca(new Dama(   'p', linha, coluna));break;
			case 4: setPeca(new Rei(    'p', linha, coluna));break;
			case 5: setPeca(new Bispo(  'p', linha, coluna));break;
			case 6: setPeca(new Cavalo( 'p', linha, coluna));break;
			case 7: setPeca(new Torre(  'p', linha, coluna));break;
			}
		}
		if (linha == 7) {
			switch(coluna) {
			case 0: setPeca(new Torre(  'b', linha, coluna));break;
			case 1: setPeca(new Cavalo( 'b', linha, coluna));break;
			case 2: setPeca(new Bispo(  'b', linha, coluna));break;
			case 3: setPeca(new Dama(   'b', linha, coluna));break;
			case 4: setPeca(new Rei(    'b', linha, coluna));break;
			case 5: setPeca(new Bispo(  'b', linha, coluna));break;
			case 6: setPeca(new Cavalo( 'b', linha, coluna));break;
			case 7: setPeca(new Torre(  'b', linha, coluna));break;
			}
		}
		
		if(linha == 1) {
			setPeca(new Peao(  'p', linha, coluna));
		}
			
		if(linha == 6) {
			setPeca(new Peao(  'b', linha, coluna));
		}
		
		
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
		//this.p.incrementaMovimento();
		this.ocupada = true;
	}
	public int getTop() {
		return this.top;
	}
	public int getLeft() {
		return this.left;
	}
	public boolean isSelecionada() {
		return this.selecionada;
	}
	public void selecionaCasa() {
		this.selecionada = true;
		if(this.isOcupada())
			this.p.selecionaPeca();
	}
	public void desselecionaCasa() {
		this.selecionada = false;
		if(this.isOcupada())
			this.p.desselecionaPeca();
	}
	public void permiteMovimento( Peca p ) {
		this.movimentoPossivel = true;
		this.pretendente = p;
	}
	public void proibeMovimento() {
		this.movimentoPossivel = false;
		this.pretendente = null;
	}
	public boolean getPermissao() {
		return this.movimentoPossivel;
	}
	
	public Peca popPeca() {
		Peca peca = this.p;
		this.p = null;
		this.ocupada = false;
		return peca;
	}
	public int getColuna() {
		return this.coluna;
	}
	public int getLinha() {
		return this.linha;
	}
}
