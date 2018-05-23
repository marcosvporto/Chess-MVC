package model;

import java.awt.geom.Rectangle2D;

import controller.Movimento;
import model.Casa;
public class Tabuleiro {
	

	int dimCasa;
	int top;
	int left;
	Casa[][] matrizCasas;
	Rectangle2D [][] matrizRetangulos;
	Casa selecionada = null;
	boolean casaSelecionada = false; 
	
	
	
	
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
	public Casa getCasaSelecionada() {
		if(this.casaSelecionada)
			return this.selecionada;
		else return null;
	}
	public void selecionaCasa(int x, int y) {
		
		for( int i=0 ; i<8 ; i++ ) {
			for( int j=0 ; j<8 ; j ++ ) {
				
				if(this.matrizCasas[i][j].getRetangulo().contains(x,y) && this.matrizCasas[i][j].isOcupada()) {
					
					if(this.casaSelecionada && this.matrizCasas[i][j].getPeca().getCor()!=this.selecionada.getPeca().getCor()) {
						Movimento.validaAtaque(this.selecionada, this.matrizCasas[i][j]);
						this.selecionada.desselecionaCasa();
						this.selecionada = null;
						this.casaSelecionada = false;
					}
					else if(this.casaSelecionada && this.selecionada.equals(this.matrizCasas[i][j])) {
						this.selecionada.desselecionaCasa();
						this.selecionada = null;
						this.casaSelecionada = false;
						
					}
					else {
						this.selecionada = this.matrizCasas[i][j];
						this.casaSelecionada = true;
						this.selecionada.selecionaCasa();
					}
					
					
				}
				else if(this.matrizCasas[i][j].getRetangulo().contains(x,y) && this.casaSelecionada) {
					Movimento.validaMovimento(this.selecionada, this.matrizCasas[i][j]);
					
					this.selecionada.desselecionaCasa();
					this.selecionada = null;
					this.casaSelecionada = false;
				}
					
				}
			}
		}
		
	}
	
	


