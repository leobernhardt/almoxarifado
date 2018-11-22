package br.com.almoxarifado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;
import br.com.almoxarifado.exceptions.EErrosDAO;
import br.com.almoxarifado.interfaces.ICRUDPadraoDAO;
import br.com.almoxarifado.interfaces.IInstalaDAO;
import br.com.almoxarifado.model.Produto;
import br.com.almoxarifado.utils.Conexao;

public class ProdutoDAO implements IInstalaDAO, ICRUDPadraoDAO<Produto> {
	public static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static Date data = new Date();
	@Override
	public boolean criaTabela() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Produto (" + "idProduto			INT(11) NOT NULL PRIMARY KEY,"
					+ "nome			VARCHAR(45) NOT NULL," + "dataEntrada	DATE NOT NULL,"
					+ "dataSaida	DATE NULL," + "validade			DATE NOT NULL," + "tipo	VARCHAR(45) NOT NULL,"
					+ "idLocal			int NOT NULL" + "valor			FLOAT NOT NULL," +  ");");
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
			st.execute("DROP TABLE Produto;");
			return true;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Produto consulta(int idProduto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Produto WHERE idProduto = ?;");
			pst.setInt(1, idProduto);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? new Produto(rs.getInt("idProduto"),
					   rs.getString("Nome"),
					   rs.getString("dataEntrada"),
					   rs.getString("dataSaida"),
					   rs.getString("Validade"),
					   rs.getString("Tipo"),
					   rs.getInt("idLocal"),
					   rs.getFloat("Valor"))
					 		  : null;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Produto> consultaTodos() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Produto> Produtos = new HashMap<Integer, Produto>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Produto;");
			while(rs.next()) {
				Produtos.put(Integer.valueOf(rs.getInt("idProduto")), new Produto(rs.getInt("idProduto"),
						   rs.getString("Nome"),
						   rs.getString("dataEntrada"),
						   rs.getString("dataSaida"),
						   rs.getString("Validade"),
						   rs.getString("Tipo"),
						   rs.getInt("idLocal"),
						   rs.getFloat("Valor")));
			}
			return Produtos;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		
	}
	public Map<Integer, Produto> consultaTodosQueEstaoNoEstoque() throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Produto> Produtos = new HashMap<Integer, Produto>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Produto WHERE dataSaida is null;");
			while(rs.next()) {
				Produtos.put(Integer.valueOf(rs.getInt("idProduto")), new Produto(rs.getInt("idProduto"),
						   rs.getString("Nome"),
						   rs.getString("dataEntrada"),
						   rs.getString("dataSaida"),
						   rs.getString("Validade"),
						   rs.getString("Tipo"),
						   rs.getInt("idLocal"),
						   rs.getFloat("Valor")));
			}
			return Produtos;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		
	}

	@Override
	public List<Produto> consultaFaixa(int ... idProdutos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Produto> Produtos = new ArrayList<Produto>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Produto WHERE idProduto = ?;");
			for (int idProduto : idProdutos) {
				try {
					pst.setInt(1, idProduto);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						Produtos.add(new Produto(rs.getInt("idProduto"),
										   rs.getString("Nome"),
										   rs.getString("dataEntrada"),
										   rs.getString("dataSaida"),
										   rs.getString("Validade"),
										   rs.getString("Tipo"),
										   rs.getInt("idLocal"),
										   rs.getFloat("Valor")));
										
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
		return Produtos;
	}
	public Map<Integer, Produto> consultaID(int produto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Produto> Produtos = new HashMap<Integer, Produto>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produto where idProduto = ?;");
			while(rs.next()) {
				Produtos.put(rs.getInt("idProduto"), new Produto(rs.getInt("idProduto"),
						   rs.getString("Nome"),
						   rs.getString("dataEntrada"),
						   rs.getString("dataSaida"),
						   rs.getString("Validade"),
						   rs.getString("Tipo"),
						   rs.getInt("idLocal"),
						   rs.getFloat("Valor")));
			
			}
			return Produtos;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		
	}

	@Override
	public boolean insere(Produto objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (Nome, dataEntrada, Tipo,idLocal,Valor) VALUES (?, ?, ?, ?, ?);");
			
			pst.setString(1, objeto.getNome());
			pst.setDate(2, java.sql.Date.valueOf(objeto.getDataEntrada()));
			
			
			pst.setString(3, objeto.getTipo());
			pst.setInt(4, objeto.getIdLocal());
			pst.setFloat(5, objeto.getValor());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Produto> insereVarios(Map<Integer, Produto> objetos) throws ConexaoException, DAOException {
		return insereVarios(new ArrayList<Produto>(objetos.values()));
	}

	@Override
	public List<Produto> insereVarios(List<Produto> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		List<Produto> falhados = new ArrayList<Produto>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (idProduto, Nome, dataEntrada, dataSaida, Validade, Tipo,idLocal,Valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			for (Produto produto : objetos) {
				try {
					pst.setInt(1, produto.getIdProduto());
					pst.setString(2, produto.getNome());
					pst.setDate(3, java.sql.Date.valueOf(produto.getDataEntrada()));
					pst.setDate(4, java.sql.Date.valueOf(produto.getDataSaida()));
					pst.setDate(5, java.sql.Date.valueOf(produto.getValidade()));
					pst.setString(6, produto.getTipo());
					pst.setInt(7, produto.getIdLocal());
					pst.setFloat(8, produto.getValor());
				} catch (SQLException i) {
					new DAOException(EErrosDAO.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(produto);
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
	public boolean insereVariosTransacao(List<Produto> objetos) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
//			Savepoint sp =  conexao.setSavepoint();
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (idProduto, Nome, dataEntrada, dataSaida, Validade, Tipo,idLocal,Valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			for (Produto produto : objetos) {
				pst.setInt(1, produto.getIdProduto());
				pst.setString(2, produto.getNome());
				pst.setDate(3, java.sql.Date.valueOf(produto.getDataEntrada()));
				pst.setDate(4, java.sql.Date.valueOf(produto.getDataSaida()));
				pst.setDate(5, java.sql.Date.valueOf(produto.getValidade()));
				pst.setString(6, produto.getTipo());
				pst.setInt(7, produto.getIdLocal());
				pst.setFloat(8, produto.getValor());
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
	public boolean altera(Produto objeto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produto SET nome = ?, dataEntrada = ?, dataSaida = ?, validade = ?, tipo = ?, idLocal = ?,valor =? WHERE idProduto = ?;");
			pst.setString(1, objeto.getNome());
			pst.setDate(2, java.sql.Date.valueOf(objeto.getDataEntrada()));
			pst.setDate(3, java.sql.Date.valueOf(objeto.getDataSaida()));
			pst.setDate(4, java.sql.Date.valueOf(objeto.getValidade()));
			pst.setString(5, objeto.getTipo());
			pst.setInt(6, objeto.getIdLocal());
			pst.setFloat(7, objeto.getValor());
			pst.setInt(8, objeto.getIdProduto());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(int idProduto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Produto WHERE idProduto = ?;");
			pst.setInt(1, idProduto);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DAOException(EErrosDAO.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public boolean alteraDataSaida(Produto produto) throws ConexaoException, DAOException {
		Connection conexao = Conexao.abreConexao();
		
		
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE Produto set dataSaida = ? where idProduto = ?;");
					pst.setString(1, sdf.format(data));
					pst.setInt(2, produto.getIdProduto());
					return pst.executeUpdate() > 0;
		} catch  (Exception e) {
			throw new DAOException(EErrosDAO.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		
	}

	@Override
	public boolean exclui(Produto objeto) throws ConexaoException, DAOException {
		return exclui(objeto.getIdProduto());
	}

	@Override
	public Produto consulta(String parametro, String valor) throws ConexaoException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
