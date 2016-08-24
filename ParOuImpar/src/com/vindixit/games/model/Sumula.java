package com.vindixit.games.model;

/**
 * Classe responsável por armazenar as informações dos jogadores, lançamentos e resultados.
 * @author Masaru
 *
 */
public class Sumula {

	private Partida[] partidas;

	public Sumula(int n) {
		this.partidas = new Partida[n];
	}

	public int getQtdPartidas() {
		return partidas.length;
	}

	public void registrar(int numero, Partida partida) {
		partidas[numero] = partida;
	}

	public String publicar() {
		String str = "";
		for (int i = 0; i < partidas.length; i++) {
			cabecalho(i);
			Partida partida = partidas[i];
			if(partida.isTerminada()){
				Jogador[] jogadores = partida.getJogadores();
				int[] lancamentos = partida.getLancamentos();
				for (int j = 0; j < jogadores.length; j++) {
					str += "Jogador "+j+": "+jogadores[j].getNome() +" jogou "+lancamentos[j]+".\n";
				}
				int total = partida.getSomaLancamentos();
				str += "Resultado: "+total+", que é um número "+partida.getResultado()+"\n";
			}
			str += "O vencedor eh " + partida.getVencedor()+".\n";
		}
		str += "------\n";
		return str;
	}

	private String cabecalho(int i) {
		String str = "******\n";
		return str += "Partida " + i+"\n";
	}

}
