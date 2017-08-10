package com.salvavidas.model;

public enum Provincia {
	
	Hla("Huíla"),
	Nb("Namibe"),
	Lda("Luanda"),
	Bg("Benguela"),
	Mlj("Malanje"),
	Bie("Bié"),
	Kk("Cuando Cubango"),
	Cbda("Cabinda"),
	Ugi("Uigé"),
	Hb("Huambo"),
	Mux("Muchico"),
	Zr("Zaire"),
	Ks("Cuanza Sul"),
	Kn("Cuanza Norte"),
	Cn("Cunene"),
	Ln("Lunda Norte"),
	Ls("Lunda Sul"),
	Bng("Bengo");
	
	private String descricao;

	Provincia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
