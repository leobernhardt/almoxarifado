package br.com.almoxarifado.exceptions;

public enum EErrosConexao {
	ABRE_CONEXAO ("Erro de Conex�o: N�o foi poss�vel estabelecer conex�o com o Banco de Dados."),
	FECHA_CONEXAO ("Erro de Conex�o: N�o foi poss�vel fechar a conex�o com o Banco de Dados.");
	
	private final String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
	private EErrosConexao(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return getMensagem();
	}
}
