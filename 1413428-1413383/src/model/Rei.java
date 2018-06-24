package model;

import java.util.ArrayList;

public class Rei extends Peca {
	ArrayList<Peca> ameacasFuturas;
	ArrayList<Peca> ameacas;
	ArrayList<Integer> distanciaAmeaca;
	public Rei(char cor){
		super(TipoPeca.rei,cor);
		ameacasFuturas = new ArrayList<Peca>();
	}

	public ArrayList <Peca>getAmeacasFuturas(){
		return this.ameacasFuturas;
	}
	public void addAmeacaFutura(Peca p) {
		this.ameacasFuturas.add(p);
	}
	public void limpaAmeacasFuturas() {
		this.ameacasFuturas.clear();
	}
	public void imprimeAmeacasFuturas() {
		//System.out.println("----Ameaca futura ao rei");
		for(int i=0;i<this.ameacasFuturas.size();i++) {
			System.out.print(this.ameacasFuturas.get(i).getTipo().getTipo());
			//System.out.println();
		}
		
	}
	public ArrayList<Peca> getAmeacas() {
		return this.ameacas;
	}
	public int getDistanciaAmeaca(Peca p) {
		if(this.ameacas.contains(p)) {
			int index = ameacas.indexOf(p);
			return this.distanciaAmeaca.get(index);
		}
		return -1;
	}
	public void imprimeAmeacas() {
		System.out.println("----Ameaca ao rei");
		for(int i=0;i<this.ameacas.size();i++) {
			System.out.print(this.ameacas.get(i).getTipo().getTipo());
			System.out.print(this.distanciaAmeaca.get(i));
			System.out.println();
		}
	}
	public void setAmeacas(ArrayList<Peca> alp) {
		this.ameacas = alp;
		this.distanciaAmeaca = new ArrayList<Integer>();
		for(int i=0;i<alp.size();i++) {
			Peca p = alp.get(i);
			int distLinha = Math.abs(this.linha-p.getLinha());
			int distColuna = Math.abs(this.coluna-p.getColuna());
			if(p.getTipo().equals(TipoPeca.bispo)) {
				distanciaAmeaca.add(distLinha);
			}
			else if(p.getTipo().equals(TipoPeca.torre) || p.getTipo().equals(TipoPeca.dama)) {
				
				int dist = distLinha==0?distColuna:distLinha;
				distanciaAmeaca.add(dist);
			}
			else distanciaAmeaca.add(0);
			
		}
		
	}
	public void limpaAmeacas() {
		this.ameacas.clear();
		this.distanciaAmeaca.clear();
	}
	@Override
	public int[][] getMovimentos() {
		int [][]mov = new int[2][10];
				
				mov[0][0] = this.linha - 1;
				mov[1][0] = this.coluna -1;
				
				mov[0][1] = this.linha - 1;
				mov[1][1] = this.coluna;
				
				mov[0][2] = this.linha - 1;
				mov[1][2] = this.coluna + 1;
				
				mov[0][3] = this.linha;
				mov[1][3] = this.coluna - 1;
				
				
				mov[0][4] = this.linha;
				mov[1][4] = this.coluna + 1;
				
				mov[0][5] = this.linha + 1;
				mov[1][5] = this.coluna - 1;
				
				mov[0][6] = this.linha + 1;
				mov[1][6] = this.coluna;
				
				mov[0][7] = this.linha + 1;
				mov[1][7] = this.coluna + 1;
				
				mov[0][8] = this.linha;
				mov[1][8] = this.coluna - 2;
				
				mov[0][9] = this.linha;
				mov[1][9] = this.coluna + 2;
				
			
				
		return mov;
	}

}
