package com.mouad.Taches.service;

import com.mouad.Taches.model.FullTachesResponse;
import com.mouad.Taches.model.Ressources;
import com.mouad.Taches.model.Taches;
import com.mouad.Taches.model.client.RessourcesClient;
import com.mouad.Taches.model.enums.Statut;
import com.mouad.Taches.repository.TachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TachesServiceImpl implements TachesService{

    @Autowired
    TachesRepository tachesRepository;
    @Autowired
    RessourcesClient ressourcesClient;

    @Override
    public Taches ajouterTache(Taches taches) {
        taches.setStatut(Statut.A_FAIRE);
        return tachesRepository.save(taches);
    }

    @Override
    public Taches editTache(Long id, Taches taches) {
        Taches edited = new Taches();
        edited.setId(id);
        edited.setStatut(taches.getStatut());
        edited.setDescription(taches.getDescription());
        edited.setDateDebut(taches.getDateDebut());
        edited.setDateFin(taches.getDateFin());
        edited.setProjetId(taches.getProjetId());
        return tachesRepository.save(edited);
    }

    @Override
    public List<Taches> getAll() {
        return tachesRepository.findAll();
    }

    @Override
    public void deleteTaches(Long id) {
        tachesRepository.deleteById(id);
    }

    @Override
    public Taches changerStatut(Long id, Taches taches) {
        Optional<Taches> optionalTaches = tachesRepository.findById(id);
        Taches statut = optionalTaches.get();
        statut.setStatut(taches.getStatut());
        return tachesRepository.save(statut);
    }

    @Override
    public List<Taches> getAllTachesByProjet(Long id) {
        return tachesRepository.findAllByProjetId(id);
    }

    @Override
    public FullTachesResponse tachWithRessources(Long id) {
        Taches tache = tachesRepository.findById(id).orElse(
                Taches.builder()
                        .description("NOT_FOUND")
                        .build()
        );
        List<Ressources> ressources = ressourcesClient.getRessourcesByTache(id);
        return FullTachesResponse.builder()
                .description(tache.getDescription())
                .dateDebut(tache.getDateDebut())
                .dateFin(tache.getDateFin())
                .statut(tache.getStatut())
                .ressources(ressources)
                .build();
    }
}
