package Comparator;

import java.util.Comparator;

import com.InteractionMots.beans.Relation;

public class ComparatorRelation implements Comparator<Relation>{

	@Override
	public int compare(Relation o1, Relation o2) {
		
		return o2.getPoids() - o1.getPoids();
	}

}
