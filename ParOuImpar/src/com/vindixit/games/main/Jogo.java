package com.vindixit.games.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.vindixit.games.model.Jogador;

public class Jogo {

	public static Scanner sc;

	public static void main(String[] args) {

		// Classe responsável por manipular e ler o texto que é informado na
		// Console.

		while (true) {
			
			sc = new Scanner(System.in);
			
			int n = informarNPartidas();
			
			if (n == 0) {
				System.out.println("Estou saindo.");
				break;
			}
			
			Jogador jogador1 = informarJogador(1);
			Jogador jogador2 = informarJogador(2);
			
			simularPartidas(n, jogador1, jogador2);
			
		}
		sc.close();
	}

	private static void simularPartidas(int n, Jogador jogador1, Jogador jogador2) {
		for (int i = 1; i <= n; i++) {
			
			System.out.println("******");
			System.out.println("Teste " + i);
			
			informarJogo(jogador1);
			informarJogo(jogador2);

			boolean resultadoPar = calcularResultado(jogador1,jogador2, false);
			if (resultadoPar) {
				System.out.println("O vencedor eh " + jogador1 + ".");
			} else {
				System.out.println("O vencedor eh " + jogador2 + ".");
			}
			System.out.println(" ");
		}
	}

	private static void informarJogo(Jogador jogador) {
		int jogo = 0;
		while (true) {
			try {
				System.out.println(jogador.getNome()+", por favor, informe o número do seu jogo:");
				jogo = sc.nextInt();
				if(jogo>5){
					throw new Exception("Somente valores entre 0 e 5 serão possíveis.");
				}
				jogador.setJogo(jogo);
			} catch(InputMismatchException ex){
				String message = "Por favor, informe somente números.";
				System.err.println(message);
				sc.next();
				continue;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;
			}
			break;
		}
	}

	private static boolean calcularResultado(Jogador jogador1, Jogador jogador2, boolean ehSorteio) {

		int jogo1 = jogador1.jogar(ehSorteio);
		int jogo2 = jogador2.jogar(ehSorteio);
		
		exibirParticipantes(jogador1, jogador2, jogo1, jogo2);
		
		int somaLancamentos = jogo1 + jogo2;
		
		int resto = somaLancamentos % 2;
		
		exibirResultado(somaLancamentos, resto);

		// Resto da divisão por 2 (dois).
		if (resto == 0) {
			// Par
			return true;
		} else {
			// Ímpar
			return false;
		}

	}
	
	private static void exibirResultado(int somaLancamentos, int resto) {
		System.out.println("Resultado: "+somaLancamentos+", que é um número "+(resto == 0?" PAR.":" ÍMPAR."));
	}

	private static void exibirParticipantes(Jogador jogador1, Jogador jogador2, int jogo1, int jogo2) {
		System.out.println("Jogador 1: "+jogador1.getNome() +" jogou "+jogo1+".");
		System.out.println("Jogador 2: "+jogador2.getNome() +" jogou "+jogo2+".");
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
				/*
				 * When a scanner throws an InputMismatchException, the scanner
				 * will not pass the token that caused the exception, so that it
				 * may be retrieved or skipped via some other method.
				 */
				sc.next();
				continue;
			}
			break;
		}
		return new Jogador(id, nome);
	}

	private static int informarNPartidas() {
		int n = 0;
		while (true) {
			try {
				// Texto solicitando ao usuário informar o numero de partidas.
				System.out.println("Informe o número de partidas:");
				// Preencher o numero de partidas na variavel da classe Jogo.
				n = sc.nextInt();
			} catch (Exception ex) {
				System.err.println("Tipo de dado errado. Por favor, informe um número inteiro.");
				/*
				 * When a scanner throws an InputMismatchException, the scanner
				 * will not pass the token that caused the exception, so that it
				 * may be retrieved or skipped via some other method.
				 */
				sc.next();
				continue;
			}
			break;
		}
		System.out.println("O número de partidas será " + n);
		return n;
	}

