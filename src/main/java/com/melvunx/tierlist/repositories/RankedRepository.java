package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankedRepository extends JpaRepository<Ranked, Integer> {
    // Méthode pour récupérer tous les ranked d'un classement spécifique
    List<Ranked> findByClassementId(Integer classementId);
}
