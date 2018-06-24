package model;

import java.awt.geom.Rectangle2D;

public class Casa {
	
	int coluna;
	int linha;
	int top;
	int left;
	int dim;
	Peca p;
	Peca pretendente;
	
	boolean ocupada=false;
	boolean selecionada=false;
	boolean movimentoPossivel=false;
	
	
	Casa(int coluna,int linha, int dim,int left,int top){
		this.coluna = coluna;
		this.linha = linha;
		this.top = top;
		this.left = left;
		this.dim = dim;

		this.selecionada = false;
		this.ocupada = false;
		
		this.p = null;
	}
	

	
	public boolean isOcupada() {
		return ocupada;
	}
	public Peca getPeca() {
		return p;
	}
	public void setPeca( Peca p ) {
		this.p = p;
		this.ocupada = true;
		p.setColuna(this.coluna);
		p.setLinha(this.linha);
	}
	public int getTop() {
		return this.top;
	}
	public int getLeft() {
		return this.left;
	}
	public int getDim() {
		return this.dim;
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
