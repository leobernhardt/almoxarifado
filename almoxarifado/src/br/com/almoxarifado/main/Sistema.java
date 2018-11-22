package br.com.almoxarifado.main;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Login;
import br.com.almoxarifado.utils.Operacoes;
import br.com.menus.Menus;



public class Sistema {
	
	
	public static void main(String[] args) throws NumberFormatException, ConexaoException, DAOException {
		if (Operacoes.possuiUsuario() == false) {
			Operacoes.cadastraUsuarioDefaultSeVazio();
			System.out.println("Foi cadastrado um usuário adiministrados pra você com o login 'login' e a senha senha");

		} 
		while (true) {
			if (Login.usuarioLogin()) {
				 Menus.montaMenuAdm();
			 } else {
				 
				 SistemaUsuario.sistemaUsuario();
			 }
		}
			
		
			
			
			
	}
}
