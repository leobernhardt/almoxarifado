package br.com.almoxarifado.main;

import br.com.almoxarifado.dao.LocalDAO;
import br.com.almoxarifado.dao.ProdutoDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Local;
import br.com.almoxarifado.model.Produto;
import br.com.almoxarifado.utils.Leitura;
import br.com.menus.Menus;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class Produtos {
	public static String nome;
	public static String dataAtual;
	public static String tipo;
	public static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static Map<Integer, Local> locais = new HashMap <Integer, Local>();
	static Date data = new Date();
	public static Integer codigoLocal;
	public static Integer localEscolhido;
	public static double valor;
	public static String opcao;
	public static int ID;
	public static Produto produto;
	public static int contaLocal = 0;
	
	public static boolean controle = false;
	
	public static void inserirProduto() {
		LocalDAO localDAO = new LocalDAO();
		try {
			locais = localDAO.consultaTodosVazio();
			if (locais.isEmpty()) {
				System.out.println("Não existem mais locais vazios!!!");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				
					e.getMessage();
				}
				return;
			}
		dataAtual = sdf.format(data);
		localEscolhido = 0;
		System.out.print("Nome do produto (Digite 'SAIR' para retornar): ");
		nome = Leitura.leString();
		switch (nome) {
		case "SAIR":
			return;
		}
		System.out.print("\nTipo do produto (Digite 'SAIR' para retornar): ");
		tipo = Leitura.leString();
		switch (tipo) {
		case "SAIR":
			return;
		}
		
		LimpaTela.limpatela();
		
		System.out.print("Escolha o local: ");
		
			System.out.println("\nID do local | Prateleira | Corredor | Sala");
			for (Integer codLocal : locais.keySet()) {
				Local local = locais.get(codLocal);
				
				codigoLocal = codLocal;
				System.out.println("       "+local.getIdLocal() + "           " + local.getPrateleira() + "          " + local.getCorredor() + "         " + local.getSala());
			}
			
		} catch (ConexaoException | DAOException e) {
			e.getMessage();			
		}
		System.out.println("Digite o local: ");
		localEscolhido = Integer.valueOf(Leitura.leString());
		while(ConsultaID.consultaIDLocalLista(locais, localEscolhido) == false) {
			
			System.out.print("Digite o local: ");
			localEscolhido = Integer.valueOf(Leitura.leString());
			
		}
		
		System.out.print("\nDigite o valor: R$");
		valor = Leitura.leDouble("Digite um valor válido!!");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		
		Produto produto = new Produto(nome,dataAtual,tipo,localEscolhido,valor);
		try {
			localDAO.atualizaUsado(Integer.valueOf(localEscolhido));
			produtoDAO.insere(produto);
		} catch (ConexaoException | DAOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	public static void retirarProduto() throws ConexaoException, DAOException {
		
		while(!controle) {
			Menus.montaMenuConsultaProduto();
			opcao = Leitura.leString();
		
			switch (opcao) {
				case "1":
					ConsultaID.consultaIDProduto();
					return;
				case "99":
					return;
					
				default:
					System.out.println("Digite a opção correta!!");
			}
			
			
			
			
			
		}
			
				
				
		
		
		
		
	}
}
