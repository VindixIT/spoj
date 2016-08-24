package com.vindixit.games.model;

public class Jogador {
	private String nome;
	private int id;
	private int lancamento;

	public Jogador(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Jogador() {
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int jogar(boolean sorteio) {
		if (sorteio) {
			double random = Math.random();
			this.lancamento = (int) Math.round(IConstantes.MAO * random);
		}
		return this.lancamento;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public void setJogo(int jogo) {
		this.lancamento = jogo;
	}

}
