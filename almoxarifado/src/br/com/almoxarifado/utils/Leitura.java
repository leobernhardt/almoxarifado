package br.com.almoxarifado.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {
	private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

	public static int leInteiro(int inicio, int fim) {
		while (true) {
			int valor = leInteiro("");
			if (valor >= inicio && valor <= fim) {
				return valor;
			}
			System.out.println("Entre uma opção entre: " + inicio + " e " + fim);
		}
	}

	public static int leInteiro(String mensagem) {
		while (true) {
			try {
				return Integer.parseInt(teclado.readLine());
			} catch (Exception e) {
				
				System.out.println(mensagem);
			}
		}
	}

	public static String leString() {
		while (true) {
			try {
				return teclado.readLine();
			} catch (IOException e) {
				
				System.out.println("Erro!");
			}
		}
	}
	
	public static double leDouble() {
		while (true) {
			try {
				return Double.parseDouble(teclado.readLine());
			} catch (Exception e) {
				System.out.println("Entre com um valor válido!!!");
			}
		}
	}
	public static double leDouble(String mensagem) {
		while (true) {
			try {
				return Double.parseDouble(teclado.readLine());
			} catch (Exception e) {
				System.out.println(mensagem);
			}
		}
	}
}
