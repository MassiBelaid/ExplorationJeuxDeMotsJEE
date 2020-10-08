package com.InteractionMots.dao;

import java.util.List;

import com.InteractionMots.beans.Terme;

public interface TermeDAO {
void ajouter(Terme terme);
List<Terme> lister();
Boolean existTerme (String nom);

}
