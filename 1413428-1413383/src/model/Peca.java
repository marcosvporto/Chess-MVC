package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Principal;
;

public abstract class Peca {

	char cor;
	private TipoPeca tipo;
	int qtdMovimentos;
	int linha;
	int coluna;
	boolean selecionada;
	String url;
	
	public Peca(TipoPeca tipo, char cor) {
		this.cor = cor;
		this.tipo = tipo;
		this.qtdMovimentos = 0;
		
		this.url = Principal.path + "src/images/"+cor+"_"+tipo.getTipo()+".gif";
	
	}
	
	/* getMovimentos retorna uma matriz de 2*m em que na primeira coluna est�o os x poss�veis e na segunda coluna est�o os y poss�veis 
	 * ou seja cada linha possui um par (x,y) que representa a casa destino da pe�a referente a algum movimento poss�vel
	 * considerando a localiza��o atual da pe�a */
	public abstract int[][] getMovimentos(); 

	
	public int getQtdMovimentos() {
		return qtdMovimentos;
	}
	
	public TipoPeca getTipo() {
		return tipo;
	}
	
	public int getLinha() {
		return linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public void selecionaPeca() {
		this.selecionada = true;

	}
	
	public void desselecionaPeca() {
		this.selecionada = false;
	}
	
	public char getCor() {
		return this.cor;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void incMovimento() {
		this.qtdMovimentos++;
	}

}
