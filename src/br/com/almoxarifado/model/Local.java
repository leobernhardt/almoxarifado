package br.com.almoxarifado.model;

public class Local {
private int idLocal;
private String prateleira;
private String corredor;
private String sala;
private boolean usado;

public Local(int idLocal, String prateleira, String corredor, String sala, boolean usado) {
	super();
	this.idLocal = idLocal;
	this.prateleira = prateleira;
	this.corredor = corredor;
	this.sala = sala;
	this.usado = usado;
}
public Local( String prateleira, String corredor, String sala, boolean usado) {
	super();
	
	this.prateleira = prateleira;
	this.corredor = corredor;
	this.sala = sala;
	this.usado = usado;
}
public int getIdLocal() {
	return idLocal;
}
public void setIdLocal(int idLocal) {
	this.idLocal = idLocal;
}
public String getPrateleira() {
	return prateleira;
}
public void setPrateleira(String prateleira) {
	this.prateleira = prateleira;
}
public String getCorredor() {
	return corredor;
}
public void setCorredor(String corredor) {
	this.corredor = corredor;
}
public String getSala() {
	return sala;
}
public void setSala(String sala) {
	this.sala = sala;
}
@Override
public String toString() {
	return "Local [idLocal=" + idLocal + ", prateleira=" + prateleira + ", corredor=" + corredor + ", sala=" + sala
			+ "]";
}
public boolean isUsado() {
	return usado;
}
public void setUsado(boolean usado) {
	this.usado = usado;
}

}
