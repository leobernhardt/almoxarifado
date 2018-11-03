package br.com.almoxarifado.model;

public class Usuario {
private int idFuncionario;
private String login;
private String senha;
private boolean adm;

public Usuario(int idFuncionario, String login, String senha, boolean adm) {
	super();
	this.idFuncionario = idFuncionario;
	this.login = login;
	this.senha = senha;
	this.adm = adm;
}
public Usuario(String login, String senha, boolean adm) {
	super();
	this.login = login;
	this.senha = senha;
	this.adm = adm;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public boolean isAdm() {
	return adm;
}
public void setAdm(boolean adm) {
	this.adm = adm;
}
@Override
public String toString() {
	return "Usuario [idFuncionario=" + idFuncionario + ", login=" + login + ", senha=" + senha + ", adm=" + adm + "]";
}

}
