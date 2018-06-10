package controller;

import model.Casa;
import model.Peca;
import model.Tabuleiro;
import model.TipoPeca;

public class Movimento {
	
	Casa [][] matrizCasa;
	public static int qtdMovimentos = 0;

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
						if(!matrizCasa[origem.getColuna()][2].isOcupada())
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
					if(origem.getLinha()==6) {
						if(!matrizCasa[origem.getColuna()][5].isOcupada())
							return true;
					}
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
	public boolean movimentoBispo(Casa origem, Casa destino) {
		
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
	public boolean movimentoCavalo(Casa origem, Casa destino) {
		
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
	public boolean movimentoDama(Casa origem, Casa destino) {
		
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
	public boolean movimentoRei(Casa origem, Casa destino, boolean teste) {
		
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==1) {

			return true;
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==1 && Math.abs(origem.getColuna() - destino.getColuna())==0) {
			
			return true;
		}
		if(Math.abs(origem.getLinha() - destino.getLinha())==0 && Math.abs(origem.getColuna() - destino.getColuna())==1) {
			
			return true;
		}
		
		//roque
		if(Math.abs(origem.getLinha() - destino.getLinha())==0 && Math.abs(origem.getColuna() - destino.getColuna())==2) {
			return validaRoque(origem,destino,teste);
		}
		return false;
	}
	public boolean validaRoque(Casa origem, Casa destino, boolean teste) {
		if(puloTorre(origem,destino) && origem.getPeca().getQtdMovimentos()==0) {
			if(origem.getColuna() - destino.getColuna()<0) {//roque curto
				
				if(this.matrizCasa[7][origem.getLinha()].isOcupada() &&
						this.matrizCasa[7][origem.getLinha()].getPeca().getTipo().equals(TipoPeca.torre) &&
						this.matrizCasa[7][origem.getLinha()].getPeca().getQtdMovimentos()==0) {
					//verifica se a torre está na posição inicial e ainda não se movimentou
				
					if(!this.matrizCasa[5][origem.getLinha()].isOcupada()) {
						//verifica se o rei não pulou ninguém
						if(!teste) {
							this.matrizCasa[7][origem.getLinha()].getPeca().incMovimento();
							this.matrizCasa[5][origem.getLinha()].setPeca(this.matrizCasa[7][origem.getLinha()].popPeca());

							return true;
						}
						return true;
					}
				}
			}
			else {//roque longo
				
				if(this.matrizCasa[0][origem.getLinha()].isOcupada() &&
						this.matrizCasa[0][origem.getLinha()].getPeca().getTipo().equals(TipoPeca.torre) &&
						this.matrizCasa[0][origem.getLinha()].getPeca().getQtdMovimentos()==0) {
					if(!this.matrizCasa[1][origem.getLinha()].isOcupada() &&
							!this.matrizCasa[2][origem.getLinha()].isOcupada() &&
							!this.matrizCasa[3][origem.getLinha()].isOcupada()) {
						if(!teste) {
							this.matrizCasa[0][origem.getLinha()].getPeca().incMovimento();
							this.matrizCasa[3][origem.getLinha()].setPeca(this.matrizCasa[0][origem.getLinha()].popPeca());
							return true;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean movimentoTorre(Casa origem, Casa destino) {
		
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

		Boolean movimenta = false;
		switch (origem.getPeca().getTipo().getTipo()) {
		case "peao" :   movimenta = movimentoPeao(   origem,destino);break;
		case "bispo" :  movimenta = movimentoBispo(  origem,destino)?puloBispo(origem,destino):false;break;
		case "cavalo" : movimenta = movimentoCavalo( origem,destino);break;
		case "torre" :  movimenta = movimentoTorre(  origem,destino)?puloTorre(origem,destino):false;break;
		case "rei" :    movimenta = movimentoRei(    origem,destino, false);break;
		case "dama" :   movimenta = movimentoDama(   origem,destino)?puloDama(origem,destino):false;break;
		}
		if (movimenta) {
			if((this.qtdMovimentos%2==0 && origem.getPeca().getCor() == 'b')
					|| this.qtdMovimentos%2!=0 && origem.getPeca().getCor() == 'p') {
				
				destino.setPeca(origem.popPeca());
				destino.getPeca().incMovimento();
				this.qtdMovimentos++;
			}
			else {
				System.out.println("Vez do adversário");
			}
		}
	}
	
	public void validaAtaque(Casa atacante, Casa alvo) {
		Boolean ataca = false;
		switch (atacante.getPeca().getTipo().getTipo()) {
		case "peao" :   ataca = ataquePeao(      atacante, alvo    );break;
		case "bispo" :  ataca = movimentoBispo(  atacante,alvo)?puloBispo(atacante,alvo):false;break;
		case "cavalo" : ataca = movimentoCavalo( atacante,alvo);break;
		case "torre" :  ataca = movimentoTorre(  atacante,alvo)?puloTorre(atacante,alvo):false;break;
		case "rei" :    ataca = movimentoRei(    atacante,alvo,false);break;
		case "dama" :   ataca = movimentoDama(   atacante,alvo)?puloDama(atacante,alvo):false;break;
		}
		if(ataca) {
			
		
			
			if((this.qtdMovimentos%2==0 && atacante.getPeca().getCor() == 'b')
					|| this.qtdMovimentos%2!=0 && atacante.getPeca().getCor() == 'p') {
				alvo.popPeca();
				alvo.setPeca(atacante.popPeca());
				alvo.getPeca().incMovimento();
				this.qtdMovimentos++;
			
				}
			}
		
		}
	}


