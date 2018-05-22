package model;

public enum TipoPeca {
	
	bispo("bispo"),
	cavalo("cavalo"),
	dama("dama"),
	peao("peao"),
	rei("rei"),
	torre("torre");
	
	private final String tipo;
	
	TipoPeca(String tpPeca){
		tipo = tpPeca;
	}
	
	public String getTipo() {
		return tipo;
	}

}
