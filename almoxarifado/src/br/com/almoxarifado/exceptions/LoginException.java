package br.com.almoxarifado.exceptions;

import br.com.almoxarifado.utils.GeraLog;

public class LoginException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LoginException(EErrosLogin erro) {
		System.out.println (erro.getMensagem());
		GeraLog log = new GeraLog();
		log.escreveLogArquivo(erro);
	}
}
