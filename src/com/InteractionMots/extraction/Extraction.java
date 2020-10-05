package com.InteractionMots.extraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.InteractionMots.beans.Relation;
import com.InteractionMots.beans.Terme;

public class Extraction {
	public static final int POID_RELATION_MINIMUM = 50;
	
	
	
	public ArrayList<String> extract(String terme) throws IOException {
		Map<Integer, Terme> mapTemre = new HashMap<Integer, Terme>();
		List< Relation> mapRelation = new ArrayList<Relation>();
		
		terme = this.raffinementTerme(terme);
		ArrayList<String> listTerme = new ArrayList<String>();
		//Lecture à partire d'une URL construite avec le terme
		String urlString = "http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel="+terme;
		URL url = new URL(urlString);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String codeSource = "";
		String s;
		Boolean existeTerme = false;

		
		//Lecture du code ligne par ligne
		while ((s = in.readLine()) != null) {
			codeSource += "\n"+s;
			
			//Recherche de la balise <CODE> qui stipule le début des termes/relations
			if(s.equals("<CODE>")){
				existeTerme = true;
			}
			
			//Si on est à l'interieur des termes/relation
			if(existeTerme) {
				//Si une ligne non vide
				if(!s.equals("")) {
					//Ligne d'une relation
					/*if(s.charAt(0) == 'r' && s.charAt(1) != 't') {
						String[] detailelation = s.split(";");
						if(mapTemre.containsKey(Integer.parseInt(detailelation[2])) && mapTemre.containsKey(Integer.parseInt(detailelation[3])) ) {
							if(Integer.parseInt(detailelation[5]) >= POID_RELATION_MINIMUM ) {
								mapRelation.add(new Relation(mapTemre.get(Integer.parseInt(detailelation[2])), mapTemre.get(Integer.parseInt(detailelation[3])), Integer.parseInt(detailelation[5]), Integer.parseInt(detailelation[4])));
							}
						}
						//listTerme.add("Une relation  : "+s);
						
					//Ligne d'un terme
					}else */if(s.charAt(0) == 'e') {
						String[] detailTerme = s.split(";");
						if(detailTerme.length > 0 && !detailTerme[2].contains("=") && !detailTerme[2].contains(">") && detailTerme[3].equals("1")) {
							
							if(Integer.parseInt(detailTerme[4]) >= POID_RELATION_MINIMUM) {
								mapTemre.put(Integer.parseInt(detailTerme[1]), new Terme(Integer.parseInt(detailTerme[1]), detailTerme[2]));
								//mapRelation.add(new Relation(mapTemre.get(Integer.parseInt(detailelation[2])), mapTemre.get(Integer.parseInt(detailelation[3])), Integer.parseInt(detailelation[5]), Integer.parseInt(detailelation[4])));
								listTerme.add(detailTerme[2]);
							}
							
							
							//listTerme.add("Un terme : "+detailTerme[2]);
						}
						
					}
				}	
			}
			//System.out.println(s);
		}
			
		System.out.println(existeTerme);
		in.close();
		
		/*for(Relation relation : mapRelation) {
			Terme t = mapTemre.get(relation.getT1().getId());
			if(t.getNom().equals(terme)) {
				t = mapTemre.get(relation.getT2().getId());
			}
			listTerme.add("Un terme : "+t.getNom());
		}
		*/
		return listTerme;
	}
	
	private String raffinementTerme(String terme) {
		terme = terme.toLowerCase();
		terme = terme.replace("é","%E9").replace("è","%E8").replace("ê","%EA").replace("à","%E0").replace("ç","%E7").replace("û","%FB").replace(" ","+");
		
		return terme;
	}
}
