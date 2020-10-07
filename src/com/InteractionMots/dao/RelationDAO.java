package com.InteractionMots.dao;

import java.util.List;

import com.InteractionMots.beans.Relation;

public interface RelationDAO {
void ajouter(Relation relation);
List<Relation> lister();
}
