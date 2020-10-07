package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.InteractionMots.beans.Relation;
import com.InteractionMots.beans.Terme;

import Comparator.ComparatorRelation;

public class main {

	public static void main(String[] args) {
		List<Relation> lisRel = new ArrayList<Relation>();
		
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),1));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),5));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),4));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),6));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),40));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),4));
		lisRel.add(new Relation(new Terme(5, "homme"),new Terme(55, "chatton"),2));
		
		for(Relation r : lisRel) {
			System.out.println(r.getPoids());
		}
		
		Collections.sort(lisRel, new ComparatorRelation());
		
		
		System.out.println("---------------------------------------------");
		for(Relation r : lisRel) {
			System.out.println(r.getPoids());
		}
		

	}

}
