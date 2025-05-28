package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassementRepository extends JpaRepository<Classement, Integer> {
//    List<Classement> findAllClassementByTierList(Integer tierListId);
//
//    void addRanked(Integer rankedId);
//    void deleteRanked(Integer rankedId);
//
//    void addMultipleRanked(List<Integer> rankedIds);
//    void deleteMultipleRanked(List<Integer> rankedIds);
}
