package br.fatec.capsula;

import br.fatec.capsula.model.Capsula;
import br.fatec.capsula.model.Integrante;
import br.fatec.capsula.model.Regiao;

public class Main {

	public static void main(String[] args) {
		Capsula cap = new Capsula();
		cap.setId(1L);
		cap.setTitle("ZVBD");
		cap.setAno(2021);
		cap.setDescricao("teste");
		cap.setImageUrl("teste");
		System.out.println(cap);

		Integrante intg = new Integrante();
		intg.setId(1L);
		intg.setNome("Daniele");
		intg.setEmail("dasa@");
		System.out.println(intg);

		Regiao r = Regiao.builder().id(10L).cidade("Santaluz").estado("Bahia").build();
		System.out.println(r);

	}

}
