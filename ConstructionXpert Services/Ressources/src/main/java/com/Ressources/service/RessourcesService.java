package com.mouad.Ressources.service;

import com.mouad.Ressources.model.Ressources;

import java.util.List;

public interface RessourcesService {
    Ressources ajouterRessource (Ressources ressources);
    Ressources editRessources (Long id, Ressources ressources);
    List<Ressources> getAllRessources ();
    void deleteRessources(Long id);
    List<Ressources> getRessourcesByTache(Long id);
}
