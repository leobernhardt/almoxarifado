package br.com.almoxarifado.model;

public class Funcionario {

	private String nome;
	private String sobrenome;
	private String cargo;
	private String setor;
	private String cpf;
	private String telefone;
	private String email;
	private int idFuncionario;
	
	
	
	
//	public Funcionario(String nome,String sobrenome, String cargo, String cpf, String telefone) {
//		setNome(nome);
//		setSobrenome(sobrenome);
//		setCargo(cargo);
//		setSetor("N/A");
//		setCpf(cpf);
//		setTelefone(telefone);
//		setEmail("N/A");
//	}
	
	public Funcionario(int idFuncionario, String nome,String sobrenome, String cargo, String setor, String cpf, String telefone, String email) {
		setNome(nome);
		setSobrenome(sobrenome);
		setCargo(cargo);
		setSetor(setor);
		setCpf(cpf);
		setTelefone(telefone);
		setEmail(email);
		setIdFuncionario(idFuncionario);
	}
	public Funcionario(String nome,String sobrenome, String cargo, String setor, String cpf, String telefone, String email) {
		setNome(nome);
		setSobrenome(sobrenome);
		setCargo(cargo);
		setSetor(setor);
		setCpf(cpf);
		setTelefone(telefone);
		setEmail(email);
		
	}
	
	
	


	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}





	public int getIdFuncionario() {
		return idFuncionario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	@Override
	public String toString() {
		return "\tNome: " + nome + "\n\tSobrenome: " + sobrenome + "\n\tCargo: " + cargo + "\n\tSetor: " + setor
				+ "\n\tCPF: " + cpf + "\n\tTelefone: " + telefone + "\n\tE-mail: " + email + "\n\tID: " + idFuncionario;
	}
	
	
	

}
