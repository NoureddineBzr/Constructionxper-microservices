package com.Taches.model;

import com.Taches.model.enums.Statut;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullTachesResponse {
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Statut statut;
    private List<Ressources> ressources;
}
