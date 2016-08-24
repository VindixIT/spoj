package com.vindixit.games.model;

public class Partida {

	private Jogador[] jogadores;
	private int[] lancamentos;
	private Resultado resultado;

	private Jogador vencedor;
	private int somaLancamentos;

	public Partida(Jogador[] jogadores) {
		this.jogadores = jogadores;
		this.lancamentos = new int[jogadores.length];
	}

	public void setSomaLancamentos(int somaLancamentos) {
		this.somaLancamentos = somaLancamentos;
		int resto = somaLancamentos % 2;
		if(0 == resto){
			this.vencedor = jogadores[0];
			resultado = Resultado.PAR;
		} else  {
			this.vencedor = jogadores[1];
			resultado = Resultado.IMPAR;
		}
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public int getSomaLancamentos() {
		return somaLancamentos;
	}

	public void registrar(int lancamento, Jogador jogador) {
		for (int i = 0; i < jogadores.length; i++) {
			if (jogador.equals(jogadores[i])) {
				lancamentos[i] = lancamento;
				break;
			}
		}
	}

	public boolean isTerminada() {
		if (null == vencedor) {
			return false;
		} else {
			return true;
		}
	}

	public Jogador[] getJogadores() {
		return jogadores;
	}

	public int[] getLancamentos() {
		return lancamentos;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public enum Resultado {
		PAR, IMPAR
	}
}
