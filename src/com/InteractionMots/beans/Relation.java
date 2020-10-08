package com.InteractionMots.beans;

public class Relation {
	private Terme t1;
	private Terme t2;
	private int poids;
	private int idT1;
	private int idT2;
	private String nomTerme1;
	private String nomTerme2;
	
	
	
	public Relation(String nomTerme1, String nomTerme2, int poids) {
		this.poids = poids;
		this.nomTerme1 = nomTerme1;
		this.nomTerme2 = nomTerme2;
	}



	public Relation(Terme t1, Terme t2, int poids) {
		this.t1 = t1;
		this.t2 = t2;
		this.poids = poids;
	}
	
	
	
	public int getIdT1() {
		return idT1;
	}



	public void setIdT1(int idT1) {
		this.idT1 = idT1;
	}



	public int getIdT2() {
		return idT2;
	}



	public void setIdT2(int idT2) {
		this.idT2 = idT2;
	}



	public Relation(int idT1, int idT2, int poids) {
		super();
		this.poids = poids;
		this.idT1 = idT1;
		this.idT2 = idT2;
	}



	public Terme getT1() {
		return t1;
	}
	public void setT1(Terme t1) {
		this.t1 = t1;
	}
	public Terme getT2() {
		return t2;
	}
	public void setT2(Terme t2) {
		this.t2 = t2;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poid) {
		this.poids = poids;
	}



	public String getNomTerme1() {
		return nomTerme1;
	}



	public void setNomTerme1(String nomTerme1) {
		this.nomTerme1 = nomTerme1;
	}



	public String getNomTerme2() {
		return nomTerme2;
	}



	public void setNomTerme2(String nomTerme2) {
		this.nomTerme2 = nomTerme2;
	}
	
	
}
