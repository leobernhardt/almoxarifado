package br.com.almoxarifado.interfaces;

import java.util.List;
import java.util.Map;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;



public interface ICRUDPadraoDAO<T> {

	public abstract T consulta(int codigo) throws ConexaoException, DAOException;

	public abstract Map<Integer, T> consultaTodos() throws ConexaoException, DAOException;

	public abstract List<T> consultaFaixa(int... codigos) throws ConexaoException, DAOException;

	public abstract boolean insere(T objeto) throws ConexaoException, DAOException;

	public abstract List<T> insereVarios(Map<Integer, T> objetos) throws ConexaoException, DAOException;

	public abstract List<T> insereVarios(List<T> objetos) throws ConexaoException, DAOException;

	public abstract boolean insereVariosTransacao(List<T> objetos) throws ConexaoException, DAOException;
	
	public abstract boolean altera(T objeto) throws ConexaoException, DAOException;
	
	public abstract boolean exclui(int codigo) throws ConexaoException, DAOException;
	
	public abstract boolean exclui(T objeto) throws ConexaoException, DAOException;

}
