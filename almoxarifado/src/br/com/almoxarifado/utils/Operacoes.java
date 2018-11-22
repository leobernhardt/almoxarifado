package br.com.almoxarifado.utils;

import br.com.almoxarifado.dao.UsuarioDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Usuario;

public class Operacoes {
	public static boolean possuiUsuario() throws ConexaoException, DAOException {
		return (!new UsuarioDAO().consultaTodos().isEmpty());

	}

	public static void cadastraUsuarioDefaultSeVazio() throws ConexaoException, DAOException {
		if (!possuiUsuario()) {
			System.out.println("Bem vindo pela primeira vez  ao Sistema de Almoxarifado.");
			System.out.println("Informe seu id de funcion�rio para ser ligado ao seu usu�rio:");
			int id = Leitura.leInteiro("Digite um n�mero v�lido!!");
			new UsuarioDAO().insere(new Usuario(id, "login", "senha", true));

		}
	}

	public static void cadastraUsuario()throws ConexaoException, DAOException {
		System.out.println("Informe o id do funcionario que voc� deseja cadastrar");
		int id = Leitura.leInteiro("Digite um n�mero v�lido!!");

	}
}
