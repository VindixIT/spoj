package com.vindixit.games.main;

import java.util.Scanner;

import com.vindixit.games.model.Jogador;
/**
 * Classe respons�vel por cadastrar os jogadores, estabelecer a quantidade de jogos, 
 * registrar as jogadas e exibir os resultados ao final. 
 * TODO Ol� IZABEL
 * Queria sugerir como reflex�o:
 * 1 - A classe Jogo poderia se limitar a consultar uma S�mula, e nesta s�mula, 
 * poderiam estar contidos todos os lan�amentos de cada Jogador. 
 * Pensar em criar essa classe POJO (coloquei isso de prop�sito 
 * para que busque conhecimento).
 * 2 - A classe Jogador poderia manter o hist�rico junto a ele, ou seja, 
 * trocar o atributo (field) lancamento por um array de tipo primitivo (qual?).    
 * @author Masaru 
 * @since 19/08/2016 
 */
public class Jogo {

	public static Scanner sc;
	private static int[] JogosJogadorUm;
	private static int[] JogosJogadorDois;

	public static void main(String[] args) throws Exception {

		// Classe respons�vel por manipular e ler o texto que � informado na
		// Console.

		while (true) {
			
			sc = new Scanner(System.in);
			
			int n = informarNPartidas();
			
			if (n == 0) {
				System.out.println("Estou saindo.");
				break;
			}
		
			JogosJogadorUm = new int[n];
			JogosJogadorDois = new int[n];
					
			Jogador jogador1 = informarJogador(1);
			Jogador jogador2 = informarJogador(2);
			
			for (int i = 0; i < n; i++)
			{
				int jogo;
				//jogador1
				System.out.println(jogador1.getNome()+",por favor, informe o n�mero do seu jogo");
				jogo = sc.nextInt();
				if(jogo>5){
					throw new Exception("Somente valores entre 0 e 5 ser�o poss�veis.");
			}
				JogosJogadorUm[i] = jogo;
				//jogador2
				System.out.println(jogador2.getNome()+",por favor, informe o n�mero do seu jogo");
				jogo = sc.nextInt();
				if(jogo>5){
					throw new Exception("Somente valores entre 0 e 5 ser�o poss�veis.");				
			}
				JogosJogadorDois[i] = jogo;
			}
			
			simularPartidas(n, jogador1, jogador2);
		}
	
		sc.close();
		
		}  

		
	private static void simularPartidas(int n, Jogador jogador1, Jogador jogador2) {
		for (int i = 1; i <= n; i++) {
			
			System.out.println("******");
			System.out.println("Teste " + i);
			
			informarJogo(jogador1, JogosJogadorUm[i-1]);
			informarJogo(jogador2, JogosJogadorDois[i-1]);

			boolean resultadoPar = calcularResultado(jogador1,jogador2, false);
			if (resultadoPar) {
				System.out.println("O vencedor eh " + jogador1 + ".");
			} else {
				System.out.println("O vencedor eh " + jogador2 + ".");
			}
			System.out.println(" ");
		}
	}

	private static void informarJogo(Jogador jogador, int valor) {
		jogador.setJogo(valor);
	}
	

	private static boolean calcularResultado(Jogador jogador1, Jogador jogador2, boolean ehSorteio) {

		int jogo1 = jogador1.jogar(ehSorteio);
		int jogo2 = jogador2.jogar(ehSorteio);
		
		exibirParticipantes(jogador1, jogador2, jogo1, jogo2);
		
		int somaLancamentos = jogo1 + jogo2;
		
		int resto = somaLancamentos % 2;
		
		exibirResultado(somaLancamentos, resto);

		// Resto da divis�o por 2 (dois).
		if (resto == 0) {
			// Par
			return true;
		} else {
			// �mpar
			return false;
		}

	}
	
	private static void exibirResultado(int somaLancamentos, int resto) {
		System.out.println("Resultado: "+somaLancamentos+", que � um n�mero "+(resto == 0?" PAR.":" �MPAR."));
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
					throw new Exception("Nome em branco n�o � permitido.");
				} else if (nome.length() > 10) {
					throw new Exception(
							"Comprimento m�ximo de 10 caracteres atingido. Favor limitar o tamanho do nome informado a 10.");
				} else if (nome.indexOf(" ") > -1) {
					throw new Exception("N�o � permitido o uso de espa�os em BRANCO ao informar o nome.");
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
				// Texto solicitando ao usu�rio informar o numero de partidas.
				System.out.println("Informe o n�mero de partidas:");
				// Preencher o numero de partidas na variavel da classe Jogo.
				n = sc.nextInt();
			} catch (Exception ex) {
				System.err.println("Tipo de dado errado. Por favor, informe um n�mero inteiro.");
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
		System.out.println("O n�mero de partidas ser� " + n);
		return n;
	}
		}
		

