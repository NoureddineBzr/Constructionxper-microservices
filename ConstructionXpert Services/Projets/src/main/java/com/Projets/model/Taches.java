package com.Projets.model;

import com.Projets.model.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taches {
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Statut statut;
}
