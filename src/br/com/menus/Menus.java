package br.com.menus;

import br.com.almoxarifado.main.LimpaTela;

public class Menus {
	public static void montaMenuAdm() {
		LimpaTela.limpatela();
		System.out.println("****MENU ADMINISTRADOR****");
		System.out.println("1 - Criar usuário");
		System.out.println("2 - Excluir usuário");
		System.out.println("3 - Incluir funcionário");
		System.out.println("4 - Excluir funcionário ");
		System.out.println("5 - Local");
		System.out.println("6 - Alterar senha");
		System.out.println("99 - Sair");
		System.out.println("00 - Sair para o Sistema Operacional");
		System.out.println("Escolha sua opção: ");
		
	}
	public static void montaMenuUsuario() {
		LimpaTela.limpatela();
		System.out.println("****MENU PRINCIPAL****");
		System.out.println("1 - Entrada de produto");
		System.out.println("2 - Saída de produto");
		System.out.println("3 - Troca de local");
		System.out.println("99 - Sair");
		System.out.println("Escolha sua opção: ");
		
	}
	public static void montaMenuLocal() {
		LimpaTela.limpatela();
		System.out.println("****LOCAL****");
		System.out.println("1 - Criar local");
		System.out.println("2 - Exlcuir local");
	}
	public static void montaMenuConsultaProduto() {
		LimpaTela.limpatela();
		System.out.println("****FILTRO PARA CONSULTA****");
		System.out.println("1 - ID");
		System.out.println("2 - Nome");
		System.out.println("3 - Tipo");
		System.out.println("99 - Sair");
		System.out.println("Escolha sua opção: ");
	}
}
