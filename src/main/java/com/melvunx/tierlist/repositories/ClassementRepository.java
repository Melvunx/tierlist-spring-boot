package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassementRepository extends JpaRepository<Classement, Integer> {
    //Ici, je peux créer des méthodes qui ne font pas partie du JPA exp findByTitle...
    // Classement findById(int id);
//    List<Ranked> findRankedByClassement(Classement classement);
}
