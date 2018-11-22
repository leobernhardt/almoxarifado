package br.com.almoxarifado.teste;

import br.com.almoxarifado.dao.FuncionarioDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Funcionario;

public class TestaFuncionario {

	public static void main(String[] args) {
		Funcionario funcionario = new Funcionario("Leonardo", "Sobrenome", "te312312213te", "pq32543p", "459485sddgf84", "4733282980", "1232412134");
		System.out.println(funcionario);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		try {
			funcionarioDAO.insere(funcionario);
		} catch (ConexaoException | DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}
