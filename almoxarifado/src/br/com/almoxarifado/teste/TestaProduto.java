package br.com.almoxarifado.teste;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.almoxarifado.dao.LocalDAO;
import br.com.almoxarifado.dao.ProdutoDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Local;
import br.com.almoxarifado.model.Produto;

public class TestaProduto {

	public static void main(String[] args) {
		try {
//		testeCadastroProduto();
		//testeAlteraProduto();
//		testeExcluiProduto();
			testeConsultaProduto();
	} catch (ConexaoException | DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

	}
	

	private static void testeExcluiProduto() throws ConexaoException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> list = new ArrayList<Produto>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroProduto();
			list = new ArrayList<Produto>( dao.consultaTodos().values());
		}
		Produto produto = list.get(0);
		System.out.println("Excluído porra.");
		dao.exclui(produto.getIdProduto());
	}


	private static void testeAlteraProduto() throws ConexaoException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> list = new ArrayList<Produto>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroProduto();
			list = new ArrayList<Produto>( dao.consultaTodos().values());
		}
		Produto produto = list.get(0);
		System.out.println("Nome anterior: "+produto.getNome());
		produto.setNome("PirocaMaster");
		dao.altera(produto);
		System.out.println("Nome novo: " +produto.getNome());
		
	}

	private static void testeCadastroLocal() throws ConexaoException, DAOException {
		Local local = new Local("a", "1", "101");
		System.out.println(local);
		
		LocalDAO localDAO = new LocalDAO();
		
			localDAO.insere(local);
		
		
	}
	private static Local retornaLocal() throws ConexaoException, DAOException {
		LocalDAO dao = new LocalDAO();
		List<Local> list = new ArrayList<Local>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroLocal();
			list = new ArrayList<Local>( dao.consultaTodos().values());
		}
		return list.get(0);
	}

	private static void testeCadastroProduto() throws ConexaoException, DAOException {
		Produto produto = new Produto("piroca", "2017-02-03","2018-02-03","2020-02-03", "genitalha",retornaLocal().getIdLocal(), (float) 20.00);
		System.out.println(produto);		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
			produtoDAO.insere(produto);
		
		
	}
	
	private static void testeConsultaProduto() throws ConexaoException, DAOException {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
			System.out.println(produtoDAO.consulta(7));
		
			// TODO Auto-generated catch block
			
		
	}

}
