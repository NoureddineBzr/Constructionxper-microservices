package com.Projets.model;

import lombok.*;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullProjetResponse {
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Double budget;
    private List<Taches> taches;
}
