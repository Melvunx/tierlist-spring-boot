package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import com.melvunx.tierlist.entities.TierList;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassementRepository extends JpaRepository<Classement, Integer> {
    List<Classement> findAllClassementByTierListId(TierList tierList);
    List<Classement> findAllClassementByTierListId(TierList tierList, Sort sort);
}
