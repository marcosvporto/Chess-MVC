package model;

import java.awt.geom.Rectangle2D;

import controller.Movimento;
import model.Casa;
public class Tabuleiro {
	
	private static Tabuleiro t = null;
	int dimCasa = 80;
	int top = 10;
	int left = 10;
	Casa[][] matrizCasas;

	Casa selecionada = null;
	boolean casaSelecionada = false; 
	
	public static Tabuleiro getTabuleiro() {
		if(t==null)
			t = new Tabuleiro();
		return t;
	}
	
	
	private Tabuleiro() {
		int i,j;
		matrizCasas = new Casa[8][8];
		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				matrizCasas[i][j] = new Casa(i,j,dimCasa,left+(i*dimCasa),top+(j*dimCasa));
			}
		}
	}
	
	public Casa[][] getMatrizCasas() {
		return matrizCasas;
	}
	

	public Casa getCasaSelecionada() {
		if(this.casaSelecionada)
			return this.selecionada;
		else return null;
	}
	public void selecionaCasa(int i, int j) {
		Movimento m = new Movimento(this.getMatrizCasas());
		
				
				if(this.matrizCasas[i][j].isOcupada()) {
					if(this.casaSelecionada && this.matrizCasas[i][j].getPeca().getCor()!=this.selecionada.getPeca().getCor()) {
						m.validaAtaque(this.selecionada, this.matrizCasas[i][j]);
						this.selecionada.desselecionaCasa();
						this.selecionada = null;
						this.casaSelecionada = false;
						removePossiveisMovimentos();
					}
					else if(this.casaSelecionada && this.selecionada.equals(this.matrizCasas[i][j])) {
						this.selecionada.desselecionaCasa();
						this.selecionada = null;
						this.casaSelecionada = false;
						removePossiveisMovimentos();
						
					}
					else {
						this.selecionada = this.matrizCasas[i][j];
						this.casaSelecionada = true;
						this.selecionada.selecionaCasa();
						addPossiveisMovimentos(this.selecionada , m);
					}
					
					
				}
				else if(this.casaSelecionada) {
					m.validaMovimento(this.selecionada, this.matrizCasas[i][j]);
					this.selecionada.desselecionaCasa();
					this.selecionada = null;
					this.casaSelecionada = false;
					removePossiveisMovimentos();
				}
					
			}
	public void addPossiveisMovimentos(Casa c, Movimento m) {
		int [][] mov = c.getPeca().getMovimentos();
		int cols = mov[0].length;
		boolean possivel = false;
		removePossiveisMovimentos();
		for(int j = 0; j<cols;j++) {
			int linhaTabuleiro = mov[1][j];
			int colunaTabuleiro = mov[0][j];
			if(linhaTabuleiro>=0 && linhaTabuleiro <=7 && colunaTabuleiro >=0 && colunaTabuleiro <=7) {
				Casa possivelMovimento = matrizCasas[linhaTabuleiro][colunaTabuleiro];
				if(!possivelMovimento.isOcupada() ) {
					switch(c.getPeca().getTipo().getTipo()) {
					case "peao" : possivel = m.movimentoPeao(c,possivelMovimento);break;
					case "bispo" : possivel =m.movimentoBispo(c, possivelMovimento)?m.puloBispo(c, possivelMovimento):false ;break;
					case "cavalo" : possivel = m.movimentoCavalo(c,possivelMovimento);break;
					case "torre" : possivel = m.movimentoTorre(c,possivelMovimento)?m.puloTorre(c, possivelMovimento):false;break;
					case "rei" : possivel = m.movimentoRei(c,possivelMovimento,true);break;
					case "dama" : possivel = m.movimentoDama(c,possivelMovimento)?m.puloDama(c, possivelMovimento):false;break;
					}
					if(possivel) {
						possivelMovimento.permiteMovimento(c.getPeca());
					}
				}
				else if(possivelMovimento.getPeca().getCor() != c.getPeca().getCor()) {
					switch(c.getPeca().getTipo().getTipo()) {
					case "peao" : possivel = m.ataquePeao(c,possivelMovimento);break;
					case "bispo" : possivel =m.movimentoBispo(c, possivelMovimento)?m.puloBispo(c, possivelMovimento):false ;break;
					case "cavalo" : possivel = m.movimentoCavalo(c,possivelMovimento);break;
					case "torre" : possivel = m.movimentoTorre(c,possivelMovimento)?m.puloTorre(c, possivelMovimento):false;break;
					case "rei" : possivel = m.movimentoRei(c,possivelMovimento,true);break;
					case "dama" : possivel = m.movimentoDama(c,possivelMovimento)?m.puloDama(c, possivelMovimento):false;break;
					}
					if(possivel) {
						possivelMovimento.permiteMovimento(c.getPeca());
					}
				}
			}
		}
	}
	public void removePossiveisMovimentos() {
		for(int i=0 ; i<8 ; i++) {
			for(int j=0 ; j<8 ; j++) {
				this.matrizCasas[i][j].proibeMovimento();
			}
		}
	}
	public void promovePeao(TipoPeca tipo,Casa c) {
		char cor = c.getPeca().getCor();
		c.popPeca();
		switch (tipo.getTipo()) {
		case "dama":c.setPeca(new Dama(cor));break;
		case "bispo":c.setPeca(new Bispo(cor));break;
		case "torre":c.setPeca(new Torre(cor));break;
		case "cavalo":c.setPeca(new Cavalo(cor));break;
		}
		;
	}
	}
	
	


