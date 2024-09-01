package com.Ressources.controller;

import com.Ressources.model.Ressources;
import com.Ressources.service.RessourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources/")
public class RessourcesController {

    @Autowired
    RessourcesService ressourcesService;

    @PostMapping("add")
    public Ressources addRessource(@RequestBody Ressources ressources){
        return ressourcesService.ajouterRessource(ressources);
    }

    @GetMapping("all")
    public List<Ressources> getAll(){
        return ressourcesService.getAllRessources();
    }

    @GetMapping("{id}")
    List<Ressources>getRessourcesByTache(@PathVariable Long id){
        return ressourcesService.getRessourcesByTache(id);
    }

    @PutMapping("edit/{id}")
    public Ressources editRessource(@PathVariable Long id, @RequestBody Ressources ressources){
        return ressourcesService.editRessources(id, ressources);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        ressourcesService.deleteRessources(id);
    }
}
