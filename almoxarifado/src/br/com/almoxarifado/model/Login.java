package br.com.almoxarifado.model;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.exceptions.LoginException;
import br.com.almoxarifado.utils.Leitura;
import br.com.almoxarifado.utils.Regras;

public class Login {
	public static String login;
	public static String senha;
	public static boolean usuarioLogin() throws NumberFormatException, ConexaoException, DAOException {
		while (true) {
			System.out.println("Digite seu usuário: ");
			login = Leitura.leString();
			System.out.println("Digite sua senha: ");
			senha = Leitura.leString();
			try {
				return Regras.validaLogin(Integer.valueOf(login), senha);
				
			} catch (LoginException e) {
				e.getMessage();
			}
		}
	}
}
