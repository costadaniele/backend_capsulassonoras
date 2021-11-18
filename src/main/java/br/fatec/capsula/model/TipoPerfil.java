package br.fatec.capsula.model;

public enum TipoPerfil {
	ADMIN(1, "ROLE_ADMIN"), INTEGRANTE(2, "ROLE_CLIENTE");

	private Integer cod;
	private String desc;

	private TipoPerfil(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static TipoPerfil toEnum(Integer cod) {
		if (cod == null)
			return null;
		for (TipoPerfil x : TipoPerfil.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}