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
import br.com.almoxarifado.model.Usuario;
import br.com.almoxarifado.utils.Conexao;

public class UsuarioDAO implements IInstalaDAO, ICRUDPadraoDAO<Usuario> {

	@Override
	public boolean criaTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS `almoxarifado`.`usuario` (\r\n" + 
					"  `idFuncionario` INT NOT NULL ,\r\n" + 
					"  `login` VARCHAR(45) NOT NULL,\r\n" + 
					"  `senha` VARCHAR(100) NOT NULL,\r\n" + 
					"  `adm` TINYINT NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idFuncionario`),\r\n" + 
					"  INDEX `fk_usuario_funcionario_idx` (`idFuncionario` ASC),\r\n" + 
					"  CONSTRAINT `fk_usuario_funcionario`\r\n" + 
					"    FOREIGN KEY (`idFuncionario`)\r\n" + 
					"    REFERENCES `almoxarifado`.`funcionario` (`idFuncionario`)\r\n" + 
					"    ON DELETE NO ACTION\r\n" + 
					"    ON UPDATE NO ACTION)\r\n" + 
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
			st.execute("DROP TABLE usuario;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Usuario consulta(int idFuncionario) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM usuario WHERE idFuncionario = ?;");
			pst.setInt(1, idFuncionario);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Usuario(rs.getInt("idFuncionario"),
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
	public Map<Integer, Usuario> consultaTodos() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Usuario> usuario = new HashMap<Integer, Usuario>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario;");
			while(rs.next()) {
				usuario.put(Integer.valueOf(rs.getInt("IdFuncionario")), new Usuario(rs.getInt("idFuncionario"),
						rs.getString("prateleira"),
						   rs.getString("corredor"),
						   rs.getString("sala")));
			}
			return usuario;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Usuario> consultaFaixa(int ... idFuncionarios) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM usuario WHERE IdFuncionario = ?;");
			for (int idFuncionario : idFuncionarios) {
				try {
					pst.setInt(1, idFuncionario);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						usuario.add(new Usuario(rs.getInt("idFuncionario"),
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
		return usuario;
	}

	@Override
	public boolean insere(Usuario objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO usuario (prateleira,corredor,sala) VALUES (?, ?, ?);");
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
	public List<Usuario> insereVarios(Map<Integer, Usuario> objetos) throws ConexaoException, DAOException {
		return insereVarios(new ArrayList<Usuario>(objetos.values()));
	}

	@Override
	public List<Usuario> insereVarios(List<Usuario> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Usuario> falhados = new ArrayList<Usuario>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO usuario (prateleira,corredor,sala) VALUES (?, ?, ?);");
			for (Usuario Usuario : objetos) {
				try {
					pst.setString(1, Usuario.getPrateleira());
					pst.setString(2, Usuario.getCorredor());
					pst.setString(3, Usuario.getSala());
				} catch (SQLException i) {
					new DAOException(EErrosDAO.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(Usuario);
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
	public boolean insereVariosTransacao(List<Usuario> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
//			Savepoint sp =  conexao.setSavepoint();
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO usuario (prateleira,corredor,sala) VALUES (?, ?, ?);");
			for (Usuario Usuario : objetos) {
				pst.setString(1, Usuario.getPrateleira());
				pst.setString(2, Usuario.getCorredor());
				pst.setString(3, Usuario.getSala());
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
	public boolean altera(Usuario objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE usuario SET Prateleira = ?, Corredor = ?, Sala = ? WHERE IdFuncionario = ?;");
			pst.setString(1, objeto.getPrateleira());
			pst.setString(2, objeto.getCorredor());
			pst.setString(3, objeto.getSala());
			pst.setInt(4, objeto.getIdFuncionario());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(int idFuncionario) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM usuario WHERE idFuncionario = ?;");
			pst.setInt(1, idFuncionario);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(Usuario objeto) throws ConexaoException, DAOException {
		return exclui(objeto.getIdFuncionario());
	}
}
