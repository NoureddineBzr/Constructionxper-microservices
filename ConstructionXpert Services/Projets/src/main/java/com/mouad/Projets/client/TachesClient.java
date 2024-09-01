package com.mouad.Projets.client;

import com.mouad.Projets.model.Taches;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "tache-service", url = "${application.config.taches-url}")
public interface TachesClient {

    @GetMapping("/projet/{id}")
    List<Taches> findAllTachesByProjet(@PathVariable Long id);
}
