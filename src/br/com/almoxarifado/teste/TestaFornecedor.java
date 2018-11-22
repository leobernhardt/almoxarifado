package br.com.almoxarifado.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.almoxarifado.dao.FornecedorDAO;
import br.com.almoxarifado.dao.FornecedorDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Fornecedor;
import br.com.almoxarifado.model.Fornecedor;

public class TestaFornecedor {

	public static void main(String[] args) {
		try {
		//testeCadastroFornecedor();
		//testeAlteraFornecedor();
		testeExcluiFornecedor();
	} catch (ConexaoException | DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

	}
	

	private static void testeExcluiFornecedor() throws ConexaoException, DAOException {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> list = new ArrayList<Fornecedor>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroFornecedor();
			list = new ArrayList<Fornecedor>( dao.consultaTodos().values());
		}
		Fornecedor fornecedor = list.get(0);
		System.out.println("Excluído porra.");
		dao.exclui(fornecedor.getIdFornecedor());
	}


	private static void testeAlteraFornecedor() throws ConexaoException, DAOException {
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> list = new ArrayList<Fornecedor>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroFornecedor();
			list = new ArrayList<Fornecedor>( dao.consultaTodos().values());
		}
		Fornecedor fornecedor = list.get(0);
		System.out.println("Nome anterior: "+fornecedor.getNome());
		fornecedor.setNome("PirocaMaster");
		dao.altera(fornecedor);
		System.out.println("Nome novo: " +fornecedor.getNome());
		
	}


	private static void testeCadastroFornecedor() throws ConexaoException, DAOException {
		Fornecedor fornecedor = new Fornecedor("pirocaprovider", "6969696969", "rua daspirocudas", "3369696969", "genitalha@gmail.com", "penis");
		System.out.println(fornecedor);		
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		
			fornecedorDAO.insere(fornecedor);
		
		
	}

}
