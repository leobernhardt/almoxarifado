package br.com.almoxarifado.exceptions;

public enum EErrosLogin {
	SENHA_INVALIDA ("Senha inválida"),
	USUARIO_INVALIDO ("Usuário inválido");
	
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
