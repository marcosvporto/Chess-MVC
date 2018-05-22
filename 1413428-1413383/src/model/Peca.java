package model;

import java.awt.Image;
;

public class Peca {
	
	char cor;
	private TipoPeca tipo;
	private Image img;
	int qtdMovimentos;
	int linha;
	int coluna;
	
	public Peca(TipoPeca tipo, char cor, int linha, int coluna) {
		this.cor = cor;
		this.tipo = tipo;
		this.qtdMovimentos = 0;
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public int getQtdMovimentos() {
		return qtdMovimentos;
	}
	
	public TipoPeca getTipo() {
		return tipo;
	}
	
	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}
	public Image getImage() {
		return img;
	}
	
	


}
