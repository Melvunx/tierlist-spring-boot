package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.TierList;
import com.melvunx.tierlist.repositories.TierListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TierListService {
    private final TierListRepository tierListRepository;

    public void create(TierList tierList) {
        tierListRepository.save(tierList);
    }

    public List<TierList> findAll() {
        return tierListRepository.findAll();
    }

    public TierList findById(Integer id) {
        Optional<TierList> rankList = tierListRepository.findById(id);
        return rankList.orElse(null);
    }
}
