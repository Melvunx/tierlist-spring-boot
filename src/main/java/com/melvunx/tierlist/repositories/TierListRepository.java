package com.melvunx.tierlist.repositories;

import com.melvunx.tierlist.entities.TierList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierListRepository extends JpaRepository<TierList, Integer> {}
