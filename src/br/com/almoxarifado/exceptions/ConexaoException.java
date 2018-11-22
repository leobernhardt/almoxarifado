package br.com.almoxarifado.exceptions;

import br.com.almoxarifado.utils.GeraLog;

public class ConexaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConexaoException(EErrosConexao erro, String mensagem) {
		super(erro.getMensagem() + "#" + mensagem);
		GeraLog log = new GeraLog();
		log.escreveLogArquivo(erro, mensagem);
	}
}
