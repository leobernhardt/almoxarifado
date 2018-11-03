package br.com.almoxarifado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.exceptions.EErrosDAO;
import br.com.almoxarifado.interfaces.ICRUDPadraoDAO;
import br.com.almoxarifado.interfaces.IInstalaDAO;
import br.com.almoxarifado.model.Fornecedor;
import br.com.almoxarifado.utils.Conexao;

public class FornecedorDAO implements IInstalaDAO, ICRUDPadraoDAO<Fornecedor> {

	@Override
	public boolean criaTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS `almoxarifado`.`fornecedor` (\r\n" + 
					"  `idFornecedor` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `nome` VARCHAR(100) NOT NULL,\r\n" + 
					"  `cpfcnpj` VARCHAR(45) NOT NULL,\r\n" + 
					"  `endereco` VARCHAR(100) NOT NULL,\r\n" + 
					"  `telefone` VARCHAR(45) NOT NULL,\r\n" + 
					"  `email` VARCHAR(45) NOT NULL,\r\n" + 
					"  `tipoProduto` VARCHAR(100) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idFornecedor`))\r\n" + 
					"ENGINE = InnoDB;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CRIA_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean excluiTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.execute("DROP TABLE fornecedor;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Fornecedor consulta(int idFornecedor) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM fornecedor WHERE idFornecedor = ?;");
			pst.setInt(1, idFornecedor);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Fornecedor(rs.getInt("idFornecedor"),
					   rs.getString("nome"),
					   rs.getString("cpfcnpj"),
					   rs.getString("endereco"),
					   rs.getString("telefone"),
					   rs.getString("email"),
					   rs.getString("tipoProduto"))
					 		  : null;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Fornecedor> consultaTodos() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Fornecedor> fornecedor = new HashMap<Integer, Fornecedor>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM fornecedor;");
			while(rs.next()) {
				fornecedor.put(Integer.valueOf(rs.getInt("IdFornecedor")), new Fornecedor(rs.getInt("idFornecedor"),
						   rs.getString("nome"),
						   rs.getString("cpfcnpj"),
						   rs.getString("endereco"),
						   rs.getString("telefone"),
						   rs.getString("email"),
						   rs.getString("tipoProduto")));
			}
			return fornecedor;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Fornecedor> consultaFaixa(int ... idFornecedors) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Fornecedor> fornecedor = new ArrayList<Fornecedor>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM fornecedor WHERE IdFornecedor = ?;");
			for (int idFornecedor : idFornecedors) {
				try {
					pst.setInt(1, idFornecedor);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						fornecedor.add(new Fornecedor(rs.getInt("idFornecedor"),
								   rs.getString("nome"),
								   rs.getString("cpfcnpj"),
								   rs.getString("endereco"),
								   rs.getString("telefone"),
								   rs.getString("email"),
								   rs.getString("tipoProduto")));
										
					}
				} catch (Exception c) {
					new DAOException(EErrosDAO.CONSULTA_DADO, c.getMessage(), this.getClass());
				}
			}
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		return fornecedor;
	}

	@Override
	public boolean insere(Fornecedor objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO fornecedor (nome,cpfcnpj,endereco,telefone,email,tipoProduto) VALUES (?, ?, ?, ?, ?, ?);");
			pst.setString(1, objeto.getNome());
			pst.setString(2, objeto.getCpfcnpj());
			pst.setString(3, objeto.getEndereco());
			pst.setString(4, objeto.getTelefone());
			pst.setString(5, objeto.getEmail());
			pst.setString(6, objeto.getTipoproduto());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Fornecedor> insereVarios(Map<Integer, Fornecedor> objetos) throws ConexaoException, DAOException {
		return insereVarios(new ArrayList<Fornecedor>(objetos.values()));
	}

	@Override
	public List<Fornecedor> insereVarios(List<Fornecedor> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Fornecedor> falhados = new ArrayList<Fornecedor>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO fornecedor (nome,cpfcnpj,endereco,telefone,email,tipoProduto) VALUES (?, ?, ?, ?, ?, ?);");
			for (Fornecedor fornecedor : objetos) {
				try {
					pst.setString(1, fornecedor.getNome());
					pst.setString(2, fornecedor.getCpfcnpj());
					pst.setString(3, fornecedor.getEndereco());
					pst.setString(4, fornecedor.getTelefone());
					pst.setString(5, fornecedor.getEmail());
					pst.setString(6, fornecedor.getTipoproduto());
				} catch (SQLException i) {
					new DAOException(EErrosDAO.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(fornecedor);
				}
			}
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		return falhados;
	}

	@Override
	public boolean insereVariosTransacao(List<Fornecedor> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
//			Savepoint sp =  conexao.setSavepoint();
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO fornecedor (nome,cpfcnpj,endereco,telefone,email,tipoProduto) VALUES (?, ?, ?, ?, ?, ?);");
			for (Fornecedor fornecedor : objetos) {
				pst.setString(1, fornecedor.getNome());
				pst.setString(2, fornecedor.getCpfcnpj());
				pst.setString(3, fornecedor.getEndereco());
				pst.setString(4, fornecedor.getTelefone());
				pst.setString(5, fornecedor.getEmail());
				pst.setString(6, fornecedor.getTipoproduto());
					pst.executeUpdate();
			}
			conexao.commit();
			return true;
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (Exception r) {
				throw new DAOException(EErrosDAO.ROLLBACK, e.getMessage(), this.getClass());
			}
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean altera(Fornecedor objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE fornecedor SET nome = ?, cpfcnpj = ?, endereco = ?, telefone = ?, email = ?, "
					+ "tipoProduto = ? WHERE IdFornecedor = ?;");
			pst.setString(1, objeto.getNome());
			pst.setString(2, objeto.getCpfcnpj());
			pst.setString(3, objeto.getEndereco());
			pst.setString(4, objeto.getTelefone());
			pst.setString(5, objeto.getEmail());
			pst.setString(6, objeto.getTipoproduto());;
			pst.setInt(7, objeto.getIdFornecedor());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(int idFornecedor) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM fornecedor WHERE idFornecedor = ?;");
			pst.setInt(1, idFornecedor);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(Fornecedor objeto) throws ConexaoException, DAOException {
		return exclui(objeto.getIdFornecedor());
	}
}
