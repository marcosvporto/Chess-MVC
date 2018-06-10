package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		
		this.url = "src/images/"+cor+"_"+tipo.getTipo()+".gif";
	
	}
	
	/* getMovimentos retorna uma matriz de 2*m em que na primeira coluna estão os x possíveis e na segunda coluna estão os y possíveis 
	 * ou seja cada linha possui um par (x,y) que representa a casa destino da peça referente a algum movimento possível
	 * considerando a localização atual da peça */
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
