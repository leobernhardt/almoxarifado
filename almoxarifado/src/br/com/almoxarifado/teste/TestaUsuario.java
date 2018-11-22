package br.com.almoxarifado.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.almoxarifado.dao.FuncionarioDAO;
import br.com.almoxarifado.dao.UsuarioDAO;
import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.model.Funcionario;
import br.com.almoxarifado.model.Usuario;

public class TestaUsuario {

	public static void main(String[] args) {
		try {
//		testeCadastroFuncionario();
//		testeCadastroUsuario();
//		testeAlteraUsuario();
	testeExcluiUsuario();
	} catch (ConexaoException | DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

	}
	private static void testeCadastroFuncionario() throws ConexaoException, DAOException {
		Funcionario funcionario = new Funcionario("nome", "sobren", "boyola","gfdslkj","02549878565","3356186","email");
		System.out.println(funcionario);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
			funcionarioDAO.insere(funcionario);
			
	}
	private static Funcionario retornaFuncionario() throws ConexaoException, DAOException {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> list = new ArrayList<Funcionario>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroFuncionario();
			list = new ArrayList<Funcionario>( dao.consultaTodos().values());
		}
		return list.get(0);
	}
	

	private static void testeExcluiUsuario() throws ConexaoException, DAOException {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> list = new ArrayList<Usuario>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroUsuario();
			list = new ArrayList<Usuario>( dao.consultaTodos().values());
		}
		Usuario usuario = list.get(0);
		System.out.println("Excluído porra.");
		dao.exclui(usuario.getIdFuncionario());
	}


	private static void testeAlteraUsuario() throws ConexaoException, DAOException {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> list = new ArrayList<Usuario>( dao.consultaTodos().values());
		if(list.isEmpty()) {
			testeCadastroUsuario();
			list = new ArrayList<Usuario>( dao.consultaTodos().values());
		}
		Usuario usuario = list.get(0);
		System.out.println("Nome anterior: "+usuario.getLogin());
		usuario.setLogin("PirocaMaster");
		dao.altera(usuario);
		System.out.println("Nome novo: " +usuario.getLogin());
		
	}

		
		

	private static void testeCadastroUsuario() throws ConexaoException, DAOException {
		Usuario usuario = new Usuario(retornaFuncionario().getIdFuncionario(),"login","senha",true);
		System.out.println(usuario);		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
			usuarioDAO.insere(usuario);
		
		
	}

}
