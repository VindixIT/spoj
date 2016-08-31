package com.vindixit.games.model;

import java.util.Scanner;

public class JogoIndividual implements IJogo {

	
	private Scanner sc;
	private Jogador[] jogadores;

	public JogoIndividual(Scanner sc) {
		this.sc = sc;
	}

	public void informarConcorrentes() {
		jogadores = informarJogadores(2);
	}


	private Jogador[] informarJogadores(int numero) {
		Jogador[] jogadores = new Jogador[numero];
		for (int i = 0; i < numero; i++) {
			Jogador jogador = informarJogador(i + 1);
			jogadores[i] = jogador;
		}
		return jogadores;
	}

	private Jogador informarJogador(int id) {
		String nome = null;
		while (true) {
			try {
				System.out.println("Informe o nome do Jogador " + id + ":");
				nome = sc.next();
				if (null == nome || "".equals(nome)) {
					throw new Exception("Nome em branco não é permitido.");
				} else if (nome.length() > 10) {
					throw new Exception(
							"Comprimento máximo de 10 caracteres atingido. Favor limitar o tamanho do nome informado a 10.");
				} else if (nome.indexOf(" ") > -1) {
					throw new Exception("Não é permitido o uso de espaços em BRANCO ao informar o nome.");
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				sc.next();
				continue;
			}
			break;
		}
		return new Jogador(id, nome);
	}
	@Override
	public Sumula jogar(int n) {
		Sumula sumula = new Sumula(n);
		for (int i = 0; i < sumula.getQtdPartidas(); i++) {

			int lancamento = 0;
			int somaLancamentos = 0;
			Partida partida = new Partida(jogadores);
			System.out.println("......");
			System.out.println("PARTIDA " + (i + 1));

			for (int j = 0; j < 2; j++) {

				Jogador jogador = jogadores[j];

				System.out.println(jogador.getNome() + ", por favor, informe o número correspondente ao seu jogo: ");

				// Pede para informar o seu lançamento.
				lancamento = sc.nextInt();
				if (lancamento > 5) {
					System.err.println("Somente valores entre 0 e 5 serão possíveis.");
					sc.next();
				}

				partida.registrar(lancamento, jogador);
				somaLancamentos += lancamento;

			}
			// Resto da divisão por 2 (dois). O primeiro jogador é sempre PAR.
			partida.setSomaLancamentos(somaLancamentos);

			sumula.registrar(i, partida);
		}
		return sumula;		
	}


}
