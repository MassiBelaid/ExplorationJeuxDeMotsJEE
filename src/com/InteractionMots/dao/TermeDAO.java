package com.InteractionMots.dao;

import java.util.List;

import com.InteractionMots.beans.Terme;

public interface TermeDAO {
void ajouter(Terme terme, int imp);
List<Terme> lister();

}
