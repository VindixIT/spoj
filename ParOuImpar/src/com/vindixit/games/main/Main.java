package com.vindixit.games.main;

import java.util.Scanner;

import com.vindixit.games.model.Equipe;
import com.vindixit.games.model.IConcorrente;
import com.vindixit.games.model.IJogo;
import com.vindixit.games.model.Jogador;
import com.vindixit.games.model.JogoIndividual;
import com.vindixit.games.model.JogoPorEquipes;
import com.vindixit.games.model.Partida;
import com.vindixit.games.model.Sumula;

import sun.nio.cs.ext.ISCII91;

/**
 * Classe responsável por cadastrar os jogadores, estabelecer a quantidade de
 * jogos, registrar as jogadas e exibir os resultados ao final.
 * 
 * @author Masaru
 * @since 24/08/2016
 */
public class Main {

	public static Scanner sc;
	private static boolean isJogoPorEquipes = false;

	public static void main(String[] args) throws Exception {

		Sumula sumula = null;

		while (true) {

			sc = new Scanner(System.in);

			IJogo jogo = selecionarTipoJogo();

			int n = 0;
			if (0 == (n = informarNumeroDePartidas())) {
				System.out.println("Estou saindo.");
				break;
			}

			jogo.informarConcorrentes();

			sumula = jogo.jogar(n);

			System.out.println(sumula.publicar());
		}

		sc.close();

	}

	private static IJogo selecionarTipoJogo() {
		IJogo jogo = null;
		if (isJogoPorEquipes) {
			jogo = new JogoPorEquipes(sc);
		} else {
			jogo = new JogoIndividual(sc);
		}
		return jogo;
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
