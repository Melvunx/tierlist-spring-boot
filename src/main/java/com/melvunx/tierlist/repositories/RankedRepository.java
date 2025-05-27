package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankedRepository extends JpaRepository<Ranked, Integer> {
    List<Ranked> findAllRankedByClassement(Classement classement);
}
