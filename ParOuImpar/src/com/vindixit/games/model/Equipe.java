package com.vindixit.games.model;

public class Equipe implements IConcorrente {

	private String nomeEquipe;
	private int jogadores;

	public Equipe(int jogadores, String nomeEquipe) {

		this.nomeEquipe = nomeEquipe;
		this.jogadores = jogadores;

	}

	public int getJogadores() {
		return jogadores;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

}
