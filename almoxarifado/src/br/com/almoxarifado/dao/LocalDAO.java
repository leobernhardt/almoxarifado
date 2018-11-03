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
import br.com.almoxarifado.model.Local;
import br.com.almoxarifado.utils.Conexao;

public class LocalDAO implements IInstalaDAO, ICRUDPadraoDAO<Local> {

	@Override
	public boolean criaTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS `almoxarifado`.`local` (\r\n" + 
					"  `idlocal` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `prateleira` VARCHAR(45) NOT NULL,\r\n" + 
					"  `corredor` VARCHAR(45) NOT NULL,\r\n" + 
					"  `sala` VARCHAR(45) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idlocal`))\r\n" + 
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
			st.execute("DROP TABLE local;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Local consulta(int idLocal) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM local WHERE idLocal = ?;");
			pst.setInt(1, idLocal);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Local(rs.getInt("idLocal"),
					   rs.getString("prateleira"),
					   rs.getString("corredor"),
					   rs.getString("sala"))
					 		  : null;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Local> consultaTodos() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Local> local = new HashMap<Integer, Local>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM local;");
			while(rs.next()) {
				local.put(Integer.valueOf(rs.getInt("IdLocal")), new Local(rs.getInt("idLocal"),
						rs.getString("prateleira"),
						   rs.getString("corredor"),
						   rs.getString("sala")));
			}
			return local;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Local> consultaFaixa(int ... idLocals) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Local> local = new ArrayList<Local>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM local WHERE IdLocal = ?;");
			for (int idLocal : idLocals) {
				try {
					pst.setInt(1, idLocal);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						local.add(new Local(rs.getInt("idLocal"),
								rs.getString("prateleira"),
								   rs.getString("corredor"),
								   rs.getString("sala")));
										
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
		return local;
	}

	@Override
	public boolean insere(Local objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO local (prateleira,corredor,sala) VALUES (?, ?, ?);");
			pst.setString(1, objeto.getPrateleira());
			pst.setString(2, objeto.getCorredor());
			pst.setString(3, objeto.getSala());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Local> insereVarios(Map<Integer, Local> objetos) throws ConexaoException, DAOException {
		return insereVarios(new ArrayList<Local>(objetos.values()));
	}

	@Override
	public List<Local> insereVarios(List<Local> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Local> falhados = new ArrayList<Local>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO local (prateleira,corredor,sala) VALUES (?, ?, ?);");
			for (Local Local : objetos) {
				try {
					pst.setString(1, Local.getPrateleira());
					pst.setString(2, Local.getCorredor());
					pst.setString(3, Local.getSala());
				} catch (SQLException i) {
					new DAOException(EErrosDAO.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(Local);
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
	public boolean insereVariosTransacao(List<Local> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
//			Savepoint sp =  conexao.setSavepoint();
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO local (prateleira,corredor,sala) VALUES (?, ?, ?);");
			for (Local Local : objetos) {
				pst.setString(1, Local.getPrateleira());
				pst.setString(2, Local.getCorredor());
				pst.setString(3, Local.getSala());
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
	public boolean altera(Local objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE local SET Prateleira = ?, Corredor = ?, Sala = ? WHERE IdLocal = ?;");
			pst.setString(1, objeto.getPrateleira());
			pst.setString(2, objeto.getCorredor());
			pst.setString(3, objeto.getSala());
			pst.setInt(4, objeto.getIdLocal());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(int idLocal) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM local WHERE idLocal = ?;");
			pst.setInt(1, idLocal);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(Local objeto) throws ConexaoException, DAOException {
		return exclui(objeto.getIdLocal());
	}
}
