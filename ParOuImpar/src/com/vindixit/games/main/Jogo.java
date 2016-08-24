package com.vindixit.games.main;

import java.util.Scanner;

import com.vindixit.games.model.Jogador;
import com.vindixit.games.model.Partida;
import com.vindixit.games.model.Sumula;

/**
 * Classe responsável por cadastrar os jogadores, estabelecer a quantidade de
 * jogos, registrar as jogadas e exibir os resultados ao final. 
 * 
 * @author Masaru
 * @since 24/08/2016
 */
public class Jogo {

	public static Scanner sc;

	public static void main(String[] args) throws Exception {

		Sumula sumula = null;

		while (true) {

			sc = new Scanner(System.in);

			int n = 0;

			if (0 == (n = informarNumeroDePartidas())) {
				System.out.println("Estou saindo.");
				break;
			}

			Jogador[] jogadores = informarJogadores(2);

			sumula = new Sumula(n);

			jogar(jogadores, sumula);

			System.out.println(sumula.publicar());
		}

		sc.close();

	}

	private static void jogar(Jogador[] jogadores, Sumula sumula) throws Exception {

		for (int i = 0; i < sumula.getQtdPartidas(); i++) {

			int lancamento = 0;
			int somaLancamentos = 0;
			Partida partida = new Partida(jogadores);
			System.out.println("......");
			System.out.println("PARTIDA "+(i+1));

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
	}
	
	private static Jogador[] informarJogadores(int numero) {
		Jogador[] jogadores = new Jogador[numero];
		for (int i = 0; i < numero; i++) {
			Jogador jogador = informarJogador(i+1);
			jogadores[i] = jogador;
		}
		return jogadores;
	}

	private static Jogador informarJogador(int id) {
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

	private static int informarNumeroDePartidas() {
		int n = 0;
		while (true) {
			try {
				// Texto solicitando ao usuário informar o numero de partidas.
				System.out.println("Informe o número de partidas:");
				// Preencher o numero de partidas na variavel da classe Jogo.
				n = sc.nextInt();
			} catch (Exception ex) {
				System.err.println("Tipo de dado errado. Por favor, informe um número inteiro.");
				sc.next();
				continue;
			}
			break;
		}
		System.out.println("O número de partidas será " + n);
		return n;
	}
}
