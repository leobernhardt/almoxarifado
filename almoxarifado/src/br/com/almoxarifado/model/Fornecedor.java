package br.com.almoxarifado.model;

public class Fornecedor {
	private int idFornecedor;
	private String nome;
	private String cpfcnpj;
	private String endereco;
	private String telefone;
	private String email;	
	private String tipoProduto;
	
	public Fornecedor(int idFornecedor, String nome, String cpfcnpj, String endereco, String telefone, String email,
			String tipoProduto) {
		super();
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.tipoProduto = tipoProduto;
	}
	public Fornecedor( String nome, String cpfcnpj, String endereco, String telefone, String email,
			String tipoProduto) {
		super();
		this.nome = nome;
		this.cpfcnpj = cpfcnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.tipoProduto = tipoProduto;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoproduto() {
		return tipoProduto;
	}
	public void setTipoproduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nome=" + nome + ", cpfcnpj=" + cpfcnpj + ", endereco="
				+ endereco + ", telefone=" + telefone + ", email=" + email + ", tipoProduto=" + tipoProduto + "]";
	}
	
}
