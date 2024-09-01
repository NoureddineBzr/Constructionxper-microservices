package com.mouad.Projets.controller;

import com.mouad.Projets.model.FullProjetResponse;
import com.mouad.Projets.model.Projets;
import com.mouad.Projets.service.ProjetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets/")
public class ProjetsController {
    @Autowired
    ProjetsService projetsService;

    @PostMapping("add")
    public Projets addProjet(@RequestBody Projets projets){
        return projetsService.ajouterProjet(projets);
    }

    @GetMapping("all")
    public List<Projets> getAll(){
        return projetsService.allProjets();
    }

    @GetMapping("{id}")
    public FullProjetResponse projetWithTaches(@PathVariable Long id){
        return projetsService.projetWithTaches(id);
    }

    @PutMapping("edit/{id}")
    public Projets edit(@PathVariable Long id, @RequestBody Projets projets){
        return projetsService.modifierProjet(id, projets);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        projetsService.supprimerProjet(id);
    }
}
