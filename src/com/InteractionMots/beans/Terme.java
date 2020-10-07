package com.InteractionMots.beans;

public class Terme {

	private int id;
	private String nom;
	private int impor;
	
	
	
	
	public Terme(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	
	
	public Terme(int id, String nom, int impor) {
		super();
		this.id = id;
		this.nom = nom;
		this.impor = impor;
	}



	public int getImpor() {
		return impor;
	}



	public void setImpor(int impor) {
		this.impor = impor;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
