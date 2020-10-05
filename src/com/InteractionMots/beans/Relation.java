package com.InteractionMots.beans;

public class Relation {
	private Terme t1;
	private Terme t2;
	private int poid;
	private int numTypeRelation;
	
	public Relation(Terme t1, Terme t2, int poid, int numTypeRelation) {
		this.t1 = t1;
		this.t2 = t2;
		this.poid = poid;
		this.numTypeRelation = numTypeRelation;
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
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public int getNumTypeRelation() {
		return numTypeRelation;
	}
	public void setNumTypeRelation(int numTypeRelation) {
		this.numTypeRelation = numTypeRelation;
	}
	
	
}
