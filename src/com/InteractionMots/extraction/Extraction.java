package com.InteractionMots.extraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import com.InteractionMots.beans.Relation;
import com.InteractionMots.beans.Terme;
import com.InteractionMots.dao.DaoFactory;
import com.InteractionMots.dao.RelationDAO;
import com.InteractionMots.dao.TermeDAO;

import Comparator.ComparatorRelation;

public class Extraction {
	public static final int POIDS_RELATION_MINIMUM = 5;
	public static final int POIDS_TERME_MINIMUM = 50;
	
	private DaoFactory daoFactory;
	private RelationDAO relationDao ;
	private TermeDAO termeDao ;
	
	public Extraction(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		relationDao = this.daoFactory.getRelationDao();
		termeDao = this.daoFactory.getTermeDao();
	}
	
	//Cpntienderont les termes/relations pour la bdd
	private List<Relation> relationsToSave = new ArrayList<Relation>();
	private List<Terme> termeToSave = new ArrayList<Terme>();
	
	
	
	
	
	
	
	
	
	public ArrayList<String> extract(String terme) throws IOException {
		
		//Contienderont les relations/termes récup de jeux de mots
		Map<Integer, Terme> mapTemre = new HashMap<Integer, Terme>();
		List< Relation> mapRelation = new ArrayList<Relation>();
		
		
		//coder le terme avant la recherche
		terme = this.raffinementTerme(terme);
		
		//Liste des terme en String pour l'affichage
		ArrayList<String> listTerme = new ArrayList<String>();
		
		
		//Lecture à partire d'une URL construite avec le terme
		String urlString = "http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel="+terme;
		URL url = new URL(urlString);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String s;
		Boolean existeTerme = false;
		Terme firstTerme =null;
		
		//Lecture du code ligne par ligne
		while ((s = in.readLine()) != null) {
			
			//Recherche de la balise <CODE> qui stipule le début des termes/relations
			if(s.equals("<CODE>")){
				existeTerme = true;
			}
			
			//Si on est à l'interieur des termes/relation
			if(existeTerme) {
				
				//Si une ligne non vide
				if(!s.equals("")) {
					
					//Ligne d'une relation
					if(s.charAt(0) == 'r' && s.charAt(1) != 't') {
						String[] detailelation = s.split(";");
						//if(mapTemre.containsKey(Integer.parseInt(detailelation[2])) && mapTemre.containsKey(Integer.parseInt(detailelation[3])) ) {
							if(Integer.parseInt(detailelation[5]) >= POIDS_TERME_MINIMUM ) {
								mapRelation.add(new Relation(Integer.parseInt(detailelation[2]), Integer.parseInt(detailelation[3]), Integer.parseInt(detailelation[5])));
							}
						
					//Ligne d'un terme
					}else if(s.charAt(0) == 'e') {
						String[] detailTerme = s.split(";");
						//Terme Lui même
						String nom = detailTerme[2].substring(1, detailTerme[2].length()-1).toLowerCase();
						if(nom.equals(terme)) {
							firstTerme = new Terme(Integer.parseInt(detailTerme[1]), nom);
							mapTemre.put(Integer.parseInt(detailTerme[1]), firstTerme);
							//Ajout pour la sauvegarde dans la bdd
							termeToSave.add(new Terme(firstTerme.getId(), firstTerme.getNom(),1));
						}else {
							
							//Terme ne possède pas des raffinments
							if(detailTerme.length > 0 && !detailTerme[2].contains("=") && !detailTerme[2].contains(">") && detailTerme[3].equals("1")) {
								
								if(Integer.parseInt(detailTerme[4]) >= POIDS_TERME_MINIMUM) {
									//String nom = detailTerme[2].substring(1, detailTerme[2].length()-1).toLowerCase();
									Terme termeI = new Terme(Integer.parseInt(detailTerme[1]), nom);
									mapTemre.put(Integer.parseInt(detailTerme[1]), termeI);
								}
								
								//terme avec raffinement
							}else if(!detailTerme[2].contains("=") && detailTerme[3].equals("1")){
								if(detailTerme.length == 6 ) {
									nom = detailTerme[5].substring(1, detailTerme[5].length()-1).toLowerCase();
									Terme termeI = new Terme(Integer.parseInt(detailTerme[1]), nom);
									mapTemre.put(Integer.parseInt(detailTerme[1]), termeI);
								}
							}
						}
					}
				}	
			}
		}
			
		System.out.println(existeTerme);
		in.close();
		Collections.sort(mapRelation, new ComparatorRelation());
		System.out.println("taille r : "+mapRelation.size());
		System.out.println("taille t : "+mapTemre.size());
		for(Relation relation : mapRelation) {
			if(mapTemre.containsKey(relation.getIdT1()) && mapTemre.containsKey(relation.getIdT2())) {
				Terme t1 = mapTemre.get(relation.getIdT1());
				Terme t2 = mapTemre.get(relation.getIdT2());
				
				relationsToSave.add(new Relation(t1, t2, relation.getPoids()));
				if(t1.getNom().equals(terme)) {
					t1 = t2;
				}
				
				
				if(!(t1.getNom().equals(terme))) {
					//Ajouter tout terme différents de celui recherché dans la liste des termes a sauvegarder
					termeToSave.add(new Terme(t1.getId(), t1.getNom(), 0));
				}
				
				//Ajout dans la liste de string pour affichage si existe pas deja
				if(!listTerme.contains(t1.getNom()))
					listTerme.add(t1.getNom());
			}
		}
		
		//Appel pour la sauvegarde dans la bdd
		saveInBase(relationsToSave, termeToSave);
		
		System.out.println("taille retour : "+listTerme.size());
		//Retourne les termes en string pour l'affichage
		return listTerme;
	}
	
	
	
	
	private void saveInBase(List<Relation> listRelations, List<Terme> listTermes) {
		
		for(Relation relation : listRelations) {
			relationDao.ajouter(relation);
		}
		
		for(Terme terme : listTermes) {
			termeDao.ajouter(terme);
		}
	}
	
	
	public ArrayList<String> getData(String nomTermeRecherche) throws IOException{
		ArrayList<String> listStringTerme = new ArrayList<String>();
		if(termeDao.existTerme(nomTermeRecherche)) {
			for(Relation relation : relationDao.lister(nomTermeRecherche)) {
				listStringTerme.add(relation.getNomTerme2());
			}
			return listStringTerme;
		}else {
			return extract(nomTermeRecherche);
		}
		
		
	}
	
	
	//Methode qui régle le terme entré avant la recherche dans JeuxDeMot
	private String raffinementTerme(String terme) {
		terme = terme.toLowerCase();
		terme = terme.trim();
		terme = terme.replace("é","%E9").replace("è","%E8").replace("ê","%EA").replace("à","%E0").replace("ç","%E7").replace("û","%FB").replace(" ","+");
		
		return terme;
	}
}
