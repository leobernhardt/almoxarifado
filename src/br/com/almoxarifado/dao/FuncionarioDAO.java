package br.com.almoxarifado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.exceptions.EErrosDAO;
import br.com.almoxarifado.interfaces.ICRUDPadraoDAO;
import br.com.almoxarifado.interfaces.IInstalaDAO;
import br.com.almoxarifado.model.Funcionario;
import br.com.almoxarifado.utils.Conexao;



public class FuncionarioDAO implements IInstalaDAO, ICRUDPadraoDAO<Funcionario> {

//	@Override
//	public boolean criaTabela() throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		try {
//			Statement st = conexao.createStatement();
//			st.executeUpdate("CREATE TABLE pessoas (" + "Codigo			INT(11) NOT NULL PRIMARY KEY,"
//					+ "Nome			VARCHAR(100) NOT NULL," + "DataNascimento	DATE NOT NULL,"
//					+ "Sexo			INT(11) NOT NULL," + "Salario		DOUBLE NOT NULL,"
//					+ "ATIVO			CHAR(1) NULL DEFAULT NULL" + ");");
//			return true;
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.CRIA_TABELA, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//	}

	@Override
	public boolean excluiTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.execute("DROP TABLE funcionario;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Funcionario consulta(int idfuncionario) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM funcionario WHERE idfuncionario = ?;");
			pst.setInt(1, idfuncionario);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Funcionario(rs.getInt("idfuncionario"),
										   rs.getString("nome"),
										   rs.getString("sobrenome"),
										   rs.getString("cargo"),
										   rs.getString("setor"),
										   rs.getString("cpf"),
										   rs.getString("telefone"),
										   rs.getString("email"))
					 		  : null;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Funcionario> consultaTodos() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Funcionario> funcionarios = new HashMap<Integer, Funcionario>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM funcionario;");
			while(rs.next()) {
				funcionarios.put(Integer.valueOf(rs.getInt("idfuncionario")), new Funcionario(rs.getInt("idfuncionario"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("cargo"), rs.getString("setor"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("email")));
			}
			return funcionarios;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Funcionario> consultaFaixa(int... codigos) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Funcionario objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO funcionario (nome, sobrenome, cargo, setor, cpf, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?);");
			pst.setString(1, objeto.getNome());
			pst.setString(2, objeto.getSobrenome());
			pst.setString(3, objeto.getCargo());
			pst.setString(4, objeto.getSetor());
			pst.setString(5, objeto.getCpf());
			pst.setString(6, objeto.getTelefone());
			pst.setString(7, objeto.getEmail());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Funcionario> insereVarios(Map<Integer, Funcionario> objetos) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> insereVarios(List<Funcionario> objetos) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Funcionario> objetos) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Funcionario objeto) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(int codigo) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Funcionario objeto) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean criaTabela() throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Funcionario consulta(String parametro, String valor) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<Funcionario> consultaFaixa(int ... codigos) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
//		try {
//			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM funcionario WHERE idfuncionario = ?;");
//			for (int codigo : codigos) {
//				try {
//					pst.setInt(1, codigo);
//					ResultSet rs = pst.executeQuery();
//					if (rs.first()) {
//						pessoas.add(new Pessoa(rs.getInt("Codigo"),
//										   rs.getString("Nome"),
//										   rs.getDate("DataNascimento"),
//										   ESexo.values()[rs.getInt("Sexo")],
//										   rs.getDouble("Salario"),
//										   rs.getString("Ativo").charAt(0) == 'S'));
//					}
//				} catch (Exception c) {
//					new DAOException(EErrosDAO.CONSULTA_DADO, c.getMessage(), this.getClass());
//				}
//			}
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//		return pessoas;
//	}
//
//	@Override
//	public boolean insere(Pessoa objeto) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		try {
//			PreparedStatement pst = conexao.prepareStatement(
//					"INSERT INTO Pessoas (Codigo, Nome, DataNascimento, Sexo, Salario, Ativo) VALUES (?, ?, ?, ?, ?, ?);");
//			pst.setInt(1, objeto.getCodigo());
//			pst.setString(2, objeto.getNome());
//			pst.setDate(3, new java.sql.Date(objeto.getDataNascimento().getTime()));
//			pst.setInt(4, objeto.getSexo().ordinal());
//			pst.setDouble(5, objeto.getSalario());
//			pst.setString(6, objeto.isAtivo() ? "S" : "N");
//			return pst.executeUpdate() > 0;
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//	}
//
//	@Override
//	public List<Pessoa> insereVarios(Map<Integer, Pessoa> objetos) throws ConexaoException, DAOException {
//		return insereVarios(new ArrayList<Pessoa>(objetos.values()));
//	}
//
//	@Override
//	public List<Pessoa> insereVarios(List<Pessoa> objetos) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		List<Pessoa> falhados = new ArrayList<Pessoa>();
//		try {
//			PreparedStatement pst = conexao.prepareStatement(
//					"INSERT INTO Pessoas (Codigo, Nome, DataNascimento, Sexo, Salario, Ativo) VALUES (?, ?, ?, ?, ?, ?);");
//			for (Pessoa pessoa : objetos) {
//				try {
//					pst.setInt(1, pessoa.getCodigo());
//					pst.setString(2, pessoa.getNome());
//					pst.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));
//					pst.setInt(4, pessoa.getSexo().ordinal());
//					pst.setDouble(5, pessoa.getSalario());
//					pst.setString(6, pessoa.isAtivo() ? "S" : "N");
//					pst.executeUpdate();
//				} catch (SQLException i) {
//					new DAOException(EErrosDAO.INSERE_DADO, i.getMessage(), this.getClass());
//					falhados.add(pessoa);
//				}
//			}
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//		return falhados;
//	}
//
//	@Override
//	public boolean insereVariosTransacao(List<Pessoa> objetos) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		try {
//			conexao.setAutoCommit(false);
////			Savepoint sp =  conexao.setSavepoint();
//			PreparedStatement pst = conexao.prepareStatement(
//					"INSERT INTO Pessoas (Codigo, Nome, DataNascimento, Sexo, Salario, Ativo) VALUES (?, ?, ?, ?, ?, ?);");
//			for (Pessoa pessoa : objetos) {
//					pst.setInt(1, pessoa.getCodigo());
//					pst.setString(2, pessoa.getNome());
//					pst.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));
//					pst.setInt(4, pessoa.getSexo().ordinal());
//					pst.setDouble(5, pessoa.getSalario());
//					pst.setString(6, pessoa.isAtivo() ? "S" : "N");
//					pst.executeUpdate();
//			}
//			conexao.commit();
//			return true;
//		} catch (Exception e) {
//			try {
//				conexao.rollback();
//			} catch (Exception r) {
//				throw new DAOException(EErrosDAO.ROLLBACK, e.getMessage(), this.getClass());
//			}
//			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//	}
//
//	@Override
//	public boolean altera(Pessoa objeto) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		try {
//			PreparedStatement pst = conexao.prepareStatement("UPDATE Pessoas SET Nome = ?, DataNascimento = ?, Sexo = ?, Salario = ?, Ativo = ? WHERE Codigo = ?;");
//			pst.setString(1, objeto.getNome());
//			pst.setDate(2, new java.sql.Date(objeto.getDataNascimento().getTime()));
//			pst.setInt(3, objeto.getSexo().ordinal());
//			pst.setDouble(4, objeto.getSalario());
//			pst.setString(5, objeto.isAtivo() ? "S" : "N");
//			pst.setInt(6, objeto.getCodigo());
//			return pst.executeUpdate() > 0;
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//	}
//
//	@Override
//	public boolean exclui(int codigo) throws ConexaoException, DAOException {
//		Connection conexao = Conexao.abreConexao();
//		try {
//			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Pessoas WHERE Codigo = ?;");
//			pst.setInt(1, codigo);
//			return pst.executeUpdate() > 0;
//		} catch (Exception e) {
//			throw new DAOException(EErrosDAO.EXCLUI_DADO, e.getMessage(), this.getClass());
//		} finally {
//			Conexao.fechaConexao();
//		}
//	}
//
//	@Override
//	public boolean exclui(Pessoa objeto) throws ConexaoException, DAOException {
//		return exclui(objeto.getCodigo());
//	}
}
