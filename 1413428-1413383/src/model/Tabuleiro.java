package model;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import controller.Movimento;
import model.Casa;
public class Tabuleiro {
	
	private static Tabuleiro t = null;
	int dimCasa = 80;
	int top = 10;
	int left = 10;
	Casa[][] matrizCasas;
	boolean reiAmeacado = false;
	Rei ameacado;
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
						try {
							addPossiveisMovimentos(this.selecionada , m, false);
						}catch (NullPointerException e) {
							// TODO: handle exception
							System.out.println("movimentos");
						}
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
	public void addRange(Casa c,Movimento m) {
		int [][] mov = c.getPeca().getMovimentos();
		int cols = mov[0].length;
		boolean possivel = false;
		//System.out.print(c.getPeca().getTipo().getTipo());
		//System.out.print("["+c.getColuna()+","+c.getLinha()+"] :");
		if(c.getPeca().getTipo().equals(TipoPeca.rei)) {
			Rei r = (Rei) c.getPeca();
			r.imprimeAmeacasFuturas();
		}
		for(int j = 0;j<cols;j++ ) {
			int linhaTabuleiro = mov[1][j];
			int colunaTabuleiro = mov[0][j];
			if(linhaTabuleiro>=0 && linhaTabuleiro <=7 && colunaTabuleiro >=0 && colunaTabuleiro <=7) {
				Casa possivelMovimento = matrizCasas[linhaTabuleiro][colunaTabuleiro];
				
				switch(c.getPeca().getTipo().getTipo()) {
				case "bispo": possivel = m.movimentoBispo(c, possivelMovimento);break;
				case "torre": possivel = m.movimentoTorre(c, possivelMovimento);break;
				case "dama": possivel = m.movimentoDama(c, possivelMovimento);break;
				default:possivel = false;break;
				}
				
				if(possivel && possivelMovimento.isOcupada() ) {
					if(true/*possivelMovimento.getPeca().getTipo().equals(TipoPeca.rei)	&& possivelMovimento.getPeca().getCor()!=c.getPeca().getCor()*/) {
						//System.out.print("["+possivelMovimento.getColuna()+","+possivelMovimento.getLinha()+"]");
						possivelMovimento.pretenderPeca(c.getPeca());
						//Rei r = (Rei)possivelMovimento.getPeca();
						//r.addAmeacaFutura(c.getPeca());
					}
				}
			}
		}
		//System.out.println();
		
	}
	public void addPossiveisMovimentos(Casa c, Movimento m, boolean futuro) {
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
						if(futuro) {
							possivelMovimento.permitePeca(c.getPeca());
							c.getPeca().permiteCasa(possivelMovimento);
						}
						else if(!this.reiAmeacado) {
							if(!previneXeque(c, true)) {
								possivelMovimento.permiteMovimento(c.getPeca());
							}
						}
						else if(this.reiAmeacado && c.getPeca().getCor()!=this.ameacado.getCor()) { 
							possivelMovimento.permiteMovimento(c.getPeca());
						} 
						else impedeXeque(c,possivelMovimento, false); 
						
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
						if(futuro) {
							possivelMovimento.permitePeca(c.getPeca());
							c.getPeca().permiteCasa(possivelMovimento);
						}
						else if(!this.reiAmeacado || c.getPeca().getCor()!=this.ameacado.getCor()) {
							possivelMovimento.permiteMovimento(c.getPeca());
							c.getPeca().permiteCasa(possivelMovimento);
						}
						else impedeXeque(c,possivelMovimento, true);
						
					}
				}
			}
		}
	}
	public boolean previneXeque(Casa c, boolean inimigo) {//se inimigo==true então a peça verificada impede um xeque inimigo portanto não pode se mexer
															//se inimigo==false então a peça impede um xeque amigo portanto não deve ser tomada pelo rei
		Casa casaRei=null;
		Peca possivelAtacante;
		Peca p = c.getPeca();
		char cor = p.getCor();
		Rei r = null;
		ArrayList<Peca> range = null;
		boolean achouRei=false;
		
		/*
		 * Deve verificar se a peça p está previnido um xeque, ou seja, verificar se o rei está no range de alguma peça inimiga (dama, bispo, ou torre)
		 * caso esteja, e se a mesma peça p está no range desta peça que pode atacar seu rei, e ainda se está na mesma direção de 
		 * ação da peça inimiga que pode por o rei em xeque. Se esta peça p for a unica que previne o xeque desta peça inimiga então ela não pode 
		 * ser movimentada.
		 * 
		 * */
		
		// rotina para achar o rei : se inimigo==true devemos achar o rei com a mesma cor da peça verificada
		// se inimigo ==false devemos achar o rei com a cor diferente da peça verificada
		
		for(int i=0;i<8;i++) {
			for(int j =0;j<8;j++) {
				Casa verificada = matrizCasas[i][j];
				if(verificada.isOcupada()) {
					if(verificada.getPeca().getTipo().equals(TipoPeca.rei)) {
						if(inimigo) {
							if(verificada.getPeca().getCor()==c.getPeca().getCor()) {
								casaRei=verificada;
								if(casaRei.getRange().size()>0) {
									range = casaRei.getRange();
									r=(Rei)casaRei.getPeca();
									achouRei=true;
								}
							}
						}
						else {
							if(verificada.getPeca().getCor()!=c.getPeca().getCor()) {
								casaRei=verificada;
								if(casaRei.getRange().size()>0) {
									range = casaRei.getRange();
									r=(Rei)casaRei.getPeca();
									achouRei=true;
								}
							}
							
						}
					}
				}
			}
		}
		if(achouRei) {
			for(int k=0;k<range.size();k++) {
				
				//System.out.println(range.size());
				if(c.getRange().contains(range.get(k))) {// se a peça verificada está no range da peça que ameaça o rei
					
					if((inimigo && range.get(k).getCor()!=c.getPeca().getCor()) || //se inimigo==false então a peça verificada deve ter a mesma cor da peça que ameaça o rei
							(!inimigo && range.get(k).getCor()==c.getPeca().getCor())) {//se inimigo==true então a peça verificada deve ter cor diferente da peça que ameaça o rei
						System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
						int distLinha = range.get(k).getLinha()-r.getLinha();
						int distMovLinha = range.get(k).getLinha()-c.getLinha();
						int distColuna = range.get(k).getColuna()-r.getColuna();
						int distMovColuna = range.get(k).getColuna()-c.getColuna();
						
						if(range.get(k).getTipo().equals(TipoPeca.torre)) {
							if(distColuna==0 && distLinha > 0) {
								if(distMovColuna==0 && distMovLinha > 0) {
									if(distMovLinha<distLinha) {
										return true;										
									}
								}
							}
							else if(distColuna==0 && distLinha < 0) {
								if(distMovColuna==0 && distMovLinha < 0) {
									if(distMovLinha>distLinha) {
										return true;
									}
								}
							}
							else if(distColuna>0) {
								if(distMovColuna>0 && distMovLinha == 0) {
									if(distMovColuna<distColuna) {
										return true;
									}
								}
							}
							else {
								if(distMovColuna<0 && distMovLinha == 0) {
									if(distMovColuna>distColuna) {
										return true;
									}
								}
							}
							
							
						}
						else if(range.get(k).getTipo().equals(TipoPeca.bispo)) {
							if(distLinha >0 && distColuna >0) {
								if(distMovLinha>0 && distMovColuna>0) {
									if(distMovLinha<distLinha && distMovColuna<distColuna) {
										return true;										
									}
								}
							}
							else if(distLinha <0 && distColuna <0) {
								if(distMovLinha<0 && distMovColuna<0) {
									if(distMovLinha>distLinha && distMovColuna>distColuna) {
										return true;										
									}
								}
							}
							else if(distLinha <0 && distColuna >0) {
								if(distMovLinha<0 && distMovColuna>0) {
									if(distMovLinha>distLinha && distMovColuna<distColuna) {
										return true;										
									}
								}
							}
							else {
								if(distMovLinha>0 && distMovColuna<0) {
									if(distMovLinha<distLinha && distMovColuna>distColuna) {
										return true;										
									}
								}
							}
							
						}
						else if(range.get(k).getTipo().equals(TipoPeca.dama)) {
							if(distLinha >0 && distColuna >0) {
								if(distMovLinha>0 && distMovColuna>0) {
									if(distMovLinha<distLinha && distMovColuna<distColuna) {
										return true;										
									}
								}
							}
							else if(distLinha <0 && distColuna <0) {
								if(distMovLinha<0 && distMovColuna<0) {
									if(distMovLinha>distLinha && distMovColuna>distColuna) {
										return true;										
									}
								}
							}
							else if(distLinha <0 && distColuna >0) {
								if(distMovLinha<0 && distMovColuna>0) {
									if(distMovLinha>distLinha && distMovColuna<distColuna) {
										return true;										
									}
								}
							}
							else if(distLinha>0 && distColuna<0){
								if(distMovLinha>0 && distMovColuna<0) {
									if(distMovLinha<distLinha && distMovColuna>distColuna) {
										return true;										
									}
								}
							}
							if(distColuna==0 && distLinha > 0) {
								if(distMovColuna==0 && distMovLinha > 0) {
									if(distMovLinha<distLinha) {
										return true;										
									}
								}
							}
							else if(distColuna==0 && distLinha < 0) {
								if(distMovColuna==0 && distMovLinha < 0) {
									if(distMovLinha>distLinha) {
										return true;
									}
								}
							}
							else if(distColuna>0 && distLinha == 0) {
								if(distMovColuna>0 && distMovLinha == 0) {
									if(distMovColuna<distColuna) {
										return true;
									}
								}
							}
							else if(distColuna<0 && distLinha == 0) {
								if(distMovColuna<0 && distMovLinha == 0) {
									if(distMovColuna>distColuna) {
										return true;
									}
								}
							}
						}
					}
					
					
					
				}
			}
		}
		/*
		for(int i=0;i<8;i++) {
			for(int j =0;j<8;j++) {
				if(this.matrizCasas[i][j].isOcupada()) {
					if(this.matrizCasas[i][j].getPeca().getTipo().equals(TipoPeca.rei) /*&& c.getPeca().getCor()==p.getCor()) {
						r = (Rei) cRei.getPeca();
						if(r.getAmeacasFuturas().size()>0)
						cRei = this.matrizCasas[i][j];
						
						range = cRei.rangePecas;
						achouRei=true;
					}
				}	
			}
		}
		System.out.println(r.getCor());
		System.out.println(range.size());
		if(achouRei && range.size()>0) {//existem peças que podem atacar o rei da posição em que se encontram caso haja uma mudança nas peças
			cRei.imprimePossiveisPecas();
			for(int k =0;k<range.size();k++) {//procura as peças que podem atacar o rei
				if(range.get(k).getPossiveisCasas().contains(c) 
						&& range.get(k).getCor()!=r.getCor()) {//uma das peças que podem atacar o rei podem tbm tomar a peça verificada
					
					int distLinha = range.get(0).getLinha()-r.getLinha();
					int distMovLinha = range.get(0).getLinha()-c.getLinha();
					int distColuna = range.get(0).getColuna()-r.getColuna();
					int distMovColuna = range.get(0).getColuna()-c.getColuna();
					if(range.get(k).getTipo().equals(TipoPeca.torre)) {
						
						if(distColuna==0 && distLinha > 0) {
							if(distMovColuna==0 && distMovLinha > 0) {
								if(distMovLinha<distLinha) {
									return true;										
								}
							}
						}
						else if(distColuna==0 && distLinha < 0) {
							if(distMovColuna==0 && distMovLinha < 0) {
								if(distMovLinha>distLinha) {
									return true;
								}
							}
						}
						else if(distColuna>0) {
							if(distMovColuna>0 && distMovLinha == 0) {
								if(distMovColuna<distColuna) {
									return true;
								}
							}
						}
						else {
							if(distMovColuna<0 && distMovLinha == 0) {
								if(distMovColuna>distColuna) {
									return true;
								}
							}
						}
					}
					else if(range.get(k).getTipo().equals(TipoPeca.bispo)) {
						if(distLinha >0 && distColuna >0) {
							if(distMovLinha>0 && distMovColuna>0) {
								if(distMovLinha<distLinha && distMovColuna<distColuna) {
									return true;										
								}
							}
						}
						else if(distLinha <0 && distColuna <0) {
							if(distMovLinha<0 && distMovColuna<0) {
								if(distMovLinha>distLinha && distMovColuna>distColuna) {
									return true;										
								}
							}
						}
						else if(distLinha <0 && distColuna >0) {
							if(distMovLinha<0 && distMovColuna>0) {
								if(distMovLinha>distLinha && distMovColuna<distColuna) {
									return true;										
								}
							}
						}
						else {
							if(distMovLinha>0 && distMovColuna<0) {
								if(distMovLinha<distLinha && distMovColuna>distColuna) {
									return true;										
								}
							}
						}
						
					}
					else if(range.get(k).getTipo().equals(TipoPeca.dama)) {
						if(distLinha >0 && distColuna >0) {
							if(distMovLinha>0 && distMovColuna>0) {
								if(distMovLinha<distLinha && distMovColuna<distColuna) {
									return true;										
								}
							}
						}
						else if(distLinha <0 && distColuna <0) {
							if(distMovLinha<0 && distMovColuna<0) {
								if(distMovLinha>distLinha && distMovColuna>distColuna) {
									return true;										
								}
							}
						}
						else if(distLinha <0 && distColuna >0) {
							if(distMovLinha<0 && distMovColuna>0) {
								if(distMovLinha>distLinha && distMovColuna<distColuna) {
									return true;										
								}
							}
						}
						else if(distLinha>0 && distColuna<0){
							if(distMovLinha>0 && distMovColuna<0) {
								if(distMovLinha<distLinha && distMovColuna>distColuna) {
									return true;										
								}
							}
						}
						if(distColuna==0 && distLinha > 0) {
							if(distMovColuna==0 && distMovLinha > 0) {
								if(distMovLinha<distLinha) {
									return true;										
								}
							}
						}
						else if(distColuna==0 && distLinha < 0) {
							if(distMovColuna==0 && distMovLinha < 0) {
								if(distMovLinha>distLinha) {
									return true;
								}
							}
						}
						else if(distColuna>0 && distLinha == 0) {
							if(distMovColuna>0 && distMovLinha == 0) {
								if(distMovColuna<distColuna) {
									return true;
								}
							}
						}
						else if(distColuna<0 && distLinha == 0) {
							if(distMovColuna<0 && distMovLinha == 0) {
								if(distMovColuna>distColuna) {
									return true;
								}
							}
						}
					}
					
				}
			}
		}*/
		return false;
	}
	public void impedeXeque(Casa c,Casa possivelMovimento, boolean ataque) {
		int alternativas = 0;
		Peca p = c.getPeca();
		Rei r = this.ameacado;
		ArrayList<Peca> alp = r.getAmeacas();
		int n = alp.size();
		System.out.println(n);
		r.imprimeAmeacas();
		if(p.equals(this.ameacado)) {//possiveis movimentos para o rei quando eles está em xeque
			
			int inimigos=0;
			for(int i=0 ; i<possivelMovimento.getPossiveisPecas().size();i++) {
				if(possivelMovimento.getPossiveisPecas().get(i).getCor()!=r.getCor()) {
					inimigos++;
				}
			}
			if(inimigos==0) {
				if(possivelMovimento.isOcupada()) {
					if(!previneXeque(possivelMovimento, false)) {
						alternativas++;
						possivelMovimento.permiteMovimento(p);
						p.permiteCasa(possivelMovimento);
					}
				}
				else {
					alternativas++;
					possivelMovimento.permiteMovimento(p);
					p.permiteCasa(possivelMovimento);
				}
				//alternativas++;
				//possivelMovimento.permiteMovimento(p);
				//p.permiteCasa(possivelMovimento);
			}
		}
		else {
			if(n==1) {//apenas uma peça está deixando o rei em xeque
				if(ataque) {
					if(possivelMovimento.getPeca().equals(alp.get(0))) {//Se é possível tomar a peça que está dando xeque
						alternativas++;
						possivelMovimento.permiteMovimento(p);
						p.permiteCasa(possivelMovimento);
					}
				}
				else {// A peça que está dando xeque é uma TORRE
					if(alp.get(0).getTipo().equals(TipoPeca.torre)) {//origem:alp.get(0) destino:r
						if(alp.get(0).getPossiveisCasas().contains(possivelMovimento)) {
							int distLinha = alp.get(0).getLinha()-r.getLinha();
							int distMovLinha = alp.get(0).getLinha()-possivelMovimento.getLinha();
							int distColuna = alp.get(0).getColuna()-r.getColuna();
							int distMovColuna = alp.get(0).getColuna()-possivelMovimento.getColuna();
							if(distColuna==0 && distLinha > 0) {
								if(distMovColuna==0 && distMovLinha > 0) {
									if(distMovLinha<distLinha) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distColuna==0 && distLinha < 0) {
								if(distMovColuna==0 && distMovLinha < 0) {
									if(distMovLinha>distLinha) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							else if(distColuna>0) {
								if(distMovColuna>0 && distMovLinha == 0) {
									if(distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							else {
								if(distMovColuna<0 && distMovLinha == 0) {
									if(distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							
						}
					}
					if(alp.get(0).getTipo().equals(TipoPeca.bispo)) {//A peça que está dando xeque é um BISPO
						if(alp.get(0).getPossiveisCasas().contains(possivelMovimento)) {//origem:alp.get(0) destino:r
							//System.out.println("Verificou a ameaca do bispo");
							int distLinha = alp.get(0).getLinha()-r.getLinha();
							int distMovLinha = alp.get(0).getLinha()-possivelMovimento.getLinha();
							int distColuna = alp.get(0).getColuna()-r.getColuna();
							int distMovColuna = alp.get(0).getColuna()-possivelMovimento.getColuna();
							if(distLinha >0 && distColuna >0) {
								if(distMovLinha>0 && distMovColuna>0) {
									if(distMovLinha<distLinha && distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distLinha <0 && distColuna <0) {
								if(distMovLinha<0 && distMovColuna<0) {
									if(distMovLinha>distLinha && distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distLinha <0 && distColuna >0) {
								if(distMovLinha<0 && distMovColuna>0) {
									if(distMovLinha>distLinha && distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else {
								if(distMovLinha>0 && distMovColuna<0) {
									if(distMovLinha<distLinha && distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
						}
						
					}
					if(alp.get(0).getTipo().equals(TipoPeca.dama)) {//A peça que está dando xeque é uma DAMA
						if(alp.get(0).getPossiveisCasas().contains(possivelMovimento)) {//origem:alp.get(0) destino:r
							int distLinha = alp.get(0).getLinha()-r.getLinha();
							int distMovLinha = alp.get(0).getLinha()-possivelMovimento.getLinha();
							int distColuna = alp.get(0).getColuna()-r.getColuna();
							int distMovColuna = alp.get(0).getColuna()-possivelMovimento.getColuna();
							if(distLinha >0 && distColuna >0) {
								if(distMovLinha>0 && distMovColuna>0) {
									if(distMovLinha<distLinha && distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distLinha <0 && distColuna <0) {
								if(distMovLinha<0 && distMovColuna<0) {
									if(distMovLinha>distLinha && distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distLinha <0 && distColuna >0) {
								if(distMovLinha<0 && distMovColuna>0) {
									if(distMovLinha>distLinha && distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distLinha>0 && distColuna<0){
								if(distMovLinha>0 && distMovColuna<0) {
									if(distMovLinha<distLinha && distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							if(distColuna==0 && distLinha > 0) {
								if(distMovColuna==0 && distMovLinha > 0) {
									if(distMovLinha<distLinha) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);										
									}
								}
							}
							else if(distColuna==0 && distLinha < 0) {
								if(distMovColuna==0 && distMovLinha < 0) {
									if(distMovLinha>distLinha) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							else if(distColuna>0 && distLinha == 0) {
								if(distMovColuna>0 && distMovLinha == 0) {
									if(distMovColuna<distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							else if(distColuna<0 && distLinha == 0) {
								if(distMovColuna<0 && distMovLinha == 0) {
									if(distMovColuna>distColuna) {
										possivelMovimento.permiteMovimento(p);
										p.permiteCasa(possivelMovimento);
									}
								}
							}
							
						}
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
		
	}
	public void negaXeque() {
		this.reiAmeacado = false; 
	}
	public void setReiAmeacado(Rei r) {
		this.ameacado = r;
		this.reiAmeacado = true;
		r.imprimeAmeacas();
	}
	public Rei getReiAmeacado() {
		return this.ameacado;
	}
	public boolean verificaAmecaRei() {
		return this.reiAmeacado;
	}
	public void preencheRange() {
		Movimento m = new Movimento(this.matrizCasas);
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.matrizCasas[i][j].limpaRange();
			
			}
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
				if(this.matrizCasas[i][j].isOcupada()) {
					try {
						addRange(this.matrizCasas[i][j],  m);
					}catch (NullPointerException e) {
					// TODO: handle exception
					System.out.println("range");
					}
				}
			}
		}
	}
	public void addPossiveisPecas() {
		Movimento m = new Movimento(this.matrizCasas);
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.matrizCasas[i][j].limpaPossiveisPecas();
			
			}
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
				if(this.matrizCasas[i][j].isOcupada()) {
					try {
						addPossiveisMovimentos(this.matrizCasas[i][j],  m, true);
					}catch (NullPointerException e) {
					// TODO: handle exception
					System.out.println("pecas");
					}
				}
			}
		}
		//System.out.println("---------------------pecas---------------------------");
		/*for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
				if(this.matrizCasas[i][j].isOcupada()) {
					Peca p = matrizCasas[i][j].getPeca();
					System.out.print(p.getCor()+"_"+p.getTipo()+"["+i+","+j+"] :");
					p.imprimePossiveisCasas();;
					System.out.println();
				}
			}
		}
		System.out.println("---------------------pecas---------------------------");*/
	}
	
}
	
	


