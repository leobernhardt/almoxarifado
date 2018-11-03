package br.com.almoxarifado.interfaces;

import br.com.almoxarifado.exceptions.ConexaoException;
import br.com.almoxarifado.exceptions.DAOException;

public interface IInstalaDAO {
	public abstract boolean criaTabela() throws ConexaoException, DAOException;
	public abstract boolean excluiTabela() throws ConexaoException, DAOException;
}
