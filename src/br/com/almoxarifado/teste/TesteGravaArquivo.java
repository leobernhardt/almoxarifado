package br.com.almoxarifado.teste;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TesteGravaArquivo {
	public static void main(String[] args) throws IOException {

		BufferedReader arquivo = new BufferedReader(new InputStreamReader(new FileInputStream("c:/lixo/dados.txt")));
		String linha = null;
		while((linha = arquivo.readLine()) != null) {
			System.out.println(linha);
			
		}
		arquivo.close();
	}
}
