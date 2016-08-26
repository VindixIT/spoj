package com.vindixit.games.model;

public class Equipe {
	
	public class equipe {
		private String nomeEquipe;
		private int jogadores ;
		


	public equipe (int jogadores, String nomeEquipe) {
		
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


}
