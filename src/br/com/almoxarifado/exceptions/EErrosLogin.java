package br.com.almoxarifado.exceptions;

public enum EErrosLogin {
	SENHA_INVALIDA ("Senha inv�lida"),
	USUARIO_INVALIDO ("Usu�rio inv�lido");
	
	private final String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
	private EErrosLogin(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return getMensagem();
	}
}
