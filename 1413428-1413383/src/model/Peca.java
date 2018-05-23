package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
;

public abstract class Peca {
	
	char cor;
	private TipoPeca tipo;
	private Image img;
	int qtdMovimentos;
	int linha;
	int coluna;
	boolean selecionada;
	
	public Peca(TipoPeca tipo, char cor, int linha, int coluna) {
		this.cor = cor;
		this.tipo = tipo;
		this.qtdMovimentos = 0;
		this.coluna = coluna;
		this.linha = linha;
		String url = "C:\\Users\\marco\\Downloads\\Chess-MVC-master\\Chess-MVC-master\\1413428-1413383\\src\\images\\"+cor+"_"+tipo.getTipo()+".gif";
		try {
			this.img = ImageIO.read(new File(url));
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Erro ao tentar ler a imagem");
			System.exit(1);
		}
	}
	
	/* getMovimentos retorna uma matriz de 2*m em que na primeira coluna est�o os x poss�veis e na segunda coluna est�o os y poss�veis 
	 * ou seja cada linha possui um par (x,y) que representa a casa destino da pe�a referente a algum movimento poss�vel
	 * considerando a localiza��o atual da pe�a */
	public abstract int[][] getMovimentos(); 
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
	
	public void selecionaPeca() {
		this.selecionada = true;
		System.out.println(this.tipo.getTipo());
		System.out.println("linha:"+String.valueOf(this.getLinha()));
		System.out.println("coluna:"+String.valueOf(this.getColuna()));
	}
	
	public void desselecionaPeca() {
		this.selecionada = false;
	}
	
	public char getCor() {
		return this.cor;
	}


}
