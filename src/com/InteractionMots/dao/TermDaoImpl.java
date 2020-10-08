package com.InteractionMots.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.InteractionMots.beans.Terme;
public class TermDaoImpl implements TermeDAO{

	private DaoFactory daoFactory;
	
	public TermDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Terme terme) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			
			connection = daoFactory.getConnection();
			//insert into terme VALUES (10,"terme",0);
			preparedStatement = connection.prepareStatement("INSERT INTO terme VALUES (?,?,?);");

			preparedStatement.setInt(1, terme.getId());
			preparedStatement.setString(2,terme.getNom());
			preparedStatement.setInt(3, terme.getImpor());
			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			//e.printStackTrace();
			System.err.println("Terme "+terme+" Erreur d'ajout.");
		}
		
	}

	@Override
	public List<Terme> lister() {
		List<Terme> termes = new ArrayList<Terme>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM terme;");
			
			while (resultSet.next()) {
				termes.add(new Terme(resultSet.getInt("id"), resultSet.getString("nom")));
				
			}
			
		}catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return termes;
	}

	@Override
	public Boolean existTerme(String nom) {
		
		List<Terme> termes = new ArrayList<Terme>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			String requete = "SELECT * FROM terme WHERE nom = '"+nom+"' AND importe = 1 ; ";
			resultSet = statement.executeQuery(requete);
			
			while (resultSet.next()) {
				termes.add(new Terme(resultSet.getInt("id"), resultSet.getString("nom")));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (termes.size()>0);
	}
	
	

}
