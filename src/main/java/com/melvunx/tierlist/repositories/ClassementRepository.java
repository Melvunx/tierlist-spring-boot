package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassementRepository extends JpaRepository<Classement, Integer> {
    List<Classement> findAllClassementByTierListId(Integer tierListId);

    List<Classement> findAllClassementByTierListId(Integer tierListId, Sort sort);

    void addRanked(Integer classementId, Integer rankedId);

    void removeRanked(Integer classementId, Integer rankedId);

    void addMultipleRanked(Integer classementId, List<Integer> rankedIds);

    void removeMultipleRanked(Integer classementId, List<Integer> rankedIds);
}
