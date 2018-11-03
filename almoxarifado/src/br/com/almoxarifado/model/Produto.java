package br.com.almoxarifado.model;

import java.time.LocalDate;

public class Produto {
	private int idProduto;
	private String nome;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private LocalDate validade;
	private String tipo;
	private int idLocal;
	private Float valor;
	
	public Produto(int idProduto, String nome, LocalDate dataEntrada, LocalDate dataSaida, LocalDate validade,
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
	public Produto( String nome, LocalDate dataEntrada, LocalDate dataSaida, LocalDate validade,
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
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDate getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
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