package com.mouad.Projets.service;

import com.mouad.Projets.client.TachesClient;
import com.mouad.Projets.model.FullProjetResponse;
import com.mouad.Projets.model.Projets;
import com.mouad.Projets.model.Taches;
import com.mouad.Projets.repository.ProjetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjetsServiceImpl implements ProjetsService{

    @Autowired
    ProjetsRepository projetsRepository;
    @Autowired
    TachesClient tachesClient;

    @Override
    public Projets ajouterProjet(Projets projet) {
        return projetsRepository.save(projet);
    }

    @Override
    public Projets modifierProjet(Long id, Projets projet) {
        Projets edited = new Projets();
        edited.setId(id);
        edited.setNom(projet.getNom());
        edited.setDescription(projet.getDescription());
        edited.setBudget(projet.getBudget());
        edited.setDateDebut(projet.getDateDebut());
        edited.setDateFin(projet.getDateFin());
        return projetsRepository.save(edited);
    }

    @Override
    public List<Projets> allProjets() {
        return projetsRepository.findAll();
    }

    @Override
    public void supprimerProjet(Long id) {
        projetsRepository.deleteById(id);
    }

    @Override
    public FullProjetResponse projetWithTaches(Long id) {
        Projets projet = projetsRepository.findById(id)
                .orElse(
                        Projets.builder()
                                .nom("NOT_FOUND")
                                .build()
                );
        List<Taches> taches = tachesClient.findAllTachesByProjet(id);
        return FullProjetResponse.builder()
                .nom(projet.getNom())
                .dateDebut(projet.getDateDebut())
                .dateFin(projet.getDateFin())
                .description(projet.getDescription())
                .budget(projet.getBudget())
                .taches(taches)
                .build();
    }
}
