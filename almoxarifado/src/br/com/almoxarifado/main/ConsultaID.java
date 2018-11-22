package br.com.almoxarifado.main;





import java.util.HashMap;
import java.util.Map;

import br.com.almoxarifado.dao.LocalDAO;
import br.com.almoxarifado.dao.ProdutoDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Local;
import br.com.almoxarifado.model.Produto;
import br.com.almoxarifado.utils.Leitura;


public class ConsultaID {
	
	
	public static int opcao;
	public static int ID;
	public static Produto produto;
	public static String sn;
	public static Map<Integer, Produto> produtos = new HashMap<Integer, Produto>();
	
	public static void consultaIDProduto() throws ConexaoException, DAOException {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		LocalDAO localDAO = new LocalDAO();
		produtos = produtoDAO.consultaTodosQueEstaoNoEstoque();
		if (produtos.isEmpty()) {
			System.out.println("Não existem produtos no Estoque...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.getMessage();
			}
			LimpaTela.limpatela();
			return;
		}
		for (Integer id : produtos.keySet()) {
			 produto = produtos.get(id);
			 System.out.println(produto.getIdProduto() + ". " + produto.getNome());
		}
		while (true) {
			System.out.print("Digite o ID: ");
			ID = Leitura.leInteiro("Número inválido, digite novamente...");
			
			try {
				produto = produtoDAO.consulta(ID);
			} catch (ConexaoException | DAOException e) {
				e.getMessage();
			}
			
			if (produto instanceof Produto) {
				System.out.println("Nome: " + produto.getNome() + "\nTipo: " + produto.getTipo() + "\nData de Entrada: " + produto.getDataEntrada() + "\nValor: " + produto.getValor());
				System.out.print("Tem certeza? (s/n)");
				switch(Leitura.leString()) {
				case "s":
					System.out.println("ok");
					try {
						produtoDAO.alteraDataSaida(produto);
						localDAO.atualizaVazio(produto.getIdLocal());
					} catch (ConexaoException | DAOException e) {
					
						e.getMessage();
					}
					break;
				case "S":
					System.out.println("ok");
					try {
						produtoDAO.alteraDataSaida(produto);
						
					} catch (ConexaoException | DAOException e) {
					
						e.getMessage();
					}
					break;
				}
				break;
				
			} else {
				System.out.println("Produto não encontrado...");
				break;
			}
		}
		
		
		
	}
	
	public static boolean consultaIDLocalLista(Map<Integer, Local> lista, int localEscolhido) {
		for (Integer codLocal : lista.keySet()) {
			if (!(lista.get(codLocal) instanceof Local)) {
				return false;
			}
			if (lista.get(codLocal).getIdLocal() == localEscolhido) {
				return true;
			}
		}
		return false;
	}
}
