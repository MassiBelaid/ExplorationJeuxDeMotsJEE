package com.InteractionMots.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.InteractionMots.beans.Relation;
import com.InteractionMots.beans.Terme;

public class RelationDaoImpl implements RelationDAO{

	private DaoFactory daoFactory;
	
	public RelationDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Relation relation) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			
			connection = daoFactory.getConnection();
			//insert into terme VALUES (10,"terme",0);
			preparedStatement = connection.prepareStatement("INSERT INTO relation VALUES (?,?,?);");
			
			preparedStatement.setString(1, relation.getT1().getNom());
			preparedStatement.setString(2,relation.getT2().getNom());
			preparedStatement.setInt(3, relation.getPoids());
			
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			//e.printStackTrace();
			System.err.println("Relation "+relation+" Erreur d'ajout.");
		}
	}

	@Override
	public List<Relation> lister(String nomTerme) {
		List<Relation> relations = new ArrayList<Relation>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			String requete = "SELECT * FROM relation WHERE nomT1 = '"+nomTerme+"' ;";
			resultSet = statement.executeQuery(requete);
			
			while (resultSet.next()) {
				relations.add(new Relation(resultSet.getString("nomT1"),resultSet.getString("nomT2"),resultSet.getInt("poids")));
			}
			
		}catch (SQLException e) {
			//e.printStackTrace();
			System.err.println("Relation pour le terme  "+nomTerme+" Erreur d'ajout.");
		}
		
		return relations;
	}

}
