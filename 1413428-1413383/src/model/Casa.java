package model;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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
	ArrayList<Peca> possiveisPecas;
	ArrayList<Peca> rangePecas;
	Casa(int coluna,int linha, int dim,int left,int top){
		this.coluna = coluna;
		this.linha = linha;
		this.top = top;
		this.left = left;
		this.dim = dim;

		this.selecionada = false;
		this.ocupada = false;
		possiveisPecas = new ArrayList<Peca>();
		rangePecas = new ArrayList<Peca>();
		
	}
	
	public void pretenderPeca(Peca p) {
		this.rangePecas.add(p);
	}
	public void limpaRange() {
		this.rangePecas.clear();
	}
	public ArrayList<Peca> getRange(){
		return this.rangePecas;
	}
	public boolean alcancavel() {
		if(this.ocupada && this.rangePecas.size()>0) {
			for(int i=0;i<this.rangePecas.size();i++) {
				if(this.rangePecas.get(i).getCor()!=this.p.getCor()) {
					return true;
				}
			}
		}
		return false;
	}
	public void permitePeca(Peca p){
		this.possiveisPecas.add(p);
	}
	
	public void limpaPossiveisPecas() {
		for(int i=0;i<this.possiveisPecas.size();i++) {
			this.possiveisPecas.get(i).limpaPossiveisCasas();
		}
		this.possiveisPecas.clear();
	}
	public boolean possivelTomada() {
		if(this.ocupada && this.possiveisPecas.size()>0)
			return true;
		return false;
	}
	public ArrayList<Peca> getPossiveisPecas(){
		return this.possiveisPecas;
	}
	public boolean possivelAtaque(Peca p) {
		if(this.possiveisPecas.contains(p))
			return true;
		return false;
	}
	public void imprimePossiveisPecas() {
		for(int i=0;i<this.possiveisPecas.size();i++) {
			Peca p = this.possiveisPecas.get(i);
			System.out.print(p.getCor()+"-"+p.getTipo().getTipo()+"["+p.getColuna()+","+p.getLinha()+"];");
		}
	}
	public void imprimeRangePecas() {
		for(int i=0;i<this.rangePecas.size();i++) {
			Peca p = this.rangePecas.get(i);
			System.out.print(p.getCor()+"-"+p.getTipo().getTipo()+"["+p.getColuna()+","+p.getLinha()+"];");
		}
		System.out.println();
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
