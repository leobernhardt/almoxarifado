package br.com.almoxarifado.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.EErrosConexao;

public class Conexao {
	private static Connection conn = null;
	
	public static Connection abreConexao() throws ConexaoException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/almoxarifado?useSSL=true&serverTimezone=America/Sao_Paulo", "root", "");
			return conn;
		} catch (Exception e) {
			throw new ConexaoException(EErrosConexao.ABRE_CONEXAO, e.getMessage());
		}
	}
	
	public static void fechaConexao() throws ConexaoException {
		try {
			if (conn instanceof Connection)	conn.close();
			conn = null;
		} catch (Exception e) {
			throw new ConexaoException(EErrosConexao.FECHA_CONEXAO, e.getMessage());
		}
	}
}
