package br.com.almoxarifado.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.almoxarifado.dao.LocalDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Local;

public class TestaLocal {

	public static void main(String[] args) {
		try {
		//testeCadastroLocal();
		//testeAlteraLocal();
		testeExcluiLocal();
	} catch (ConexaoException | DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

	}
	

	private static void testeExcluiLocal() throws ConexaoException, DAOException {
		LocalDAO dao = new LocalDAO();
		List<Local> list = new ArrayList<Local>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroLocal();
			list = new ArrayList<Local>( dao.consultaTodos().values());
		}
		Local local = list.get(0);
		System.out.println("Excluído porra.");
		dao.exclui(local.getIdLocal());
	}


	private static void testeAlteraLocal() throws ConexaoException, DAOException {
		LocalDAO dao = new LocalDAO();
		List<Local> list = new ArrayList<Local>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroLocal();
			list = new ArrayList<Local>( dao.consultaTodos().values());
		}
		Local local = list.get(0);
		System.out.println(local.getCorredor());
		local.setCorredor("Corredor atualizado");
		dao.altera(local);
		
	}

	private static void testeCadastroLocal() throws ConexaoException, DAOException {
		Local local = new Local("a", "1", "101");
		System.out.println(local);
		
		LocalDAO localDAO = new LocalDAO();
		
			localDAO.insere(local);
		
		
	}

}
