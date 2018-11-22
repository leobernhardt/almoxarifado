package br.com.almoxarifado.exceptions;

import br.com.almoxarifado.utils.GeraLog;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(EErrosDAO erro, String mensagem, Class<?> classe) {
		super(erro.getMensagem() + classe.getName() + "#" + mensagem);
		GeraLog log = new GeraLog();
		log.escreveLogArquivo(erro, classe.getName(), mensagem);
	}
}
