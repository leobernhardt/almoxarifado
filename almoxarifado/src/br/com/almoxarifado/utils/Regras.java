package br.com.almoxarifado.utils;

import br.com.almoxarifado.dao.UsuarioDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.exceptions.EErrosLogin;
import br.com.almoxarifado.exceptions.LoginException;
import br.com.almoxarifado.model.Usuario;


public class Regras {
	
	public static boolean validaLogin(int usuario, String senha) throws LoginException, ConexaoException, DAOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (!(usuarioDAO.consulta(usuario) instanceof Usuario)) throw (new LoginException(EErrosLogin.USUARIO_INVALIDO));
		if (!(usuarioDAO.consulta(usuario).getSenha().equals(senha))) throw (new LoginException(EErrosLogin.SENHA_INVALIDA));
		return usuarioDAO.consulta(usuario).isAdm();
	}

}
