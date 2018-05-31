package controller;

import model.Casa;
import model.Peca;
import model.Tabuleiro;

public class Movimento {
	
	Casa [][] matrizCasa;
	public static int qtdMovimentos = 0;
	/*
	public static void trataMovimento(Tabuleiro t) {
		Casa [][]c = t.getMatrizCasas();
		for( int i=0 ; i<8 ; i++) {
			for( int j=0 ; j<8 ; j++ ) {
				if( c[i][j].isSelecionada()) {
					switch(c[i][j].getPeca().getTipo().getTipo()) {
					case "peao" :   movimentoPeao(   c[i][j].getPeca() , t.getMatrizCasas());break;
					case "bispo" :  movimentoBispo(  c[i][j].getPeca() , t.getMatrizCasas());break;
					case "cavalo" : movimentoCavalo( c[i][j].getPeca() , t.getMatrizCasas());break;
					case "torre" :  movimentoTorre(  c[i][j].getPeca() , t.getMatrizCasas());break;
					case "rei" :    movimentoRei(    c[i][j].getPeca() , t.getMatrizCasas());break;
					case "dama" :   movimentoDama(   c[i][j].getPeca() , t.getMatrizCasas());break;
					}
				}
			}
		}
	}*/
	public Movimento(Casa[][]c) {
		this.matrizCasa = c;
		
	}
	public boolean movimentoPeao(Casa origem, Casa destino) {
		
		if(origem.getPeca().getCor()=='p') {
			if(origem.getColuna() == destino.getColuna()) {
				if(origem.getLinha() == (destino.getLinha() -1)) {
					return true;
				}
				else if(origem.getLinha() == (destino.getLinha() -2)) {
					if(origem.getLinha()==1) {
						return true;
					}
				}
			}
		}
		else {
			if(origem.getColuna() == destino.getColuna()) {
				if(origem.getLinha() == (destino.getLinha() +1)) {
					return true;
				}
				else if(origem.getLinha() == (destino.getLinha() +2)) {
					if(origem.getLinha()==6)
						return true;
				}
			}
		}
		
		return false;
	}
	public boolean ataquePeao(Casa origem, Casa destino) {
		
		
		if(origem.getPeca().getCor()=='p') {
			if(origem.getLinha() - destino.getLinha()==-1 && Math.abs(origem.getColuna() - destino.getColuna())==1) {
				return true;
			}
		}
		else {
			if(origem.getLinha() - destino.getLinha()==1 && Math.abs(origem.getColuna() - destino.getColuna())==1) {
				return true;
			}
		}
		return false;
	}
	public boolean movimentoBispo(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha()) == Math.abs(origem.getColuna() - destino.getColuna())) {
		
			return true;
		}
		return false;
	}
	public boolean puloBispo(Casa origem, Casa destino) {
		Casa teste;
		int coluna = origem.getColuna();
		int linha = origem.getLinha();
		int distanciaLinha = origem.getLinha() - destino.getLinha();
		int distanciaColuna = origem.getColuna() - destino.getColuna();
		
		if(distanciaLinha > 0 && distanciaColuna >0) {
			
			teste = this.matrizCasa[coluna-1][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaLinha < 0 && distanciaColuna < 0) {
			teste = this.matrizCasa[coluna+1][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaLinha < 0 && distanciaColuna > 0) {
			
			teste = this.matrizCasa[coluna-1][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else {
			teste = this.matrizCasa[coluna+1][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		return true;
		
		
	}
	public boolean movimentoCavalo(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getColuna() - destino.getColuna())==2 ) {
			if(Math.abs(origem.getLinha() - destino.getLinha())==1) {
				
				return true;
			}
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==2) {
			if(Math.abs(origem.getColuna() - destino.getColuna())==1) {
				
				return true;
			}
		}
		return false;
	}
	public boolean movimentoDama(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha()) == Math.abs(origem.getColuna() - destino.getColuna()))
		{
			
			return true;
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==0) {
			
			
			return true;
		}
		if(Math.abs(origem.getColuna() - destino.getColuna())==0) {
		
			
			return true;
		}
		return false;
	}
	public boolean puloDama(Casa origem,Casa destino) {
		Casa teste;
		int coluna = origem.getColuna();
		int linha = origem.getLinha();
		int distanciaLinha = origem.getLinha() - destino.getLinha();
		int distanciaColuna = origem.getColuna() - destino.getColuna();
		
		if(distanciaLinha > 0 && distanciaColuna >0) {
			
			teste = this.matrizCasa[coluna-1][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaLinha < 0 && distanciaColuna < 0) {
			teste = this.matrizCasa[coluna+1][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaLinha < 0 && distanciaColuna > 0) {
			
			teste = this.matrizCasa[coluna-1][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaLinha > 0 && distanciaColuna < 0) {
			teste = this.matrizCasa[coluna+1][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaColuna == 0 && distanciaLinha > 0) {
			
			teste = this.matrizCasa[coluna][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaColuna == 0 && distanciaLinha < 0) {
			
			teste = this.matrizCasa[coluna][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		else if(distanciaColuna > 0) {
			
			teste = this.matrizCasa[coluna-1][linha];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		else {
			teste = this.matrizCasa[coluna+1][linha];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		return true;
		
		
	}
	public boolean movimentoRei(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==1) {

			return true;
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==0) {
			
			return true;
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==0 && Math.abs(origem.getColuna() - destino.getColuna())==1) {
			
			return true;
		}
		return false;
	}
	public boolean movimentoTorre(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha())==0) {
			
			return true;
		}
		if(Math.abs(origem.getColuna() - destino.getColuna())==0) {
			
			return true;
		}
		return false;
	}
	public boolean puloTorre(Casa origem, Casa destino) {
		Casa teste;
		int coluna = origem.getColuna();
		int linha = origem.getLinha();
		int distanciaLinha = origem.getLinha() - destino.getLinha();
		int distanciaColuna = origem.getColuna() - destino.getColuna();
		
		if(distanciaColuna == 0 && distanciaLinha > 0) {
			
			teste = this.matrizCasa[coluna][linha-1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				linha--;
				teste = this.matrizCasa[coluna][linha];
			}
		}
		else if(distanciaColuna == 0 && distanciaLinha < 0) {
			
			teste = this.matrizCasa[coluna][linha+1];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				linha++;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		else if(distanciaColuna > 0) {
			
			teste = this.matrizCasa[coluna-1][linha];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna--;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		else {
			teste = this.matrizCasa[coluna+1][linha];
			while(!teste.equals(destino) ) {
				if(teste.isOcupada())
					return false;
				coluna++;
				teste = this.matrizCasa[coluna][linha];
			}
			
		}
		return true;
	}

	public void validaMovimento(Casa origem, Casa destino) {
		//destino.setPeca(origem.popPeca());
		Boolean movimenta = false;
		switch (origem.getPeca().getTipo().getTipo()) {
		case "peao" :   movimenta = movimentoPeao(   origem,destino);break;
		case "bispo" :  movimenta = movimentoBispo(  origem,destino,false)?puloBispo(origem,destino):false;break;
		case "cavalo" : movimenta = movimentoCavalo( origem,destino,false);break;
		case "torre" :  movimenta = movimentoTorre(  origem,destino,false)?puloTorre(origem,destino):false;break;
		case "rei" :    movimenta = movimentoRei(    origem,destino,false);break;
		case "dama" :   movimenta = movimentoDama(   origem,destino,false)?puloDama(origem,destino):false;break;
		}
		if (movimenta) {
			
			destino.setPeca(origem.popPeca());
			this.qtdMovimentos++;
		}
	}
	
	public void validaAtaque(Casa atacante, Casa alvo) {
		Boolean ataca = false;
		switch (atacante.getPeca().getTipo().getTipo()) {
		case "peao" :   ataca = ataquePeao(      atacante, alvo    );break;
		case "bispo" :  ataca = movimentoBispo(  atacante,alvo,true)?puloBispo(atacante,alvo):false;break;
		case "cavalo" : ataca = movimentoCavalo( atacante,alvo,true);break;
		case "torre" :  ataca = movimentoTorre(  atacante,alvo,true)?puloTorre(atacante,alvo):false;break;
		case "rei" :    ataca = movimentoRei(    atacante,alvo,true);break;
		case "dama" :   ataca = movimentoDama(   atacante,alvo,true)?puloDama(atacante,alvo):false;break;
		}
		if(ataca) {
		
			
			alvo.popPeca();
			alvo.setPeca(atacante.popPeca());
			this.qtdMovimentos++;
			
			
			}
		
		}
	}


