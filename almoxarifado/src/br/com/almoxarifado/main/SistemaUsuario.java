package br.com.almoxarifado.main;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.utils.Leitura;
import br.com.menus.Menus;

public class SistemaUsuario {
	public static String opcao;
	public static void sistemaUsuario() throws ConexaoException, DAOException {
		while(true) {
			Menus.montaMenuUsuario();
			switch (Leitura.leString()) {
			case "1":
				Produtos.inserirProduto();
				break;
			case "2":
				Produtos.retirarProduto();
				break;
			case "99":
				LimpaTela.limpatela();
				return;
			default:
				System.out.println("Digite a opção correta!!");
				break;
				
			}
		}
	}
	
}
