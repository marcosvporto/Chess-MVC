package model;

import java.awt.geom.Rectangle2D;
import model.Casa;
public class Tabuleiro {
	

	int dimCasa;
	int top;
	int left;
	Casa[][] matrizCasas;
	Rectangle2D [][] matrizRetangulos;
	
	
	
	public Tabuleiro(int top,int left, int dimCasa) {
		int i,j;
		this.dimCasa = dimCasa;
		this.top = top;
		this.left = left;
		matrizCasas = new Casa[8][8];
		matrizRetangulos = new Rectangle2D[8][8];
		
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				matrizCasas[i][j] = new Casa(i,j,dimCasa,left+(i*dimCasa),top+(j*dimCasa));
				matrizRetangulos[i][j] = matrizCasas[i][j].getRetangulo();
			}
		}
	}
	
	public Casa[][] getMatrizCasas() {
		return matrizCasas;
	}
	
	public Rectangle2D[][] getMatrizRetangulos() {
		return matrizRetangulos;
	}
	
	
	

}
