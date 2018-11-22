package br.com.almoxarifado.model;


//import java.sql.Date;


public class Produto {
	private int idProduto;
	private String nome;
	private String dataEntrada;
	private String dataSaida;
	private String validade;
	private String tipo;
	private int idLocal;
	private Float valor;
	
	public Produto(int idProduto, String nome, String dataEntrada, String dataSaida, String validade,
			String tipo, int idLocal, Float valor) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.validade = validade;
		this.tipo = tipo;
		this.idLocal = idLocal;
		this.valor = valor;
	}
	public Produto( String nome, String dataEntrada, String dataSaida, String validade,
			String tipo,int idLocal, Float valor) {
		super();
		this.nome = nome;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.validade = validade;
		this.tipo = tipo;
		this.idLocal = idLocal;
		this.valor = valor;
	}
	public Produto( String nome, String dataEntrada,
			String tipo,Integer idLocal, double valor) {
		super();
		this.nome = nome;
		this.dataEntrada = dataEntrada;
		
		
		this.tipo = tipo;
		this.idLocal = idLocal;
		this.valor = (float) valor;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", dataEntrada=" + dataEntrada + ", dataSaida="
				+ dataSaida + ", validade=" + validade + ", tipo=" + tipo + ", idLocal=" + idLocal + ", valor=" + valor
				+ "]";
	}

}