package controller;

import model.Casa;
import model.Peca;
import model.Tabuleiro;

public class Movimento {
	
	
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
	public static void movimentoPeao(Casa origem, Casa destino, boolean ataque) {
		
		if(origem.getPeca().getCor()=='p') {
			if(origem.getColuna() == destino.getColuna()) {
				if(origem.getLinha() == (destino.getLinha() -1)) {
					destino.setPeca(origem.popPeca());
				}
				else if(origem.getLinha() == (destino.getLinha() -2)) {
					if(origem.getLinha()==1) {
						destino.setPeca(origem.popPeca());
					}
				}
			}
		}
		else {
			if(origem.getColuna() == destino.getColuna()) {
				if(origem.getLinha() == (destino.getLinha() +1)) {
					destino.setPeca(origem.popPeca());
				}
				else if(origem.getLinha() == (destino.getLinha() +2)) {
					if(origem.getLinha()==6)
						destino.setPeca(origem.popPeca());
				}
			}
		}
		
		
	}
	public static void movimentoBispo(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha()) == Math.abs(origem.getColuna() - destino.getColuna())) {
		
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}	
	}
	public static void movimentoCavalo(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getColuna() - destino.getColuna())==2 ) {
			if(Math.abs(origem.getLinha() - destino.getLinha())==1) {
				if(ataque)
					destino.popPeca();
				destino.setPeca(origem.popPeca());
			}
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==2) {
			if(Math.abs(origem.getColuna() - destino.getColuna())==1) {
				if(ataque)
					destino.popPeca();
				destino.setPeca(origem.popPeca());
			}
		}
	}
	public static void movimentoDama(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha()) == Math.abs(origem.getColuna() - destino.getColuna()))
		{
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==0) {
			
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		if(Math.abs(origem.getColuna() - destino.getColuna())==0) {
		
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		
	}
	public static void movimentoRei(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==1) {

			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==0) {
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==0 && Math.abs(origem.getColuna() - destino.getColuna())==1) {
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
	}
	public static void movimentoTorre(Casa origem, Casa destino, boolean ataque) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha())==0) {
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		if(Math.abs(origem.getColuna() - destino.getColuna())==0) {
			if(ataque)
				destino.popPeca();
			destino.setPeca(origem.popPeca());
		}
		
	}

	public static void validaMovimento(Casa origem, Casa destino) {
		//destino.setPeca(origem.popPeca());
		switch (origem.getPeca().getTipo().getTipo()) {
		case "peao" :   movimentoPeao(   origem,destino,false);break;
		case "bispo" :  movimentoBispo(  origem,destino,false);break;
		case "cavalo" : movimentoCavalo( origem,destino,false);break;
		case "torre" :  movimentoTorre(  origem,destino,false);break;
		case "rei" :    movimentoRei(    origem,destino,false);break;
		case "dama" :   movimentoDama(   origem,destino,false);break;
		}
	}
	
	public static void validaAtaque(Casa atacante, Casa alvo) {
		switch (atacante.getPeca().getTipo().getTipo()) {
		case "peao" :   System.out.println("peao");;break;
		case "bispo" :  movimentoBispo(  atacante,alvo,true);break;
		case "cavalo" : movimentoCavalo( atacante,alvo,true);break;
		case "torre" :  movimentoTorre(  atacante,alvo,true);break;
		case "rei" :    movimentoRei(    atacante,alvo,true);break;
		case "dama" :   movimentoDama(   atacante,alvo,true);break;
		}
	}

}
