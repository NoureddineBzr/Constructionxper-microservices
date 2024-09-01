package com.Projets.repository;

import com.Projets.model.Projets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetsRepository extends JpaRepository<Projets, Long> {
}
