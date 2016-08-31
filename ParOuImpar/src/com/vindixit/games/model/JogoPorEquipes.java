package com.vindixit.games.model;

import java.util.Scanner;

public class JogoPorEquipes implements IJogo {

	private Scanner sc;
	private Equipe[] equipes;
	
	public JogoPorEquipes(Scanner sc) {
		this.sc = sc;
	}
	
	@Override
	public void informarConcorrentes() {
		equipes = informarEquipes(2);
	}

	private Equipe[] informarEquipes(int numero) {
		Equipe[] equipes = new Equipe[numero];
		for (int i = 0; i < numero; i++) {
			Equipe equipe = informarEquipe(i + 1);
			equipes[i] = equipe;
		}
		return equipes;
	}

	private Equipe informarEquipe(int id) {
		String nome = null;
		while (true) {
			try {
				System.out.println("Informe o nome da Equipe " + id + ":");
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
		return new Equipe(id, nome);
	}

	@Override
	public Sumula jogar(int n) {
		return null;
	}

}
