package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.InteractionMots.beans.Relation;
import com.InteractionMots.beans.Terme;
import com.InteractionMots.dao.DaoFactory;
import com.InteractionMots.dao.RelationDAO;
import com.InteractionMots.dao.TermeDAO;

import Comparator.ComparatorRelation;

public class main {

	public static void main(String[] args) {
		/*List<Relation> lisRel = new ArrayList<Relation>();
		
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
		}*/
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		TermeDAO tDa = daoFactory.getTermeDao();
		
		tDa.ajouter(new Terme(10, "moha la squale"));
		System.out.println("finish");
		

	}

}
